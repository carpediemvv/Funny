package com.carpediem.vv.funny.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.carpediem.vv.funny.Base.BaseFragment;
import com.carpediem.vv.funny.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.BookAdapter;
import bean.BookBean.Book;
import bean.FunnyGIF.FunnyGif;
import cn.bmob.v3.BmobQuery;

/**
 * Created by Administrator on 2016/6/28.
 */
public class BooksFragment extends BaseFragment {


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


    public static BooksFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        BooksFragment fragment = new BooksFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initData() {
        BmobQuery<Book> query = new BmobQuery<Book>();


    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_books, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_gif);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        initRecyclerView();
        initSwipeRefreshLayout();
        return view;
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.post(new Runnable() {
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

                    }
                }, 2);
            }
        });

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


}
