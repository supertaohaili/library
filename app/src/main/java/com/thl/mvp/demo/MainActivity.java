package com.thl.mvp.demo;

import android.support.v4.app.Fragment;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.thl.mvp.mvp.BottomMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BottomMenuActivity {


    @Override
    protected void initBottomMenu(ArrayList<AHBottomNavigationItem> bottomNavigationItems, List<Fragment> fragmentList) {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("首页", R.drawable.sel_home);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("本地", R.drawable.sel_home);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("设置", R.drawable.sel_home);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);

        fragmentList.add(new DemoTabFragment());
        fragmentList.add(new DemoFragment());
        fragmentList.add(new DemoFragment());
    }


    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }
}
