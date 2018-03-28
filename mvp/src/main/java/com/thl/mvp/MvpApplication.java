package com.thl.mvp;

import android.app.Application;
import android.content.pm.ApplicationInfo;

import www.thl.com.utils.Utils;


public abstract class MvpApplication extends Application {
	public static boolean DEBUG = false;

	@Override
	public void onCreate() {
		super.onCreate();
		Utils.init(this);
		initDebug();
		//初始化配置
		try {
			initConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					initConfigThread();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}



	protected abstract void initConfig() throws Exception;

	protected abstract void initConfigThread() throws Exception;


	public static boolean isDebug() {
		return DEBUG;
	}

	private void initDebug() {
		try {
			ApplicationInfo info = getApplicationInfo();
			DEBUG = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		} catch (Exception e) {
			DEBUG = false;
		}
	}

}
