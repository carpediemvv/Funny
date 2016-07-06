package com.carpediem.vv.funny;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MainFragment extends BaseFragment {

    @Override
    protected View initView() {
        View view=View.inflate(mActivity,R.layout.fragment_main,null);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.id_toolbar);

        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("App Title");
        // Sub Title
        toolbar.setSubtitle("Sub title");
        //Navigation Icon
        toolbar.setNavigationIcon(R.drawable.bottom_home_tab_bg);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add("Menu 1a").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Menu 1b").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(mActivity, "index is"+" && menu text is "+item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {
        super.initData();
    }
}
