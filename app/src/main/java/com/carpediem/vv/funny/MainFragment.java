package com.carpediem.vv.funny;

import android.view.View;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MainFragment extends BaseFragment {

    @Override
    protected View initView() {
        View view=View.inflate(mActivity,R.layout.fragment_main,null);
        return view;
    }
}
