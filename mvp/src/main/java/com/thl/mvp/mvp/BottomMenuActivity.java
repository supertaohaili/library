package com.thl.mvp.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.BottomNavigation;
import com.aurelhubert.ahbottomnavigation.BottomNavigationViewPager;
import com.thl.mvp.R;
import com.thl.mvp.base.XFragmentAdapter;
import com.thl.mvp.log.XLog;

import java.util.ArrayList;
import java.util.List;


public abstract class BottomMenuActivity extends StateActivity {

    protected BottomNavigationViewPager mBottomNavigationViewPager;
    protected XFragmentAdapter mXFragmentAdapter;
    protected List<Fragment> fragmentList;

    protected BottomNavigation mBottomNavigation;
    protected ArrayList<AHBottomNavigationItem> bottomNavigationItems;


    @Override
    public int getLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
        mBottomNavigationViewPager = (BottomNavigationViewPager) findViewById(R.id.bottomNavigationViewPager);
        mBottomNavigation = (BottomNavigation) findViewById(R.id.bottomNavigation);
        fragmentList = new ArrayList<>();
        bottomNavigationItems = new ArrayList<>();
        initBottomMenu(bottomNavigationItems, fragmentList);
        mXFragmentAdapter = new XFragmentAdapter(getSupportFragmentManager(), fragmentList);
        mBottomNavigationViewPager.setAdapter(mXFragmentAdapter);
        mBottomNavigation.addItems(bottomNavigationItems);
        mBottomNavigation.setTranslucentNavigationEnabled(true);
        mBottomNavigationViewPager.setPagingEnabled(true);

        mBottomNavigation.setOnTabSelectedListener(new BottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (wasSelected) {
                    return true;
                }
                mBottomNavigationViewPager.setCurrentItem(position, false);
                return true;
            }
        });

        mBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigation.setCurrentItem(position);
                XLog.e("position", "position:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initBottomNavigation(mBottomNavigation);
        initNavigationViewPager(mBottomNavigationViewPager);
    }

    protected void initBottomNavigation(BottomNavigation mBottomNavigation) {
    }

    protected void initNavigationViewPager(BottomNavigationViewPager mBottomNavigationViewPager) {

    }

    protected abstract void initBottomMenu(ArrayList<AHBottomNavigationItem> bottomNavigationItems, List<Fragment> fragmentList);

    protected void repaceBottomMenu(ArrayList<AHBottomNavigationItem> bottomNavigationItems, List<Fragment> fragmentList) {
        this.bottomNavigationItems.clear();
        this.bottomNavigationItems.addAll(bottomNavigationItems);

        this.fragmentList.clear();
        this.fragmentList.addAll(fragmentList);


        mBottomNavigation.removeAllItems();
        mBottomNavigation.addItems(this.bottomNavigationItems);

        mXFragmentAdapter.notifyDataSetChanged();
    }

    protected void addBottomMenu(ArrayList<AHBottomNavigationItem> bottomNavigationItems, List<Fragment> fragmentList) {
        this.bottomNavigationItems.addAll(bottomNavigationItems);

        this.fragmentList.clear();
        this.fragmentList.addAll(fragmentList);

        mBottomNavigation.removeAllItems();
        mBottomNavigation.addItems(this.bottomNavigationItems);
        mXFragmentAdapter.notifyDataSetChanged();

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        showContent();
    }

    public BottomNavigationViewPager getmBottomNavigationViewPager() {
        return mBottomNavigationViewPager;
    }

    public BottomNavigation getmBottomNavigation() {
        return mBottomNavigation;
    }
}
