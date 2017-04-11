package com.Whiz.vaishali.deSpa;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AboutUs extends Fragment {
private Button mBtnCall12;
    TextView abt ;
    TextView  vbn ;
    TextView  mnb ;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us, container, false);


        TextView abt = (TextView) view.findViewById(R.id.aboutus);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        abt.setTypeface(font);

        TextView vbn = (TextView) view.findViewById(R.id.about);
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        vbn.setTypeface(font1);

        TextView mnb = (TextView) view.findViewById(R.id.about2);
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        mnb.setTypeface(font2);

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
