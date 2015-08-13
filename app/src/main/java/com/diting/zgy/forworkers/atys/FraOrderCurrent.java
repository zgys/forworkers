package com.diting.zgy.forworkers.atys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diting.zgy.forworkers.R;

/**
 * Created by Administrator on 2015/8/6.
 */
public class FraOrderCurrent extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.tab_order_current, container, false);
    }
}