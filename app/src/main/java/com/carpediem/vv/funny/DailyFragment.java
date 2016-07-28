package com.carpediem.vv.funny;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
        //初始化数据
        arrayList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            arrayList.add("这是第"+i+"条数据");
        }
        //listview
        listView = (ListView) view.findViewById(R.id.listview);
        initListView();
        //下拉刷新
       /* swiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateDate();
            }
        });*/
        return view;
    }

    @Override
    public void initData() {
      /*  arrayList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            arrayList.add("这是第"+i+"条数据");
        }*/
        //initListView();

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
        public int getItemViewType(int position) {
            if (position % 2 != 0) {
                // 奇数项返回0
                return 0;
            } else {
                // 偶数项返回0
                return 1;
            }

        }


        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(mActivity, R.layout.item_list, null);
                viewHolder.text_content = (TextView)convertView.findViewById(R.id.text_content);
                viewHolder.image_content= (ImageView) convertView.findViewById(R.id.image_content);
                viewHolder.button_like = (Button)convertView.findViewById(R.id.button_like);
                viewHolder.button_dislike = (Button)convertView.findViewById(R.id.button_dislike);
                viewHolder.button_share = (Button)convertView.findViewById(R.id.button_share);
                viewHolder.button_comment = (Button)convertView.findViewById(R.id.button_comment);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.text_content.setText(arrayList.get(position));

            return convertView;
        }
    }
    public final class ViewHolder {
        public TextView text_content;
        public ImageView image_content;
        public Button button_like;
        public Button button_dislike;
        public Button button_share;
        public Button button_comment;
    }
    /**
     * 下拉刷新
     */
    public void updateDate() {
        Toast.makeText(mActivity, "刷新", Toast.LENGTH_SHORT).show();

        swiperefresh.setRefreshing(false);
    }


}
