package com.carpediem.vv.funny;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.carpediem.vv.funny.Base.BasePager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/28.
 */
public class DailyFragment extends BasePager {


    public SwipeRefreshLayout swiperefresh;
    private ListView listView;
    private ArrayList<String> arrayList;

    public DailyFragment(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_daily, null);
        //listview
        listView = (ListView) view.findViewById(R.id.listview);

        //下拉刷新
        swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateDate();
            }
        });
        return view;
    }

    /**
     * listView
     */
    private void initListView() {
        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);

    }

    class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.item_list, null);
            }
            view = convertView;
            TextView text = (TextView) view.findViewById(R.id.text);
            text.setText(arrayList.get(position));
            return view;
        }
    }

    /**
     * 下拉刷新
     */
    public void updateDate() {
        Toast.makeText(mActivity, "刷新", Toast.LENGTH_SHORT).show();

        swiperefresh.setRefreshing(false);
    }

    @Override
    public void initData() {
        arrayList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            arrayList.add("这是第"+i+"条数据");
        }
        initListView();
        super.initData();
    }

}
