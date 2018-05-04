package com.thl.mvp.mvp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.thl.mvp.R;
import com.thl.mvp.base.XFragmentAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public abstract class TabFragment extends XFragment {

    private View stateView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<String> titles;
    private List<Fragment> fragments;
    private XFragmentAdapter mXFragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    public void bindUI(View rootView) {
        stateView = rootView.findViewById(R.id.llt_content);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.content_vp);
        super.bindUI(rootView);

        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        initTabs(titles, fragments);

        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));//添加tab选项卡
        }

        mXFragmentAdapter = new XFragmentAdapter(getChildFragmentManager(), fragments,titles);
        mViewPager.setAdapter(mXFragmentAdapter);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//设置tab模式，当前为系统默认模式
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mViewPager.setCurrentItem(0);

        initTabLayout(mTabLayout);
        initViewPager(mViewPager);
    }

    protected abstract void initTabs(List<String> titles, List<Fragment> fragments);

    protected void repaceTabs(List<String> titles, List<Fragment> fragments) {
        mTabLayout.removeAllTabs();
        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));//添加tab选项卡
        }
        this.fragments.clear();
        this.fragments.addAll(fragments);
        mXFragmentAdapter.notifyDataSetChanged();
    }

    protected void addTabs(List<String> titles, List<Fragment> fragments) {
        for (String title : titles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));//添加tab选项卡
        }
        this.fragments.addAll(fragments);
        mXFragmentAdapter.notifyDataSetChanged();
    }

    protected void initViewPager(ViewPager mViewPager) {

    }

    protected void initTabLayout(TabLayout mTabLayout) {

    }
    

//    @Override
//    protected void LazyData() {
//        showContent();
//    }

//    @Override
//    protected Object getStateView() {
//        return stateView;
//    }
}
