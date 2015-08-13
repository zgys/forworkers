package com.diting.zgy.forworkers.atys;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Window;

import com.diting.zgy.forworkers.Config;
import com.diting.zgy.forworkers.R;
import com.diting.zgy.forworkers.adapter.TabAdapter;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class AtyMain extends FragmentActivity
{
    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    private TabAdapter mAdapter ;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_aty_main);

        initView();
    }

    private void initView()
    {
        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new FraOrderTake();
        Fragment mTab02 = new FraOrderCurrent();
        Fragment mTab03 = new FraOrderHistory();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabPageIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);
        mAdapter = new TabAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);

        mTabPageIndicator.setViewPager(mViewPager, 0);
        mTabPageIndicator.setCurrentItem(0);
    }



}
