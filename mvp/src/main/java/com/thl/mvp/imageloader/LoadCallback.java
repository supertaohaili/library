package com.thl.mvp.imageloader;

import android.graphics.Bitmap;

/**
 * Created on 2016/12/21.
 */

public abstract class LoadCallback {
    void onLoadFailed(Throwable e) {}

    public abstract void onLoadReady(Bitmap bitmap);

    void onLoadCanceled() {}
}
