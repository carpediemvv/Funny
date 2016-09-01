package com.carpediem.vv.funny.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.carpediem.vv.funny.R;

import java.util.ArrayList;

import FunnyGIF.Comment;

/**
 * Created by Administrator on 2016/8/31.
 */
public class CommentActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Comment> arrayList=new ArrayList<>();
    private CommentAdapter commentAdapter;

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
        listView = (ListView) findViewById(R.id.comment_listview);
        TextView tvEmpty = (TextView) findViewById(R.id.tv_empty);
        listView.setEmptyView(tvEmpty);
        initListView();
        Button btCommit = (Button) findViewById(R.id.bt_commit);
        final EditText etComment = (EditText) findViewById(R.id.et_comment);
        assert btCommit != null;
        btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment = new Comment();
                String textComment = etComment.getText().toString().trim();
                if (textComment!="")
                comment.setContent(textComment);
                arrayList.add(comment);
                commentAdapter.notifyDataSetChanged();
                etComment.setText("");
            }
        });
    }

    private void initListView() {
        commentAdapter = new CommentAdapter();
        listView.setAdapter(commentAdapter);
    }
    class CommentAdapter extends BaseAdapter
    {

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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(CommentActivity.this, R.layout.item_comment_list, null);
                viewHolder.text_name = (TextView) convertView.findViewById(R.id.id_name);
                viewHolder.text_comment = (TextView) convertView.findViewById(R.id.tv_comment);
                viewHolder.image_phone = (ImageView) convertView.findViewById(R.id.iv_photo);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
           if (true) {

                viewHolder.text_name.setText(android.os.Build.MODEL);
                viewHolder.text_comment.setText(arrayList.get(position).getContent());


            }
            return convertView;
        }
    }
    public final class ViewHolder {
        public TextView text_name;
        public TextView text_comment;
        public ImageView image_phone;

    }
}
