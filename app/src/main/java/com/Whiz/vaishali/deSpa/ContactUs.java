package com.Whiz.vaishali.deSpa;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ContactUs extends Fragment {
    private Button mBtnCall1;
    private Button visit;
    private Button mBtnMailus;

    private Button getme;
    private Button twitter;
    private Button facebook;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us, container, false);


        visit=(Button) view.findViewById(R.id.visitus);
        mBtnMailus = (Button) view.findViewById(R.id.emailus);

        visit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), VisitUs.class);

                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0,0);

            }
        });

        getme=(Button) view.findViewById(R.id.getmethere);

        getme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DirectionActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });


        twitter=(Button) view.findViewById(R.id.twitter);

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Twitter.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });


        facebook=(Button) view.findViewById(R.id.facebook);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Facebookpage_activity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });



        mBtnCall1 = (Button) view.findViewById(R.id.call1);
        mBtnCall1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9028225054"));
                if (ActivityCompat.checkSelfPermission(getActivity().getApplication(), Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });


//      Intent callIntent = new Intent(Intent.ACTION_CALL);
//        callIntent.setData(Uri.parse("tel:9028843200"));
//        startActivity(callIntent);

        mBtnMailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:despastudio@gmail.com"));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        TextView a = (TextView) view.findViewById(R.id.contact);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        a.setTypeface(font);

        TextView b = (TextView) view.findViewById(R.id.add);
        Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        b.setTypeface(font1);

        TextView c = (TextView) view.findViewById(R.id.address);
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        c.setTypeface(font2);

        TextView d = (TextView) view.findViewById(R.id.mobile);
        Typeface font3 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        d.setTypeface(font3);

        TextView e = (Button) view.findViewById(R.id.getmethere);
        Typeface font4 = Typeface.createFromAsset(getActivity().getAssets(),"DancingScript_Regular.otf" );
        e.setTypeface(font4);


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