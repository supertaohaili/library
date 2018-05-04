package com.thl.mvp.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.gyf.barlibrary.ImmersionBar;
import com.thl.mvp.kit.KnifeKit;

import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import www.thl.com.utils.KeyboardUtils;

/**
 * Created on 2016/12/29.
 */

public abstract class XActivity<P extends IPresent> extends SupportActivity implements IView<P> {

    private VDelegate vDelegate;
    private P p;
    protected Activity context;

    private Unbinder unbinder;
    protected ImmersionBar mImmersionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
            bindEvent();
        }
        if (isImmersionBarEnabled())
            initImmersionBar();
        initData(savedInstanceState);

    }

    protected void initImmersionBar() {
        if (mImmersionBar == null) {
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.init();
        }
    }

    protected boolean isImmersionBarEnabled() {
        return false;
    }


    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this);
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
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //在BaseActivity里销毁
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void bindEvent() {

    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public P newP() {
        return null;
    }


    @Override
    public void finish() {
        super.finish();
        KeyboardUtils.hideSoftInput(this);
    }

    //region软键盘的处理
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(), ev)) return super.dispatchTouchEvent(ev);
            if (hideSoftByEditViewIds() == null || hideSoftByEditViewIds().length == 0)
                return super.dispatchTouchEvent(ev);
            View v = getCurrentFocus();
            if (isFocusEditText(v, hideSoftByEditViewIds())) {
                if (isTouchView(hideSoftByEditViewIds(), ev))
                    return super.dispatchTouchEvent(ev);
                //隐藏键盘
                KeyboardUtils.hideSoftInput(this);
                clearViewFocus(v, hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 清除editText的焦点
     *
     * @param v   焦点所在View
     * @param ids 输入框
     */
    protected void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }

    /**
     * 隐藏键盘
     *
     * @param v   焦点所在View
     * @param ids 输入框
     * @return true代表焦点在edit上
     */
    protected boolean isFocusEditText(View v, int... ids) {
        if (v instanceof EditText) {
            EditText tmp_et = (EditText) v;
            for (int id : ids) {
                if (tmp_et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    //是否触摸在指定view上面,对某个控件过滤
    protected boolean isTouchView(View[] views, MotionEvent ev) {
        if (views == null || views.length == 0) return false;
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    //是否触摸在指定view上面,对某个控件过滤
    protected boolean isTouchView(int[] ids, MotionEvent ev) {
        int[] location = new int[2];
        for (int id : ids) {
            View view = findViewById(id);
            if (view == null) continue;
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth())
                    && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     *
     * @return id 数组
     */
    protected int[] hideSoftByEditViewIds() {
        return null;
    }

    /**
     * 传入要过滤的View
     * 过滤之后点击将不会有隐藏软键盘的操作
     *
     * @return id 数组
     */
    protected View[] filterViewByIds() {
        return null;
    }
}
