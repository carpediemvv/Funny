package com.carpediem.vv.funny;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MainFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = View.inflate(mActivity, R.layout.fragment_main, null);

        return view;
    }

     @Override
            protected View initView() {
                View view=View.inflate(mActivity,R.layout.fragment_main,null);

                return view;
            }


    @Override
    public void initData() {
        super.initData();
    }
}
