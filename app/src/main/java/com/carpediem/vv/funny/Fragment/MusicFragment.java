package com.carpediem.vv.funny.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carpediem.vv.funny.Base.BaseFragment;
import com.carpediem.vv.funny.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/6/28.
 */
public class MusicFragment extends BaseFragment {


    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private ArrayList<String> mDatas;

    public static MusicFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MusicFragment fragment = new MusicFragment();
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
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_music, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
        initRecyclerView();
        return view;
    }



    private void initRecyclerView() {
        //设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        //设置adapter
        recyclerView.setAdapter( new MusicAdapter());
        //设置Item增加、移除动画
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
    }
    class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    mActivity).inflate(R.layout.item_music, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
