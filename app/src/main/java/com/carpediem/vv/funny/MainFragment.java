package com.carpediem.vv.funny;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carpediem.vv.funny.Base.BaseFragment;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MainFragment extends BaseFragment {
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
