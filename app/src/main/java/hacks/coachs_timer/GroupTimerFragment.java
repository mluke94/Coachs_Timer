package hacks.coachs_timer;

import android.app.Fragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by LukeM on 2/27/2016.
 */
public class GroupTimerFragment extends Fragment {
    ListView timerList;
    Button startButton;
    Timer overallTimer;
    TextView overallTime;
    TextView overallMilli;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group_timer_layout, container, false);
        timerList = (ListView) view.findViewById(R.id.timer_list);
        startButton = (Button) view.findViewById(R.id.start_button);
        overallTimer = new Timer();
        overallTime = (TextView) view.findViewById(R.id.overallTimer);
        overallMilli = (TextView) view.findViewById(R.id.overallMilli);
        return view;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState)     {
            super.onActivityCreated(savedInstanceState);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long sysClock = SystemClock.uptimeMillis();
                if(overallTimer.isRunning())
                    overallTimer.stop();
                else {
                    overallTimer.start(sysClock);
                    TimerAdapter tA = (TimerAdapter) timerList.getAdapter();
                    if (tA.getCount() > 0) {
                        Timer[] data = tA.getData();
                        for (Timer t : data) {
                            t.start(sysClock);
                        }
                    }
                }
                System.out.println("Clicked");
            }
        });

               /* timerList.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
                    @Override
                    public void onSwipeRight() {
                        System.out.println("Swipe right;): ");
                    }

                    @Override
                    public void onSwipeLeft() {
                        System.out.println("Swipe left)");
                    }
                });*/


    }



    public void setAdapter(TimerAdapter tl) {
        timerList.setAdapter(tl);
    }
    public void updateView(int index, Timer t) {
        View v = timerList.getChildAt(index - timerList.getFirstVisiblePosition());
        if(v == null){
            return;
        }

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView splitNum = (TextView) v.findViewById(R.id.textView3);
        TextView totalTime = (TextView) v.findViewById(R.id.totalTime);
        TextView runningSplit = (TextView) v.findViewById(R.id.runningSplit);
        TextView lastSplit = (TextView) v.findViewById(R.id.lastSplit);

        name.setText(t.getName());
        splitNum.setText(t.getSplitNumber());
        totalTime.setText(t.getTotalTime());
        runningSplit.setText(t.getRunningSplit());
        lastSplit.setText(t.getLastSplit());
    }
    public int[] visibleRange() {
        return new int[] {timerList.getFirstVisiblePosition(), timerList.getLastVisiblePosition()};
    }
    public void updateOverallTime(long sysTime) {
        if(overallTimer.isRunning()) {
            overallTimer.update(sysTime);
            Time t = overallTimer.getTime();
            overallTime.setText(t.timeString());
            overallMilli.setText(t.milString());
        }
    }
}
