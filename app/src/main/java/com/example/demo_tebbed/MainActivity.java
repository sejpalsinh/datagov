package com.example.demo_tebbed;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView camera,call,chat,status;
    PageViewAdepter pageViewAdepter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = findViewById(R.id.camera);

        status = findViewById(R.id.status);
        chat = findViewById(R.id.chat);
        viewPager = (ViewPager)findViewById(R.id.fragment_container);
        pageViewAdepter = new PageViewAdepter(getSupportFragmentManager());
        viewPager.setAdapter(pageViewAdepter);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageSelected(int position) {

                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onChangeTab(int position) {

        if (position == 0){
            camera.setTextSize(30);
            //camera.setTextColor(getColor(R.color.bright_color));


            status.setTextSize(20);
            //status.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(20);
            //chat.setTextColor(getColor(R.color.light_color));
        }

        if (position == 1){
            camera.setTextSize(20);
            //camera.setTextColor(getColor(R.color.light_color));


            status.setTextSize(20);
           // status.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(30);
           // chat.setTextColor(getColor(R.color.bright_color));
        }
        if (position == 2){
            camera.setTextSize(20);
            //camera.setTextColor(getColor(R.color.light_color));


            status.setTextSize(30);
            //status.setTextColor(getColor(R.color.bright_color));

            chat.setTextSize(20);
            //chat.setTextColor(getColor(R.color.light_color));
        }
        if (position == 3){
            camera.setTextSize(20);
            //camera.setTextColor(getColor(R.color.light_color));


            status.setTextSize(20);
            //status.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(20);
           // chat.setTextColor(getColor(R.color.light_color));
        }

    }


}
