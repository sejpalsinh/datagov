package com.example.demo_tebbed;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PageViewAdepter2 extends FragmentPagerAdapter {
    public PageViewAdepter2(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new Fragment_Hospital();
                break;
            case 1:
                fragment = new Fragment_facilities();
                break;
            case 2:
                fragment = new Fragment_doctor();
                break;

        }


        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
