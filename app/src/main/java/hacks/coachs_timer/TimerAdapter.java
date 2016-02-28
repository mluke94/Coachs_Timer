package hacks.coachs_timer;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sam on 2/28/2016.
 */
public class TimerAdapter extends ArrayAdapter {
    Context context;
    int layoutResourceId;
    Timer data[] = null;

    public TimerAdapter(Context context, int layoutResourceId, Timer[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
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
            holder.delete = (ImageButton)row.findViewById(R.id.deleteButton);
            //TODO: Add button listeners

            row.setTag(holder);
            row.setOnTouchListener(new OnSwipeTouchListener(context) {
                @Override
                public void onSwipeRight() {
                    if(view != null) {
                        ListView lV = (ListView) view.getParent();
                        int position = lV.getPositionForView(view);
                        if(data[position].isRunning())
                            data[position].stop();
                        else
                            data[position].start(SystemClock.uptimeMillis());
                    }
                    System.out.println("Swipe right;): ");
                }

                @Override
                public void onSwipeLeft() {
                    if(view != null) {
                        ListView lV = (ListView) view.getParent();
                        int position = lV.getPositionForView(view);
                        if (data[position].isRunning())
                            data[position].splitAction();
                    }
                    System.out.println("Swipe left)");
                }

                @Override
                public void doubleTap() {
                    ImageButton resetButton = (ImageButton) view.findViewById(R.id.resetButton);
                    ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
                    resetButton.setVisibility(View.VISIBLE);
                    deleteButton.setVisibility(View.VISIBLE);
                    resetButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ll = (LinearLayout) v.getParent();
                            ListView lv = (ListView) ll.getParent();
                            int position = lv.getPositionForView(ll);
                            data[position].reset();
                        }
                    });
                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout ll = (LinearLayout) v.getParent();
                            ListView lv = (ListView) ll.getParent();
                            int position = lv.getPositionForView(ll);
                            data[position].setDelete();
                        }
                    });
                    System.out.println("Double Tap");
                }

                @Override
                public void singleTap() {
                    ImageButton resetButton = (ImageButton) view.findViewById(R.id.resetButton);
                    ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
                    resetButton.setVisibility(View.INVISIBLE);
                    deleteButton.setVisibility(View.INVISIBLE);
                    resetButton.setOnClickListener(null);
                    deleteButton.setOnClickListener(null);
                    System.out.println("Single Tap");
                }
            });
        }
        else {
            holder = (TimerHolder)row.getTag();
        }

        Timer timer = data[position];
        holder.name.setText(timer.getName());
        holder.splitNum.setText(timer.getSplitNumber());
        holder.totalTime.setText(timer.getTotalTime());
        holder.runningSplit.setText(timer.getRunningSplit());
        holder.lastSplit.setText(timer.getLastSplit());

        return row;
    }

    public Timer[] getData(){
        return data;
    }

    static class TimerHolder {
        TextView name,splitNum,totalTime,runningSplit,lastSplit;
        ImageButton reset,delete;
    }
}
