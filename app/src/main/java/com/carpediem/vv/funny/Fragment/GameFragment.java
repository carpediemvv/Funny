package com.carpediem.vv.funny.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.carpediem.vv.funny.Base.BaseFragment;
import com.carpediem.vv.funny.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.BookAdapter;
import FunnyGIF.FunnyGif;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/6/28.
 */
public class GameFragment extends BaseFragment {


    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<String> mDatas = new ArrayList<String>();
    private BookAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler  = new Handler();

    List<FunnyGif> arrayList = new ArrayList<FunnyGif>();
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多
    private String lastTime;
    private int limit = 10;        // 每页的数据是10条
    private int curPage = 0;        // 当前页的编号，从0开始
    private int isLoadData;
    public static GameFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initData() {

        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
        super.initData();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_game, null);
       // toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_gif);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        initToolbar();
        initRecyclerView();
        initSwipeRefreshLayout();
        return view;
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        handler.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.clear();
                        getFirstDataFromBmob();
                    }
                }, 2);
            }
        });

    }

    private void getFirstDataFromBmob() {
        Log.e("bmob查询的数据", curPage + "：curPage");
        queryData(0, STATE_REFRESH);
        mAdapter.setData(arrayList);
        mAdapter.notifyDataSetChanged();
       // swipeRefreshLayout.setRefreshing(false);
    }



    private void initToolbar() {
       // toolbar.setTitle("四叶草");
    }

    private void initRecyclerView() {
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        //设置adapter
        mAdapter = new BookAdapter(mActivity,arrayList);
        recyclerView.setAdapter(mAdapter);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    /**
     * 分页获取数据
     *
     * @param page       页码
     * @param actionType ListView的操作类型（下拉刷新、上拉加载更多）
     */
    private void queryData(int page, final int actionType) {
        Log.e("bmob", "pageN:" + page + " limit:" + limit + " actionType:" + actionType);

        BmobQuery<FunnyGif> query = new BmobQuery<>();
        // 按时间降序查询
        query.order("-createdAt");

        // 如果是加载更多
        if (actionType == STATE_MORE) {
            Log.e("bmob查询的数据", "curPage:" + curPage + " limit:" + limit + " actionType:" + actionType);
            // 处理时间查询

            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          /*  try {
                date = sdf.parse(lastTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
            // 只查询小于等于最后一个item发表时间的数据
            //query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            // 跳过之前页数并去掉重复数据

            query.setSkip(curPage * limit);

            // query.setSkip(curPage * limit+1);

        } else {
            curPage = 0;
            query.setSkip(curPage);
        }
        // 设置每页数据个数
        query.setLimit(limit);
        // 查找数据
        query.findObjects(new FindListener<FunnyGif>() {
            @Override
            public void done(List<FunnyGif> list, BmobException e) {
                if (e == null) {
                    // Toast.makeText(mActivity, "查询成功共"+list.size()+"条数据", Toast.LENGTH_SHORT).show();
                    Log.e("bmob查询的数据", "查询成功共" + list.size() + "条数据");
                    if (actionType == STATE_MORE) {
                        isLoadData = 0;
                        if (list.size() == 0) {
                            Toast.makeText(mActivity, "没有更多数据了", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if (actionType == STATE_REFRESH) {
                        // 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
                        curPage = 0;
                        arrayList.clear();
                        // 获取最后时间
                        lastTime = list.get(list.size() - 1).getCreatedAt();
                    }

                    // 将本次查询的数据添加到arrayList中
                    arrayList=list;
                    for (FunnyGif fg : arrayList) {
                        Log.e("arrayList","arrayList：成功"+fg.getTextContent());
                    }
                    // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
                    curPage++;
                    Log.e("bmob查询的数据++", "curPage：" + curPage);

                } else if (actionType == STATE_MORE) {
                    Toast.makeText(mActivity, "没有更多数据了", Toast.LENGTH_SHORT).show();
                } else if (actionType == STATE_REFRESH) {
                    Toast.makeText(mActivity, "没有数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
