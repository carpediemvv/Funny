package com.carpediem.vv.funny;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.carpediem.vv.funny.Base.BasePager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import FunnyGIF.FunnyGif;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/6/28.
 */
public class DailyFragment extends BasePager {


    public SwipeRefreshLayout swiperefresh;
    private ListView listView;
    private ArrayList<String> arrayList;
    private Handler handler = new Handler();
    private ListViewAdapter listViewAdapter;
    private int lastItem;
    List<FunnyGif> bankCards = new ArrayList<FunnyGif>();
    private String lastTime;

    public DailyFragment(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_daily, null);

        //listview
        listView = (ListView) view.findViewById(R.id.listview);
        listViewAdapter = new ListViewAdapter();
        listView.setAdapter(listViewAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (lastItem == (listView.getCount()-1)&& scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        Toast.makeText(mActivity, "马上加载更多", Toast.LENGTH_SHORT).show();
                        Log.e("listview",""+listView.getCount());
                        loadData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount - 1;
                Log.e("listview",""+lastItem);
                Log.e("getCount",""+listView.getCount());
            }
        });
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

    private void loadData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("listViewAdapter",listViewAdapter.getCount()+"");
                int count = listViewAdapter.getCount();
                Toast.makeText(mActivity, "加载新数据的个数"+count, Toast.LENGTH_SHORT).show();
                for (int i = count; i < count + 10; i++) {
                    arrayList.add("这是第加载更多的" + i + "条数据");
                }

                listViewAdapter.notifyDataSetChanged();
            }
        }, 5000);

    }

    @Override
    public void initData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("这是第" + i + "条数据");
        }
    }


    class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
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
                viewHolder.text_content = (TextView) convertView.findViewById(R.id.text_content);
                viewHolder.image_content = (ImageView) convertView.findViewById(R.id.image_content);
                viewHolder.button_like = (Button) convertView.findViewById(R.id.button_like);
                viewHolder.button_dislike = (Button) convertView.findViewById(R.id.button_dislike);
                viewHolder.button_share = (Button) convertView.findViewById(R.id.button_share);
                viewHolder.button_comment = (Button) convertView.findViewById(R.id.button_comment);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (arrayList != null) {
                viewHolder.text_content.setText(arrayList.get(position));
            }


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
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多

    private int limit = 10;		// 每页的数据是10条
    private int curPage = 0;		// 当前页的编号，从0开始
    /**
     * 下拉刷新
     */
    public void updateDate() {
        Toast.makeText(mActivity, "刷新", Toast.LENGTH_SHORT).show();
        queryData(0, STATE_REFRESH);
        swiperefresh.setRefreshing(false);
    }

    /**
     * 分页获取数据
     * @param page	页码
     * @param actionType	ListView的操作类型（下拉刷新、上拉加载更多）
     */
    private void queryData(int page, final int actionType){
        Log.i("bmob", "pageN:"+page+" limit:"+limit+" actionType:"+actionType);

        BmobQuery<FunnyGif> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");
        // 如果是加载更多
        int count=10;
        if(actionType == STATE_MORE){
            // 处理时间查询
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(lastTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 只查询小于等于最后一个item发表时间的数据
            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            // 跳过之前页数并去掉重复数据
            query.setSkip(page * count+1);
        }else{
            page=0;
            query.setSkip(page);
        }
        // 设置每页数据个数
        query.setLimit(count);
        // 查找数据
        query.findObjects(new FindListener<FunnyGif>() {
            @Override
            public void done(List<FunnyGif> list, BmobException e) {
                if(e==null){
                    Toast.makeText(mActivity, "查询成功共"+list.size()+"条数据", Toast.LENGTH_SHORT).show();
                    for (FunnyGif fg : list) {
                        Log.e("bmob查询的数据","数据："+fg.getTextContent());
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }


}
