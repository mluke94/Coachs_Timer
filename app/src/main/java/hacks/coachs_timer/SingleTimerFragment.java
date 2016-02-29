package hacks.coachs_timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Sam on 2/28/2016.
 */
public class SingleTimerFragment extends Fragment {
    ListView splitList;
    Button startButton, splitButton;
    Timer currentTimer;
    TextView totalTime, totalMilli, splitTime, splitMilli;

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
        currentTimer = new Timer();
        return view;
    }
    
}
