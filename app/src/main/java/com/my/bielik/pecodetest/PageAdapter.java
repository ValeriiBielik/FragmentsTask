package com.my.bielik.pecodetest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PageAdapter extends SmartFragmentStatePagerAdapter {

    private static final String TAG = "PageAdapter";
    private int maxPageNumber = 1;

    private List<Fragment> fragmentList = new ArrayList<>();

    public PageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

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

    @Override
    public int getItemPosition(Object object) {
        if (fragmentList.contains(object)) return fragmentList.indexOf(object);
        else return POSITION_NONE;
    }
}
