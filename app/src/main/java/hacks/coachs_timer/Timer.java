package hacks.coachs_timer;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.res.TypedArrayUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sam on 2/27/2016.
 */
public class Timer implements Parcelable {
    // This class is an individual timer
    private ArrayList<Split> splits; // history of splits
    private short splitNum; // current split number
    private boolean started; // is the timer running
    private boolean stopSplit; // does it split when stopped
    private long timeInMilliseconds; // Initialize and set to zero the time in milliseconds
    private long timeSwapBuff; // buffer time when paused
    private long updatedTime; // updated time for timer
    private long lastTime; // total time at last split
    private long currentTime ; // total time at current split
    private long startTime; // time at start
    private Time time, split; // object of total time and current split
    private String name; // name of the timer;

    public Timer(){
        // creates new timer with all the default values
        splits = new ArrayList<Split>();
        splitNum = 1;
        started = false;
        stopSplit = false;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
        lastTime = 0L;
        currentTime = 0L;
        startTime = 0L;
        time = new Time(0L);
        split = new Time(0L);
        name = "New Timer";
    }
    // Parcelling part
    public Timer(Parcel in) {
        int cnt = in.readInt(); // denotes total data length to get number of splits
        long[] data = new long[cnt];
        in.readLongArray(data);
        splitNum = (short) data[0];
        timeInMilliseconds = data[1];
        timeSwapBuff = data[2];
        updatedTime = data[3];
        lastTime = data[4];
        currentTime = data[5];
        startTime = data[6];
        time = new Time(data[7]);
        split = new Time(data[8]);
        int i = 9; // index where splits start
        while (data.length > (i + 3)){ // read in splits from parcel
            splits.add(new Split(new long[] {data[i], data[i+1], data[i+3]}));
            i += 3;
        }
        name = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ArrayList<Long> tempArray = new ArrayList<Long>();
        List<Long> newList = Arrays.asList(new Long[] {(long) splitNum,timeInMilliseconds,
                timeSwapBuff,updatedTime,lastTime,currentTime,startTime,time.getTime(),
                split.getTime()}); // write basic data to arraylist
        tempArray.addAll(newList);
        for (Split s:splits) { // add all splits to arraylist
            List<Long> splitList = Arrays.asList(s.getLongArray());
            tempArray.addAll(splitList);
        }
        int length = tempArray.size();
        long[] input = new long[length];
        for (int i = 0; i < length; i++) { // convert ArrayList to primitive long array
            input[i] = tempArray.get(i).longValue();
        }
        dest.writeInt(length); // write data to parcel
        dest.writeLongArray(input);
        dest.writeBooleanArray(new boolean[] {started, stopSplit});
        dest.writeString(name);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Timer createFromParcel(Parcel in) {
            return new Timer(in);
        }
        public Timer[] newArray(int size) {
            return new Timer[size];
        }
    }; // variable to be called that allows object to be parcelled
    // End code required for Parcelling
    
    public void start(long sysTime) {
        startTime = sysTime;
        started = true;
    }
    public void stop() {
        timeSwapBuff += timeInMilliseconds;
        started = false;
        if (stopSplit) {
            this.splitAction();
        }
    }
    public void update(long sysTime) {
        timeInMilliseconds = sysTime - startTime;
        updatedTime = timeSwapBuff + timeInMilliseconds;
        time = new Time(updatedTime);
        split = new Time(updatedTime-lastTime);
    }
    public void splitAction() {
        currentTime = updatedTime;
        Split temp = new Split(splitNum,currentTime-lastTime,currentTime);
        splits.add(temp);
        lastTime = currentTime;
        splitNum += 1;
    }
    public void reset() {
        started = false;
        splitNum = 1;
        splits.clear();
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
        lastTime = 0L;
        currentTime = 0L;
        time = new Time(0L);
        split = new Time(0L);
    }
    public ArrayList<Split> getSplits() {
        return splits;
    }
    public void setName(String in) {
        name = in;
    }
    public String getName() {
        return name;
    }
    public boolean isRunning() {
        return started;
    }
    public String getSplitNumber() {
        return splitNum;
    }
    public String getTotalTime() {
        return time.toString();
    }
    public String getRunningSplit() {
        return split.toString();
    }
    public String getLastSplit() {
        return splits.get(split.size()-1).getSplit();
    }

}
