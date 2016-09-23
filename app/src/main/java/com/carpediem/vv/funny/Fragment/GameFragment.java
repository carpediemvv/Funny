package com.carpediem.vv.funny.Fragment;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.Menu;
import android.view.MenuInflater;

import com.carpediem.vv.funny.R;

/**
 * Created by Administrator on 2016/6/28.
 */
public class GameFragment extends PreferenceFragmentCompat {

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
        addPreferencesFromResource(R.xml.pref_general);
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
