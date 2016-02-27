package hacks.coachs_timer;

import android.os.Parcel;
import android.os.Parcelable;

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

    public Timer(){
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
    }
    // Parcelling part
    public Timer(Parcel in) {
        int cnt = in.readInt();
        long[] data = new long[cnt];
        in.readLongArray(data);

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
                split.getTime()});
        tempArray.addAll(newList);
        for (Split s:splits) {
            List<Long> splitList = Arrays.asList(s.getLongArray());
            tempArray.addAll(splitList);
        }
    }
}
