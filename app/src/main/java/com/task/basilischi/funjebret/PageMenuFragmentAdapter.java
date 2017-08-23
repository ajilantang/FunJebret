package com.task.basilischi.funjebret;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by muhammadaa on 23/08/17.
 */

public class PageMenuFragmentAdapter extends FragmentPagerAdapter {

    private String[] titles = {"Home", "Schedule", "Following"};

    public PageMenuFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int num) {
        // Buat implementasi more fragment buat pengecekan disini
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int tabposition) {
        return titles[tabposition];
    }
}
