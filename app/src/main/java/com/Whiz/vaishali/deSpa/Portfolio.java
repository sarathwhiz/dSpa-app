package com.Whiz.vaishali.deSpa;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Portfolio extends Fragment {
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    ViewPager viewPager;
    private int[] sliderImagesId = {
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic11, R.drawable.pic12,
            R.drawable.pic13, R.drawable.pic14,
            R.drawable.pic15, R.drawable.pic16,
            R.drawable.pic17, R.drawable.pic18,
            R.drawable.pic19,R.drawable.pic20,
            R.drawable.pic21,R.drawable.pic22,
            R.drawable.pic23,R.drawable.pic24,};
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_portfolio, container, false);
        viewFlipper=(ViewFlipper)getActivity().findViewById(R.id.viewFlipper) ;

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5000);



//        viewFlipper = (ViewFlipper) view.findViewById(R.id.bckgrndViewFlipper1);
////                fade_in = AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_in);
////                fade_out = AnimationUtils.loadAnimation(HomePage.this, android.R.anim.fade_out);
//        viewFlipper.setInAnimation(fade_in);
//        viewFlipper.setOutAnimation(fade_out);
////sets auto flipping
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(1000);
//        viewFlipper.startFlipping();


//
//        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPageAndroid);
//        AndroidImageAdapter adapterView = new AndroidImageAdapter(getActivity());
//        mViewPager.setAdapter(adapterView);


        return view;
    }


}