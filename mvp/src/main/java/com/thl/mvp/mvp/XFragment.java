package com.thl.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.thl.mvp.kit.KnifeKit;

import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


public abstract class XFragment<P extends IPresent> extends SupportFragment implements IView<P> {

    protected VDelegate vDelegate;
    protected P p;
    protected Activity context;
    protected View rootView;
    protected LayoutInflater layoutInflater;


    private Unbinder unbinder;

    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isInitView = false;//是否与View建立起映射关系
    private boolean isFirstLoad = true;//是否是第一次加载数据

    protected ImmersionBar mImmersionBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        isInitView = true;
        lazyLoadData();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(getActivity());
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
        if (!isVisibleToUser && mImmersionBar != null)
            mImmersionBar.init();
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void lazyLoadData() {
        if (isFirstLoad) {
        } else {
        }
        if (!isFirstLoad || !isVisible || !isInitView) {
            return;
        }
        LazyData();
        isFirstLoad = false;
    }


    /**
     * 加载要显示的数据
     */
    protected void LazyData() {

    }


    protected View getRealRootView() {
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {

        }
        bindEvent();
        initData(savedInstanceState);
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }

    @Override
    public void onDestroy() {

        if (mImmersionBar != null)
            mImmersionBar.destroy();
        super.onDestroy();
    }


    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public P newP() {
        return null;
    }
}
