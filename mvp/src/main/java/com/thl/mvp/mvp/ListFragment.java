package com.thl.mvp.mvp;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.thl.mvp.R;
import com.thl.mvp.base.SimpleRecAdapter;


public abstract class ListFragment<P extends IPresent> extends StateFragment<P>{

    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView mRecyclerView;
    private SimpleRecAdapter adapter;

    protected static final int MAX_PAGE = 10;

    @Override
    public void bindUI(View mView) {
        super.bindUI(mView);
        refreshLayout = (TwinklingRefreshLayout) mView.findViewById(R.id.refresh);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        ProgressLayout headerView = new ProgressLayout(getActivity());
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setAutoLoadMore(true);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                refresh(refreshLayout);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                loadMore(refreshLayout);
            }
        });
        initTwinklingRefreshLayout(refreshLayout);
        initRecyclerView(mRecyclerView);
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            mRecyclerView.setLayoutManager(layoutManager);
        }
        adapter = initAdapter();
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected Object getStateView() {
        return getRealRootView().findViewById(R.id.refresh);
    }

    protected void initRecyclerView(RecyclerView mRecyclerView) {

    }

    protected void initTwinklingRefreshLayout(TwinklingRefreshLayout refreshLayout) {

    }

    public SimpleRecAdapter getAdapter() {
        return adapter;
    }

    @Override
    protected boolean isCheckNet() {
        return true;
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract SimpleRecAdapter initAdapter();

    protected  void loadMore(TwinklingRefreshLayout refreshLayout){
        loadNetData();
    }

    protected  void refresh(TwinklingRefreshLayout refreshLayout){}

    protected void finishRefreshing() {
        if (refreshLayout != null) {
            refreshLayout.finishRefreshing();
        }
    }

    protected void finishLoadmore() {
        if (refreshLayout != null) {
            refreshLayout.finishLoadmore();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

}
