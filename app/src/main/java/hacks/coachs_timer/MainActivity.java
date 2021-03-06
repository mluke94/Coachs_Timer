package hacks.coachs_timer;
// Luke and Sam
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ImageButton fab;
    TimerList tList;
    TimerAdapter tAdapt;
    GroupTimerFragment groupFragment;
    SingleTimerFragment singleFragment;
    MainActivity main = this;
    private Handler customHandler = new Handler(); //Initialize and create a Handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container) != null) {


            if(savedInstanceState != null) {
                return;
            }

            //new fragment
            groupFragment = new GroupTimerFragment();
            singleFragment = new SingleTimerFragment();
            singleFragment.setContext(this);
            //intent?

            //Add fragment
            getFragmentManager().beginTransaction().add(R.id.fragment_container, groupFragment).commit();
        }

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        tList = new TimerList();
        tAdapt = new TimerAdapter(main, R.layout.timer_list_layout,tList.toArray());

        //Add FAB
        fab = (ImageButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // does all the things
                tList.addTimer();
                tAdapt = new TimerAdapter(main, R.layout.timer_list_layout,tList.toArray());
                groupFragment.setAdapter(tAdapt);
            }
        });

        final int abTitleId = getResources().getIdentifier("action_bar_title", "id", "android");
        System.out.println(abTitleId);

        customHandler.postDelayed(updateTimerThread,0);
    }

    // Create runnable that updates Timers
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            //timer update logic here
            long systemClock = SystemClock.uptimeMillis();
            tList.updateAll(systemClock);
            if (groupFragment.isVisible()) {
                groupFragment.updateOverallTime(systemClock);
                int[] visible = groupFragment.visibleRange();
                for (int i = visible[0]; i <= visible[1]; i++) {
                    if (tList.getTimer(i).getDelete()) {
                        tList.removeTimer(i);
                        tAdapt = new TimerAdapter(main, R.layout.timer_list_layout, tList.toArray());
                        groupFragment.setAdapter(tAdapt);
                        visible[1]--;
                        break;
                    }
                    if (tList.getTimer(i).getExpand()) {
                        FragmentTransaction transition = getFragmentManager().beginTransaction();
                        singleFragment.setTimer(tList.getTimer(i));
                        transition.replace(R.id.fragment_container, singleFragment);
                        transition.addToBackStack(null);
                        transition.commit();
                    }
                    groupFragment.updateView(i, tList.getTimer(i));
                }
            } else if (singleFragment.isVisible()) {
                if (singleFragment.getTimer().getExpand()) {
                    singleFragment.getTimer().setExpand(false);
                    singleFragment.setAdapter();
                    setTitle(singleFragment.getTimer().getName());
                }
                singleFragment.update(systemClock);
            }

            customHandler.postDelayed(this, 0); //post action with no delay
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            setTitle("Coach's Timer");
            getFragmentManager().popBackStack();
        }
    }

    public void startAllTimers() {
        tList.startAll(SystemClock.uptimeMillis());
    }
    public void startTimer(int index) {
        tList.getTimer(index).start(SystemClock.uptimeMillis());
    }
    public void stopTimer(int index) {
        tList.getTimer(index).stop();
    }
}
