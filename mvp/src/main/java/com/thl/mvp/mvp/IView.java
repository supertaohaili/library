package com.thl.mvp.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * Created on 2016/12/29.
 */

public interface IView<P> {

    public int getLayoutId();

    public void bindUI(View rootView);

    public void bindEvent();

    public void initData(Bundle savedInstanceState);

    public int getOptionsMenuId();

    public boolean useEventBus();

    public P newP();
}
