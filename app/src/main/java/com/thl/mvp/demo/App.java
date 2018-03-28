package com.thl.mvp.demo;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.Bugly;
import com.thl.mvp.MvpApplication;


/**
 * Created on 2016/12/31.
 */

public class App extends MvpApplication {


    @Override
    protected void initConfig() throws Exception {
//        Bugly.init(this, Config.BUGLY_KEY, true);   //更新与崩溃统计初始化

    }

    @Override
    protected void initConfigThread() throws Exception {

    }

}
