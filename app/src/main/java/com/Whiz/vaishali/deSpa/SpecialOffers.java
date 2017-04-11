package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SpecialOffers extends Fragment {
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;

    @Override

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle saveInstanceState) {


        View view = inflater.inflate(R.layout.special_offers, container, false);

        viewFlipper = (ViewFlipper)view.findViewById(R.id.bckgrndViewFlipper1);
//        fade_in = AnimationUtils.loadAnimation(Gallery.this, android.R.anim.fade_in);
//        fade_out = AnimationUtils.loadAnimation(Gallery.this, android.R.anim.fade_out);
        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
//sets auto flipping
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.startFlipping();


        return view;

    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        Intent intent = new Intent(getActivity(), TheDeSpaStudio.class);
                        startActivity(intent);

                        return true;
                    }
                }
                return false;
            }
        });
    }
}
