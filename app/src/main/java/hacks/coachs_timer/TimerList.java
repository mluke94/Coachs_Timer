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
public class TimerList extends ArrayAdapter {
    private CopyOnWriteArrayList<Timer> timers;
    private int layoutResourceId;
    private Context context;
    public TimerList(Context contextIn, int layoutResourceIdIn) {
        super(contextIn,layoutResourceIdIn);
        layoutResourceId = layoutResourceIdIn;
        context = contextIn;

        timers = new CopyOnWriteArrayList<>();
        timers.add(new Timer());
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TimerHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TimerHolder();
            holder.name = (TextView)row.findViewById(R.id.name);
            holder.splitNum = (TextView)row.findViewById(R.id.textView3);
            holder.totalTime = (TextView)row.findViewById(R.id.totalTime);
            holder.runningSplit = (TextView)row.findViewById(R.id.runningSplit);
            holder.lastSplit = (TextView)row.findViewById(R.id.lastSplit);
            holder.reset = (ImageButton)row.findViewById(R.id.resetButton);
            holder.delete = (ImageButton)row.findViewById(R.id.imageButton);
            //TODO: Add button listeners

            row.setTag(holder);
        }
        else {
            holder = (TimerHolder)row.getTag();
        }

        Timer timer = getTimer(position);
        holder.name.setText(timer.getName());
        holder.splitNum.setText(timer.getSplitNumber());
        holder.totalTime.setText(timer.getTotalTime());
        holder.runningSplit.setText(timer.getRunningSplit());
        holder.lastSplit.setText(timer.getLastSplit());

        System.out.print("get view reached");
        return row;
    }

    static class TimerHolder {
        TextView name,splitNum,totalTime,runningSplit,lastSplit;
        ImageButton reset,delete;
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
}
