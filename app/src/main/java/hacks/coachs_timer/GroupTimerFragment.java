package hacks.coachs_timer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by LukeM on 2/27/2016.
 */
public class GroupTimerFragment extends Fragment {
    ListView timerList;
    Button startButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.group_timer_layout, container, false);
        timerList = (ListView) view.findViewById(R.id.timer_list);
        startButton = (Button) view.findViewById(R.id.start_button);

        return view;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState)     {
            super.onActivityCreated(savedInstanceState);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
