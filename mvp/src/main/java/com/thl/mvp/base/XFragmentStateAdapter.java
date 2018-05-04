package com.thl.mvp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import www.thl.com.utils.EmptyUtils;

public class XFragmentStateAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private List<String> mTitles;

    public XFragmentStateAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.mFragmentList = fragmentList;
    }

    public XFragmentStateAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> mTitles) {
        super(fragmentManager);
        this.mTitles = mTitles;
        this.mFragmentList = fragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return !EmptyUtils.isEmpty(mTitles) ? mTitles.get(position) : "";
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
