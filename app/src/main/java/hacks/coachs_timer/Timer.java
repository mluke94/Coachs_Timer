package hacks.coachs_timer;

import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Sam on 2/27/2016.
 */
public class Timer implements Parcelable {
    // This class is an individual timer
    private ArrayList<Split> splits; // history of splits
    private short splitNum; // current split number
    private boolean started; // is the timer running
    private boolean stopSplit; // does it split when stopped
    private long timeInMilliseconds = 0L; // Initialize and set to zero the time in milliseconds
    private long timeSwapBuff = 0L; // buffer time when paused
    private long updatedTime = 0L; // updated time for timer
    private long lastTime = 0L; // total time at last split
    private long currentTime = 0L; // total time at current split
    private long startTime = 0L; // time at start
    private Time time, split; // object of total time and current split
}
