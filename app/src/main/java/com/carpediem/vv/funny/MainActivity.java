package com.carpediem.vv.funny;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import Utils.MyApplication;
import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    private SearchView mSearchView;
    private SearchView.SearchAutoComplete editText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private FragmentManager supportFragmentManager;
    private MainFragment fragmentMain;
    public MyApplication mMyApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyApplication = (MyApplication)getApplication();
        Bmob.initialize(this, "c4e9104738e2747a6c63855e7d2a9b7d");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        // App Logo
        // toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("四叶草");
        // Sub Title
        //toolbar.setSubtitle("Sub title");
        //Navigation Icon
        //toolbar.setNavigationIcon(R.drawable.bottom_home_tab_bg);
        // toolbar.inflateMenu(R.menu.test);
        setSupportActionBar(toolbar);
       /* toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.action_publish:
                        Toast.makeText(MainActivity.this, "" + itemId, Toast.LENGTH_SHORT).show();
                    case R.id.action_pick_pic:
                        Toast.makeText(MainActivity.this, "" + itemId, Toast.LENGTH_SHORT).show();
                    case R.id.menu_refresh:
                        Toast.makeText(MainActivity.this, "" + itemId, Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });*/

        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    private void init() {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentMain = (MainFragment) supportFragmentManager.findFragmentById(R.id.fl_main);
        fragmentTransaction.replace(R.id.fl_main, new MainFragment(), "main_fragment").commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        final MenuItem item = menu.findItem(R.id.ab_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);


        //获取了SearchView，我们就能设置其相应的属性，一开始就处于显示SearchView的状态
        //mSearchView.setIconified(false);
        //不想让它隐藏SearchView
        //mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "提交查询", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "数据变化", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        editText = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_publish) {
            Toast.makeText(MainActivity.this, "投稿功能正在开发", Toast.LENGTH_SHORT).show();

        }
      /*  if (id == R.id.action_pick_pic) {
            Toast.makeText(MainActivity.this, "action_pick_pic", Toast.LENGTH_SHORT).show();

        }*/
        if (id == R.id.menu_refresh) {
            Toast.makeText(MainActivity.this, "分享功能正在开发", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }



}
