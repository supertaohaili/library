package com.thl.mvp.mvp;

import android.view.View;

import cn.thl.view.statemanager.StateListener;
import cn.thl.view.statemanager.StateManager;
import www.thl.com.utils.NetworkUtils;

/**
 * Created by Administrator on 2018/3/23.
 */

public abstract class StateFragment<P extends IPresent> extends XLazyFragment<P> implements StateView {

    protected StateManager mStateManager;

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
        if (mStateManager==null) {
            mStateManager = StateManager.builder(getActivity())
                    .setContent(this.getStateView())//为哪部分内容添加状态管理。这里可以是Activity，Fragment或任何View。
                    .setErrorOnClickListener(getErrorListener())
                    .setNetErrorOnClickListener(getNetErrorListener())
                    .setEmptyOnClickListener(getEmptyListener())
                    .setConvertListener(getConvertListener())
                    .build();//构建
            if (!NetworkUtils.isNetworkConnected()) {
                showNetError();
                return;
            }
            showLoading();
            loadNetData();
        }
    }

    protected abstract Object getStateView();

    protected boolean isCheckNet() {
        return false;
    }


    protected StateListener.ConvertListener getConvertListener() {
        return null;
    }


    protected StateListener.OnClickListener getEmptyListener() {
        if (isDefaultLoad()) {
            return new StateListener.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!NetworkUtils.isNetworkConnected()) {
                        showNetError();
                        return;
                    }
                    showLoading();
                    loadNetData();
                }
            };
        } else {
            return null;
        }
    }

    protected StateListener.OnClickListener getNetErrorListener() {
        if (isDefaultLoad()) {
            return new StateListener.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!NetworkUtils.isNetworkConnected()) {
                        return;
                    }
                    showLoading();
                    loadNetData();
                }
            };
        } else {
            return null;
        }
    }

    protected StateListener.OnClickListener getErrorListener() {
        if (isDefaultLoad()) {
            return new StateListener.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!NetworkUtils.isNetworkConnected()) {
                        showNetError();
                        return;
                    }
                    showLoading();
                    loadNetData();
                }
            };
        } else {
            return null;
        }
    }

    private boolean isDefaultLoad() {
        return true;
    }


    protected void loadNetData() {

    }

    @Override
    public void showLoading() {
        if (mStateManager != null) {
            mStateManager.showLoading();
        }
    }

    @Override
    public void showError() {
        if (mStateManager != null) {
            mStateManager.showError();
        }
    }

    @Override
    public void showNetError() {
        if (mStateManager != null) {
            mStateManager.showNetError();
        }
    }

    @Override
    public void showContent() {
        if (mStateManager != null) {
            mStateManager.showContent();
        }
    }

    @Override
    public void showEmpty() {
        if (mStateManager != null) {
            mStateManager.showEmpty();
        }
    }

}
