package com.carpediem.vv.funny.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.carpediem.vv.funny.R;

/**
 * Created by Administrator on 2016/8/31.
 */
public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_activity);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.comment_recyclerView);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置adapter
        CommentAdapter commentAdapter = new CommentAdapter();

        recyclerView.setAdapter(commentAdapter);
    }
    class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
