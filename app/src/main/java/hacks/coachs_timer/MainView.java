package hacks.coachs_timer;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by LukeM on 2/27/2016.
 *
 * Main view class for timer
 */
public class MainView extends View {
    private LinearLayout timeDisplay;
    private TextView mainTime, milliTime;
    private View view;

    /*
    * Main Constructor
    * */
    public MainView(Activity mainActivity) {
        super(mainActivity);
        view = inflate(getContext(), R.layout.activity_main, null);

        //init values
    }


    /*
    * Takes time in the form 0:00:00 and milli in the form :0
    */
    public void updateMainTimer(String time, String milli) {

    }
}
