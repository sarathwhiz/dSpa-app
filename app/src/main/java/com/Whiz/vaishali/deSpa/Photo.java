package com.Whiz.vaishali.deSpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Photo extends Activity {
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    int[] resources = {R.drawable.pic27, R.drawable.pic2, R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic11,
            R.drawable.pic12, R.drawable.pic13, R.drawable.pic14, R.drawable.pic15, R.drawable.pic16, R.drawable.pic17, R.drawable.pic18,
            R.drawable.pic19, R.drawable.pic20, R.drawable.pic21, R.drawable.pic22, R.drawable.pic23, R.drawable.pic24, R.drawable.pic25, R.drawable.pic26, R.drawable.pic1,};

//    private ViewPager vPager;
//
//    // the pager adapter that provides the pages to the view pager widget
//
//    private PagerAdapter pAdapter;
//
//    private Handler handler=new Handler();
//    private Runnable runnale=new Runnable(){
//        public void run(){
//            vPager.setCurrentItem(position,true);
//            if(position>=NUM_PAGES ) position=0;
//            else position++;
//            // Move to the next page after 10s
//            handler.postDelayed(runnale, 3000);
//        }
//    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(4000); // flip every 2 seconds (2000ms)
        // Add all the images to the ViewFlipper
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resources[i]);
            mViewFlipper.addView(imageView);
        }
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);

    }
    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gesture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {

        Intent i = new Intent(Photo.this,TheDeSpaStudio.class);
        startActivity(i);
        finish();

//        Intent launchNextActivity;
//        launchNextActivity = new Intent(Photo.this, TheDeSpaStudio.class);
//        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(launchNextActivity);

    }
}