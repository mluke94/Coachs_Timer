package hacks.coachs_timer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sam on 2/26/2016.
 */
public class Time implements Parcelable{
    // This class stores the value of a time in both milliseconds long form and individual parcing

    private short hours;
    private byte mins;
    private byte secs;
    private short milSecs;
    private long time;

    public Time(long longTime) {
        time = longTime;
        hours = (short) (longTime/3600000);
        mins = (byte) ((longTime/60000)%60);
        secs = (byte) ((longTime/1000)%60);
        milSecs = (short) (longTime % 1000);
    }
    public Time(short h, byte m, byte s, short ms) {
        hours = h;
        mins = m;
        secs = s;
        milSecs = ms;
        time = (long) (hours*3600000 + mins*60000 + secs*1000 + milSecs);
    }
    // Methods required for Parcelling
    public Time(Parcel in) {
        long[] data = new long[5];
        in.readLongArray(data);
        time = data[0];
        hours = (short) data[1];
        mins = (byte) data[2];
        secs = (byte) data[3];
        milSecs = (short) data[4];
    }
    @Override
    // Unsure what this method does but is required for Parcelling
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLongArray(new long[] {this.time,
                (long) this.hours,
                (long) this.mins,
                (long) this.secs,
                (long) this.milSecs});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }
        public Time[] newArray(int size) {
            return new Time[size];
        }
    }; // variable to be called that allows object to be parcelled
    // End code required for Parcelling

    public String toString() {
        // Returns all pertinent values describing the time
        return String.valueOf(hours) + ":" + String.format("%02d", mins) + ":" +
                String.format("%02d", secs) + "." + String.format("%03d", milSecs);
    }
    public String timeString() {
        // Returns all pertinent time values excluding milliseconds
        return String.valueOf(hours) + ":" + String.format("%02d", mins) + ":" +
                String.format("%02d", secs) + ".";
    }
    public String milString() {
        // Returns milliseconds separately to allow for text resizing
        return String.format("%03d", milSecs);
    }
    public long getTime(){
        return time;
    }
}
