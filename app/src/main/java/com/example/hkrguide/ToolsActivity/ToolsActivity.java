package com.example.hkrguide.ToolsActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.hkrguide.BaseActivity;
import com.example.hkrguide.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ToolsActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9, iv10, iv11;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11;
    ObjectAnimator objectAnimator;

    @Override
    public void onResume() {
        // Initialize Super Class
        super.onResume();
        // Set Checked Item
        ((NavigationView) findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_item_tools);
        super.currentNavItem = R.id.nav_item_tools;
        // Set Toolbar Text
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toobar_title_tools);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Super Class
        super.onCreate(savedInstanceState);
        // Inflate Layout into Parent Frame Layout
        FrameLayout frameLayout = findViewById(R.id.activity_frame);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(R.layout.activity_tools, findViewById(R.id.root_tools), false);
        frameLayout.addView(activityView);

        /* Activity Code */

        iv1 = findViewById(R.id.imageView);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);
        iv7 = findViewById(R.id.imageView7);
        iv8 = findViewById(R.id.imageView8);
        iv9 = findViewById(R.id.imageView9);
        iv10 = findViewById(R.id.imageView10);
        iv11 = findViewById(R.id.imageView11);


        tv1 = findViewById(R.id.textView2);
        tv2 = findViewById(R.id.textView3);
        tv3 = findViewById(R.id.textView4);
        tv4 = findViewById(R.id.textView5);
        tv5 = findViewById(R.id.textView6);
        tv6 = findViewById(R.id.textView7);
        tv7 = findViewById(R.id.textView8);
        tv8 = findViewById(R.id.textView9);
        tv9 = findViewById(R.id.textView10);
        tv10 = findViewById(R.id.textView11);
        tv11 = findViewById(R.id.textView12);

        // to make image view clickable.
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        iv8.setOnClickListener(this);
        iv9.setOnClickListener(this);
        iv10.setOnClickListener(this);
        iv11.setOnClickListener(this);

        // to make image view touchable.
        iv1.setOnTouchListener(this);
        iv2.setOnTouchListener(this);
        iv3.setOnTouchListener(this);
        iv4.setOnTouchListener(this);
        iv5.setOnTouchListener(this);
        iv6.setOnTouchListener(this);
        iv7.setOnTouchListener(this);
        iv8.setOnTouchListener(this);
        iv9.setOnTouchListener(this);
        iv10.setOnTouchListener(this);
        iv11.setOnTouchListener(this);

        // to animate textViews.
        animateTextView();
    }

    public void animateTextView() {
        ArrayList<TextView> tvList = new ArrayList<>();
        tvList.add(tv1);
        tvList.add(tv2);
        tvList.add(tv3);
        tvList.add(tv4);
        tvList.add(tv5);
        tvList.add(tv6);
        tvList.add(tv7);
        tvList.add(tv8);
        tvList.add(tv9);
        tvList.add(tv10);
        tvList.add(tv11);

        for (int i = 0; i < tvList.size(); i++) {
            //same animation for all textViews:
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvList.get(i), "alpha", 0f, 1f);
            objectAnimator.setDuration(3000);
            objectAnimator.setRepeatCount(Animation.INFINITE);
            objectAnimator.start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.imageView:
                objectAnimator = ObjectAnimator.ofFloat(iv1, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView2:
                objectAnimator = ObjectAnimator.ofFloat(iv2, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView3:
                objectAnimator = ObjectAnimator.ofFloat(iv3, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView4:
                objectAnimator = ObjectAnimator.ofFloat(iv4, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView5:
                objectAnimator = ObjectAnimator.ofFloat(iv5, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView6:
                objectAnimator = ObjectAnimator.ofFloat(iv6, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView7:
                objectAnimator = ObjectAnimator.ofFloat(iv7, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView8:
                objectAnimator = ObjectAnimator.ofFloat(iv8, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView9:
                objectAnimator = ObjectAnimator.ofFloat(iv9, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView10:
                objectAnimator = ObjectAnimator.ofFloat(iv10, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
            case R.id.imageView11:
                objectAnimator = ObjectAnimator.ofFloat(iv11, "alpha", 1f, 0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        objectAnimator.setFloatValues(0f, 1f);
                    }
                });
                break;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.imageView:
                //open the app if its installed on the phone
                // or send the user to google play to download the app.
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("se.skanetrafiken.washington");
                    startActivity(intent);
                } catch (Exception e) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=se.skanetrafiken.washington");// sending to Deal Website
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);
                }
                break;
            case R.id.imageView2:
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.instructure.candroid");
                    startActivity(intent);
                } catch (Exception e) {
                    Uri uri2 = Uri.parse("https://play.google.com/store/apps/details?id=com.instructure.candroid");// sending to Deal Website
                    Intent i2 = new Intent(Intent.ACTION_VIEW, uri2);
                    startActivity(i2);
                }

                break;
            case R.id.imageView3:
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("se.kronox.app");
                    startActivity(intent);
                } catch (Exception e) {
                    Uri uri3 = Uri.parse("https://play.google.com/store/apps/details?id=se.kronox.app");// sending to Deal Website
                    Intent i3 = new Intent(Intent.ACTION_VIEW, uri3);
                    startActivity(i3);
                }
                break;
            case R.id.imageView4:
                Uri uri4 = Uri.parse("https://www.hkr.se/om-hkr/organisation/bibliotekochhogskolepedagogik/bibliotek/");// sending to Deal Website
                Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                startActivity(i4);
                break;
            case R.id.imageView5:
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("f24s_mobile.Android");
                    startActivity(intent);
                } catch (Exception e) {
                    Uri uri5 = Uri.parse("https://play.google.com/store/apps/details?id=f24s_mobile.Android");// sending to Deal Website
                    Intent i5 = new Intent(Intent.ACTION_VIEW, uri5);
                    startActivity(i5);
                }
                break;
            case R.id.imageView6:
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("se.onlinepizza");
                    startActivity(intent);
                } catch (Exception e) {
                    Uri uri6 = Uri.parse("https://play.google.com/store/apps/details?id=se.onlinepizza");// sending to Deal Website
                    Intent i6 = new Intent(Intent.ACTION_VIEW, uri6);
                    startActivity(i6);
                }
                break;
            case R.id.imageView7:
                Uri uri7 = Uri.parse("https://www.c4shopping.se/");// sending to Deal Website
                Intent i7 = new Intent(Intent.ACTION_VIEW, uri7);
                startActivity(i7);
                break;
            case R.id.imageView8:
                Uri uri8 = Uri.parse("https://www.adlibris.com/se");// sending to Deal Website
                Intent i8 = new Intent(Intent.ACTION_VIEW, uri8);
                startActivity(i8);
                break;
            case R.id.imageView9:
                Uri uri9 = Uri.parse("https://www.kristianstad.se/globalassets/dokument/uppleva-och-gora/turism/broschyrer/kristianstad_egen_hand_en_ty_red.pdf");// sending to Deal Website
                Intent i9 = new Intent(Intent.ACTION_VIEW, uri9);
                startActivity(i9);
                break;
            case R.id.imageView10:
                Uri uri10 = Uri.parse("https://www.student.ladok.se/student/loggain");// sending to Deal Website
                Intent i10 = new Intent(Intent.ACTION_VIEW, uri10);
                startActivity(i10);
                break;
            case R.id.imageView11:
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("se.mecenat.app");
                    startActivity(intent);
                } catch (Exception e) {
                    Uri uri11 = Uri.parse("https://play.google.com/store/apps/details?id=se.mecenat.app&hl=en&gl=US");// sending to Deal Website
                    Intent i11 = new Intent(Intent.ACTION_VIEW, uri11);
                    startActivity(i11);
                }
                break;
        }
    }
}