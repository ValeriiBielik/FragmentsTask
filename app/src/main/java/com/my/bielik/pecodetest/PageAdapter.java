package com.my.bielik.pecodetest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "PageAdapter";

    private List<Fragment> fragmentList = new ArrayList<>();
    private int maxPageNumber = 1;

    PageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        addFragment();
    }

    void addFragment() {
        fragmentList.add(BlankFragment.newInstance(maxPageNumber));
        notifyDataSetChanged();
        maxPageNumber++;
        Log.e(TAG, "addFragment " + getCount());
    }

    void removeFragment() {
        if (getCount() > 1) {
            Log.e(TAG, "removeFragment " + getCount());
            fragmentList.remove(getCount() - 1);
            notifyDataSetChanged();
            maxPageNumber--;
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
