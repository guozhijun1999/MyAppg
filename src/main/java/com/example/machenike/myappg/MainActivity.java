package com.example.machenike.myappg;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.machenike.myappg.fragments.gankfragment.GankFragment;
import com.example.machenike.myappg.fragments.weichatfragment.WeiChatFragment;
import com.example.machenike.myappg.fragments.zhihufragment.ZhihuFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static MaterialSearchView mViewSearch;
    private MenuItem mMenuItem;
    private MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewSearch = findViewById(R.id.view_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mMenuItem = navigationView.getMenu().findItem(R.id.drawer_zhihu);
        mMenuItem.setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content,new ZhihuFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_settings);
        if (mMenuItem.getItemId() == R.id.drawer_zhihu){
            item.setVisible(true);
        }else {
            item.setVisible(false);
        }
        mViewSearch.setMenuItem(item);
        searchMenuItem=item;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        switch (id) {
            case R.id.drawer_zhihu:
                fragmentTransaction.replace(R.id.fl_content, new ZhihuFragment());
                searchMenuItem.setVisible(false);
                break;
            case R.id.drawer_wechat:
                fragmentTransaction.replace(R.id.fl_content, new WeiChatFragment());
                searchMenuItem.setVisible(true);
                break;
            case R.id.drawer_gank:
                fragmentTransaction.replace(R.id.fl_content,new GankFragment());
                searchMenuItem.setVisible(false);
                break;
            case R.id.drawer_data:
                searchMenuItem.setVisible(false);
                break;
            case R.id.drawer_vtex:
                searchMenuItem.setVisible(false);
                break;
            case R.id.drawer_like:
                searchMenuItem.setVisible(false);
                break;
            case R.id.drawer_setting:
                searchMenuItem.setVisible(false);
                break;
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
