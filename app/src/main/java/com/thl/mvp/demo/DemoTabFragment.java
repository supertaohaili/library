package com.thl.mvp.demo;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.thl.mvp.mvp.StateFragment;
import com.thl.mvp.mvp.TabFragment;

import java.util.List;

import www.thl.com.utils.ToastUtils;

/**
 * Created by Administrator on 2018/5/3.
 */

public class DemoTabFragment extends TabFragment {


    @Override
    protected void initTabs(List<String> titles, List<Fragment> fragments) {
        titles.add("tab");
        titles.add("tab2");
//        titles.add("tab3");
//        titles.add("tab4");
//        titles.add("tab5");
//        titles.add("tab6");
//        titles.add("tab7");
//        titles.add("tab8");
        fragments.add(new DemoFragment());
        fragments.add(new DemoFragment());
//        fragments.add(new DemoFragment());
//
//        fragments.add(new DemoFragment());
//        fragments.add(new DemoFragment());
//        fragments.add(new DemoFragment());
//        fragments.add(new DemoFragment());
//        fragments.add(new DemoFragment());
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.navigationBarColor(R.color.white)
                .titleBarMarginTop(R.id.tabs)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
    }
}
