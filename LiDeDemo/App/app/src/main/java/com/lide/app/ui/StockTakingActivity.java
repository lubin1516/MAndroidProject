package com.lide.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lide.app.R;
import com.lide.app.adapter.ViewPagerAdapter;
import com.lide.app.config.Configs;
import com.lide.app.ui.fragment.FragmentBase;
import com.lide.app.ui.fragment.InventorySheetFragment;
import com.lide.app.ui.fragment.StockReadFragment;
import com.lide.app.ui.fragment.StockUpLoadFragment;
import com.lide.app.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author DaiJiCheng
 * @time 2016/7/19  9:10
 * @desc ${盘点控制}
 */
public class StockTakingActivity extends FragmentActivity {
    @BindView(R.id.vp_stock)
    NoScrollViewPager vp_stock;
    @BindView(R.id.tv_read_reading)
    TextView tv_read_reading;
    @BindView(R.id.iv_read_arror)
    ImageView iv_read_arror;
    @BindView(R.id.iv_read_cloud)
    ImageView iv_read_cloud ;

    private ViewPagerAdapter mAdapter;
    private List<FragmentBase> mFragments;

    private static final int READ_FRAGMENT = 0;
    private static final int UPOLAD_FRAGMENT = 1;
    private static final int SHEET_FRAGMENT = 2;
    private  boolean isLogin = true; //true表示已经登录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_tacking);
        ButterKnife.bind(this);
        initData();
        initView();
        showFragmet(READ_FRAGMENT);
        
    }

    private void initView() {
        iv_read_cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                    //已经登录
                   showFragmet(SHEET_FRAGMENT);
                    iv_read_cloud.setVisibility(View.INVISIBLE);

                }else{
                    //未登录
                    Toast.makeText(StockTakingActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new StockReadFragment() );
        mFragments.add(new StockUpLoadFragment());
        mFragments.add(new InventorySheetFragment());
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(),mFragments);
        vp_stock.setAdapter(mAdapter);

        Intent intent = getIntent();
        intent.getSerializableExtra(Configs.CheckTask);
    }
    //界面切换
    public void showFragmet(int FRAGMET) {
       switch(FRAGMET){
           case READ_FRAGMENT:
               vp_stock.setCurrentItem(Configs.READ_FRAGMENT);
               break;
           case UPOLAD_FRAGMENT:
               vp_stock.setCurrentItem(Configs.UPOLAD_FRAGMENT);
               break;
           case SHEET_FRAGMENT:
               vp_stock.setCurrentItem(Configs.SHEET_FRAGMENT);
               break;
       }
   }
    //设置头部文本内容
    public void setReadingText(String text){
        tv_read_reading.setText(text);
    }

}