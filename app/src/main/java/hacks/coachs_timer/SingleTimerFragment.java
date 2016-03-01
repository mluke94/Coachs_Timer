package hacks.coachs_timer;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sam on 2/28/2016.
 */
public class SingleTimerFragment extends Fragment {
    ListView splitList;
    Button startButton, splitButton;
    Timer currentTimer;
    TextView totalTime, totalMilli, splitTime, splitMilli;
    Context c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.single_timer_layout,container,false);
        splitList = (ListView) view.findViewById(R.id.split_list);
        startButton = (Button) view.findViewById(R.id.startButton);
        splitButton = (Button) view.findViewById(R.id.splitButton);
        totalTime = (TextView) view.findViewById(R.id.timerValue);
        totalMilli = (TextView) view.findViewById(R.id.timerMil);
        splitTime = (TextView) view.findViewById(R.id.splitValue);
        splitMilli = (TextView) view.findViewById(R.id.splitMil);
        if (currentTimer.isRunning()) {
            startButton.setText("STOP");
            splitButton.setText("SPLIT");
        } else {
            startButton.setText("START");
            splitButton.setText("RESEST");
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long sysClock = SystemClock.uptimeMillis();
                if (currentTimer.isRunning()) {
                    currentTimer.stop();
                    startButton.setText("START");
                    splitButton.setText("RESEST");
                } else {
                    currentTimer.start(sysClock);
                    startButton.setText("STOP");
                    splitButton.setText("SPLIT");
                }
            }
        });
        splitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTimer.isRunning()) {
                    currentTimer.splitAction();
                    // add splitadapter and update
                    setAdapter();
                } else {
                    currentTimer.reset();
                    // add splitadapter and update
                    setAdapter();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c = context;
    }

    public void setTimer(Timer t) {
        currentTimer = t;
        //add split adapter and update
    }

    // should this be private?
    public void setAdapter() {
        ArrayList<Split> splits = currentTimer.getSplits();
        ArrayAdapter<Split> adapter = new ArrayAdapter<Split>(c,R.layout.splitlayout,splits.toArray(new Split[splits.size()]));
        splitList.setAdapter(adapter);
    }
    public void update(long sysTime) {
        if (currentTimer.isRunning()) {
            currentTimer.update(sysTime);
        }
            Time t = currentTimer.getTime();
            Time s = currentTimer.getSplit();
            totalTime.setText(t.timeString());
            totalMilli.setText(t.milString());
            splitTime.setText(s.timeString());
            splitMilli.setText(s.milString());
    }
    public Timer getTimer() {
        return currentTimer;
    }
}
