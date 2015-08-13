package com.diting.zgy.forworkers.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter
{

    public List<Fragment> mFragments;

	public static String[] TITLES = new String[]
	{ "可接订单", "当前订单", "历史订单" };

	public TabAdapter(FragmentManager fm, List<Fragment> mFragments)
	{
		super(fm);
        this.mFragments = mFragments;
	}

	@Override
	public Fragment getItem(int arg0)
	{
		return mFragments.get(arg0);
	}

	@Override
	public int getCount()
	{
		return mFragments == null ? 0 : mFragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return TITLES[position];
	}

}
