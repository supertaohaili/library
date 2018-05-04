package com.thl.mvp.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.thl.mvp.mvp.StateFragment;

import www.thl.com.utils.ToastUtils;

/**
 * Created by Administrator on 2018/5/3.
 */

public class DemoFragment extends StateFragment {

    private FrameLayout flt_content;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void bindUI(View rootView) {

        flt_content = (FrameLayout) rootView.findViewById(R.id.flt_content);
        super.bindUI(rootView);

    }


    @Override
    protected void LazyData() {
        super.LazyData();
        ToastUtils.showShort("LazyData");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showContent();
            }
        }, 3000);
    }

    @Override
    protected Object getStateView() {
        return flt_content;
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.navigationBarColor(R.color.white)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .keyboardEnable(true)
                .init();
    }
}
