package com.example.administrator.gtbanner;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.banner_vp)
    ViewPager vp;
    private List<BannerItem> mList;
    private boolean unlimit = true;//是否无限
    private boolean auto = true;//是否自动翻页
    private int currentIndex = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int index = msg.what;
            vp.setCurrentItem(index);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }
    private void initData() {
        mList = new ArrayList<BannerItem>();
        mList.add(new BannerItem(0,"这是图一","www.baidu.com", BitmapFactory.decodeResource(getResources(),R.mipmap.banner1)));
        mList.add(new BannerItem(1,"这是图二","www.taobao.com", BitmapFactory.decodeResource(getResources(),R.mipmap.banner2)));
        mList.add(new BannerItem(2,"这是图三","www.tecent.com", BitmapFactory.decodeResource(getResources(),R.mipmap.banner3)));
    }
    private void initView() {
        BannerAdapter adapter = new BannerAdapter(this,mList,unlimit);
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(currentIndex);
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (auto){
                    try {
                        sleep(2000);
                        currentIndex++;
                        if(!unlimit){
                            if (currentIndex>=mList.size()){
                                currentIndex = 0;
                            }
                        }
                        handler.sendEmptyMessage(currentIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
