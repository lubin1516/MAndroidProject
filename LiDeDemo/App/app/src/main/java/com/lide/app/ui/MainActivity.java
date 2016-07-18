package com.lide.app.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.service.ScanService;
import com.lide.app.ui.fragment.ChannelFragment;
import com.lide.app.ui.fragment.CheckTaskFragment;
import com.lide.app.ui.fragment.FragmentBase;
import com.lide.app.ui.fragment.KillTagFragment;
import com.lide.app.ui.fragment.ReadTagFragment;
import com.lide.app.ui.fragment.SearchTagFragment;
import com.lide.app.ui.fragment.SetFragment;
import com.lide.app.ui.fragment.WriteTagFragment;
import com.lide.app.view.NoScrollViewPager;
import com.rscja.deviceapi.RFIDWithUHF;
import com.rscja.utility.StringUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tb_main)
    Toolbar tbMain;
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;
    @BindView(R.id.lf_main)
    NavigationView lfMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;

    private List<FragmentBase> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    public RFIDWithUHF mReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        startUHF();
        initViews();
        intiEvent();
        initService();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mReader != null) {
            mReader.free();
        }
        stopService(new Intent(this,ScanService.class));
        super.onDestroy();
    }

    private void startUHF() {
        try {
            mReader = RFIDWithUHF.getInstance();
        } catch (Exception ex) {

            Toast.makeText(MainActivity.this, ex.getMessage(),
                    Toast.LENGTH_SHORT).show();

            return;
        }

        if (mReader != null) {
            new InitTask().execute();
        }
    }

    private void initViews() {

        setSupportActionBar(tbMain);

        setNacigationItemSelectedListener();
        fragments = new ArrayList<>();
        fragments.add(new SearchTagFragment());
        fragments.add(new WriteTagFragment());
        fragments.add(new ReadTagFragment());
        fragments.add(new KillTagFragment());
        fragments.add(new SetFragment());
        fragments.add(new CheckTaskFragment());
        //主功能选择界面
        fragments.add(new ChannelFragment());
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(viewPagerAdapter);
    }

    private void intiEvent() {
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeOrOpenDrawer();
            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, dlMain, tbMain,
                R.string.isOpen, R.string.isClose);
        mDrawerToggle.syncState();//初始化状态
        dlMain.setDrawerListener(mDrawerToggle);
    }


    private void initService() {
        startService(new Intent(this, ScanService.class));
    }


    @Override
    public void onBackPressed() {
        if (dlMain.isDrawerOpen(GravityCompat.START)) {
            closeOrOpenDrawer();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void closeOrOpenDrawer() {
        if (dlMain.isDrawerOpen(GravityCompat.START)) {
            dlMain.closeDrawer(GravityCompat.START);
        } else {
            dlMain.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 设备上电异步类
     *
     * @author liuruifeng
     */
    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            return mReader.init();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            mypDialog.cancel();

            if (!result) {
                Toast.makeText(MainActivity.this, "init fail",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            mypDialog = new ProgressDialog(MainActivity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("init...");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }

    }

    /**
     * 验证十六进制输入是否正确
     *
     * @param str
     * @return
     */
    public boolean vailHexInput(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        // 长度必须是偶数
        if (str.length() % 2 == 0) {
            return StringUtility.isHexNumberRex(str);
        }

        return false;
    }

    private void setNacigationItemSelectedListener() {
        lfMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_search:
                        vpMain.setCurrentItem(0);
                        closeOrOpenDrawer();
                        break;
                    case R.id.nav_write:
                        vpMain.setCurrentItem(1);
                        closeOrOpenDrawer();
                        break;
                    case R.id.nav_read:
                        vpMain.setCurrentItem(2);
                        closeOrOpenDrawer();
                        break;
                    case R.id.nav_kill:
                        vpMain.setCurrentItem(3);
                        closeOrOpenDrawer();
                        break;
                    case R.id.nav_set:
                        vpMain.setCurrentItem(4);
                        closeOrOpenDrawer();
                        break;
                    case R.id.nav_lock:
                        vpMain.setCurrentItem(5);
                        closeOrOpenDrawer();
                        break;
                    case R.id.nav_channel:
                        vpMain.setCurrentItem(6);
                        closeOrOpenDrawer();
                        break;

                }
                return true;
            }
        });
    }
        public void setTbTitle(String title){
            tbMain.setTitle(title);
        }
}