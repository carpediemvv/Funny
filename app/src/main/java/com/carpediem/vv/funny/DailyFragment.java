package com.carpediem.vv.funny;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.carpediem.vv.funny.Base.BasePager;

/**
 * Created by Administrator on 2016/6/28.
 */
public class DailyFragment extends BasePager {


    public SwipeRefreshLayout swiperefresh;

    public DailyFragment(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_daily, null);
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateDate();
            }
        });
        return view;
    }

    public void updateDate() {
        Toast.makeText(mActivity,"刷新",Toast.LENGTH_SHORT).show();

        swiperefresh.setRefreshing(false);
    }

    @Override
    public void initData() {
        super.initData();
    }

}
