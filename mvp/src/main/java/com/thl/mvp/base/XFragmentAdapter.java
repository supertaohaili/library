package com.thl.mvp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2016/12/10.
 */

public class XFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles;

    public XFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList.clear();
        this.fragmentList.addAll(fragmentList);

    }

    public XFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titles) {
        super(fm);
        this.fragmentList.clear();
        this.fragmentList.addAll(fragmentList);
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.size() > position) {
            return titles.get(position);
        }
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
