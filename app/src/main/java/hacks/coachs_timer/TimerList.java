package hacks.coachs_timer;


import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Sam on 2/27/2016.
 */
public class TimerList {
    private CopyOnWriteArrayList<Timer> timers;

    public TimerList() {
        timers = new CopyOnWriteArrayList<>();
    }

    public void addTimer(Timer tIn) {
        timers.add(tIn);
    }
    public void removeTimer(int i) {
        timers.remove(i);
    }
    public void clear() {
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
}
