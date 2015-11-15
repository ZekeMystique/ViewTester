package com.example.administrator.viewtester;

import android.gesture.GestureOverlayView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements OnGestureListener {
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         detector = new GestureDetector(this, this);
        View myView = new View(this);
    }

    private static int page = 1;
    private static final String TAG = "Swipetesting";
    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d(TAG, "+ onTouchEvent(event:" + event + ")");
        detector.onTouchEvent(event);
        Log.d(TAG, "- onTouchEvent()");
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
                result = true;
            }
            result = true;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
    public void onSwipeRight(){
        Log.d(TAG, "Swipe Right");
        if(page == 1){
            page = 3;
            setContentView(R.layout.activity_third);
        }
        else if(page == 2)
        {
            page = 1;
            setContentView(R.layout.activity_main);
        }

    }
    public void onSwipeLeft(){
        Log.d(TAG, "Swipe Left");
        setContentView(R.layout.activity_second);
        if(page == 1){
            page = 2;
            setContentView(R.layout.activity_second);
        }
        else if(page == 3)
        {
            page = 1;
            setContentView(R.layout.activity_main);
        }

    }
}
