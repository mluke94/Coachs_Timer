package hacks.coachs_timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by LukeM on 2/27/2016.
 */
public class GroupTimerFragment extends Fragment {
    ListView timerList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group_timer_layout, container, false);
        timerList = (ListView)view.findViewById(R.id.timer_list);


        return view;
    }
}
