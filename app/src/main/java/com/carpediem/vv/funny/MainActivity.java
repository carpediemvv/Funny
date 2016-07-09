package com.carpediem.vv.funny;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("App Title");
        // Sub Title
        toolbar.setSubtitle("Sub title");
        //Navigation Icon
        toolbar.setNavigationIcon(R.drawable.bottom_home_tab_bg);
        toolbar.inflateMenu(R.menu.test);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.action_publish:
                        Toast.makeText(MainActivity.this, "" + itemId, Toast.LENGTH_SHORT).show();
                    case R.id.action_pick_pic:
                        Toast.makeText(MainActivity.this, "" + itemId, Toast.LENGTH_SHORT).show();
                    case R.id.action_take_pic:
                        Toast.makeText(MainActivity.this, "" + itemId, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        // setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, new MainFragment(), "main_fragment").commit();

    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/
}
