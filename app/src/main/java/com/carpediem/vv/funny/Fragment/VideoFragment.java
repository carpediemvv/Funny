package com.carpediem.vv.funny.Fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.carpediem.vv.funny.Adapter.StaggeredHomeAdapter;
import com.carpediem.vv.funny.Base.BaseFragment;
import com.carpediem.vv.funny.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class VideoFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredHomeAdapter mStaggeredHomeAdapter;


    public static VideoFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
        super.initData();
    }


    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_video, null);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview);
        mStaggeredHomeAdapter = new StaggeredHomeAdapter(mActivity, mDatas);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initEvent();
        return view;
    }
    private void initEvent()
    {
        mStaggeredHomeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(mActivity,
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(mActivity,
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
