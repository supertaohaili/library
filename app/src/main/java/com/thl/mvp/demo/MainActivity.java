package com.thl.mvp.demo;

import android.os.Bundle;
import android.os.Handler;

import com.thl.mvp.mvp.StateActivity;

public class MainActivity extends StateActivity {


    @Override
    public void initData(Bundle savedInstanceState) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showNetError();
            }
        },3000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main2;
    }

}
