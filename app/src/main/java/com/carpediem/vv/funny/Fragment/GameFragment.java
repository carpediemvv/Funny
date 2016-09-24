package com.carpediem.vv.funny.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_game, null);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
