package com.carpediem.vv.funny;

import android.app.Activity;
import android.view.View;

import com.carpediem.vv.funny.Base.BasePager;

/**
 * Created by Administrator on 2016/6/28.
 */
public class DailyFragment extends BasePager {


    public DailyFragment(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_daily, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
