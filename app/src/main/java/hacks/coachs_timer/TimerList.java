package hacks.coachs_timer;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Sam on 2/27/2016.
 */
public class TimerList {
    private CopyOnWriteArrayList<Timer> timers;
    public TimerList() {
        timers = new CopyOnWriteArrayList<>();
    }


    public void addTimer() {
        Timer tIn = new Timer();
        timers.add(tIn);
    }
    public void removeTimer(int i) {
        timers.remove(i);
    }
    public void clearTimers() {
        timers.clear();
    }
    public void updateAll(long sysTime) {
        for(Timer t: timers) {
            if (t.isRunning()) {
                t.update(sysTime);
            }
        }
    }
    public void stopAll() {
        for (Timer t: timers) {
            if (t.isRunning()) {
                t.stop();
            }
        }
    }
    public void startAll(long sysTime) {
        for (Timer t: timers) {
            if (!t.isRunning()) {
                t.start(sysTime);
            }
        }
    }
    public Timer getTimer(int index) {
        return timers.get(index);
    }
    public Timer[] toArray() {
        int l = timers.size();
        Timer[] temp = new Timer[l];
        for (int i = 0; i < l; i++) {
            temp[i] = timers.get(i);
        }
        return temp;
    }
}
