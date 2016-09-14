package com.carpediem.vv.funny.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.carpediem.vv.funny.Base.BaseFragment;
import com.carpediem.vv.funny.R;

/**
 * Created by Administrator on 2016/6/28.
 */
public class GameFragment extends BaseFragment {

    public static GameFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_game, null);
        TextView textView = (TextView) view.findViewById(R.id.tv);
        Typeface asset = Typeface.createFromAsset(mActivity.getAssets(), "fonts/apple.ttf");
        textView.setTypeface(asset);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
