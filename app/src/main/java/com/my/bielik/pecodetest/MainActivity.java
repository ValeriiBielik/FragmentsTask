package com.my.bielik.pecodetest;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import static com.my.bielik.pecodetest.BlankFragment.EXTRA_PAGE_TO_OPEN;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

    private ViewPager viewPager;
    private PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        pageAdapter = new PageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pageAdapter);

        if (getIntent().hasExtra(EXTRA_PAGE_TO_OPEN)) {
            viewPager.setCurrentItem(getIntent().getIntExtra(EXTRA_PAGE_TO_OPEN, 1) - 1);
        } else {
            viewPager.setCurrentItem(0);
        }

    }

    @Override
    public void onAddBtnClick(int currentPage) {
        pageAdapter.addFragment();
        viewPager.setCurrentItem(currentPage);
    }

    @Override
    public void onRemoveBtnClick() {
        ((NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE)).cancel(pageAdapter.getCount());
        pageAdapter.removeFragment();
        viewPager.setCurrentItem(pageAdapter.getCount() - 1);
    }

}