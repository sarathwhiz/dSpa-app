package com.Whiz.vaishali.deSpa;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class AndroidImageAdapter extends PagerAdapter {
    Context mContext;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;

    AndroidImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    private int[] sliderImagesId = new int[]{
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic11, R.drawable.pic12,
            R.drawable.pic13, R.drawable.pic14,
            R.drawable.pic15, R.drawable.pic16,
            R.drawable.pic17, R.drawable.pic18,
            R.drawable.pic19,R.drawable.pic20,R.drawable.pic21,R.drawable.pic22,R.drawable.pic23,R.drawable.pic24,};



    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
       // mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setImageResource(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;


    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_portfolio, container, false);

        viewFlipper = (ViewFlipper) view.findViewById(R.id.bckgrndViewFlipper1);
//                fade_in = AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_in);
//                fade_out = AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_out);
        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
//sets auto flipping
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(1000);
        viewFlipper.startFlipping();
        return view;
    }

}