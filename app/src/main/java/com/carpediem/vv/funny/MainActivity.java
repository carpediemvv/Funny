package com.carpediem.vv.funny;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.carpediem.vv.funny.Base.BaseFragment;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    private SearchView mSearchView;
    private SearchView.SearchAutoComplete editText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private FragmentManager supportFragmentManager  = getSupportFragmentManager();

    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "c4e9104738e2747a6c63855e7d2a9b7d");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // App Logo
        // toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("四叶草");
        // Sub Title
        //toolbar.setSubtitle("Sub title");
        //Navigation Icon
        //toolbar.setNavigationIcon(R.drawable.bottom_home_tab_bg);
         toolbar.inflateMenu(R.menu.test);
       // setSupportActionBar(toolbar);
       toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.action_publish:
                        Toast.makeText(MainActivity.this, "正在开发" + itemId, Toast.LENGTH_SHORT).show();
                    case R.id.menu_refresh:
                        Toast.makeText(MainActivity.this, "正在开发" + itemId, Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    private void init() {

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "每日").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "书籍").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "音乐").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "视频").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "鱼乐").setActiveColorResource(R.color.colorPrimary))
                .setMode(BottomNavigationBar.MODE_SHIFTING)//设置底部代文字显示模式。MODE_DEFAULT默认MODE_FIXED代文字MODE_SHIFTING不带文字
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)//背景模式BACKGROUND_STYLE_RIPPLE涟漪BACKGROUND_STYLE_STATIC静态
                .initialise();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {



            //当前的选中的tab
            @Override
            public void onTabSelected(int position) {
                Log.i("tab", "onTabSelected position:" + position);
                switch (position) {
                    case 0:

                        fragmentTransaction = supportFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment, new DailyFragment(), "daily_fragment").commit();
                        break;
                    case 1:

                        fragmentTransaction = supportFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment, new BooksFragment(), "main_fragment").commit();
                        break;
                    case 2:
                         fragmentTransaction = supportFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment, new MusicFragment(), "main_5fragment").commit();
                        break;
                    case 3:
                         fragmentTransaction = supportFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment, new VideoFragment(), "m2ain_fragment").commit();
                        break;
                    case 4:
                         fragmentTransaction = supportFragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.main_fragment, new GameFragment(), "main_8fragment").commit();
                        break;

                }

            }

            //上一个选中的tab
            @Override
            public void onTabUnselected(int position) {
                Log.i("tab", "onTabUnselected position:" + position);

            }

            //当前tab被重新选中
            @Override
            public void onTabReselected(int position) {
                Log.i("tab", "onTabReselected position:" + position);

            }
        });
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fragment, getFragments().get(0));
        transaction.commit();
    }
    private ArrayList<BaseFragment> getFragments() {

        fragments.add(new DailyFragment());
        fragments.add(new BooksFragment());
        fragments.add(new MusicFragment());
        fragments.add(new VideoFragment());
        fragments.add(new GameFragment());
        return fragments;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        final MenuItem item = menu.findItem(R.id.ab_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);


        //获取了SearchView，我们就能设置其相应的属性，一开始就处于显示SearchView的状态
       // mSearchView.setIconified(false);
        //不想让它隐藏SearchView
       // mSearchView.setIconifiedByDefault(false);
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

        if (id == R.id.menu_refresh) {
            Toast.makeText(MainActivity.this, "分享功能正在开发", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }


}
