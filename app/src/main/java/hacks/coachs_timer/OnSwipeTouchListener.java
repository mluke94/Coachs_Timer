package hacks.coachs_timer;

/**
 * Created by LukeM on 2/28/2016.
 * Source code from: http://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures
 */

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Detects left and right swipes across a view.
 */
public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;
    public View view;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public void doubleTap() {
    }

    public void singleTap(){
    }

    public void longPress(){
    }

    public boolean onTouch(View v, MotionEvent event) {
        view = v;
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 25;
        private static final int SWIPE_VELOCITY_THRESHOLD = 25;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            doubleTap();
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            singleTap();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            longPress();
            super.onLongPress(e);
        }
    }
}