package com.example.administrator.gtbanner;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2018/11/7.
 */

public class BannerAdapter extends PagerAdapter{
    private Context mContext;
    private boolean unlimit;
    private List<BannerItem> mList;
    public BannerAdapter(Context mContext,List<BannerItem> mList,boolean unlimit){
        this.mContext = mContext;
        this.mList = mList;
        this.unlimit = unlimit;
    }
    @Override
    public int getCount() {
        if(unlimit){
            return Integer.MAX_VALUE;
        }else {
            return mList==null?0:mList.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object?true:false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(unlimit){
            position = position%mList.size();
        }
        final BannerItem item = mList.get(position);
        View view = View.inflate(mContext,R.layout.include_banner_view,null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150));
        ImageView img = view.findViewById(R.id.banner_img);
        TextView content = view.findViewById(R.id.banner_content);
        TextView index = view.findViewById(R.id.banner_index);
        img.setImageBitmap(item.getBitmap());
        String con = item.getContent();
        if(TextUtils.isEmpty(con)){
            content.setVisibility(View.GONE);
        }else{
            content.setVisibility(View.VISIBLE);
            content.setText(item.getContent());
        }
        String in = (position+1)+"/"+mList.size();
        index.setText(in);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,item.getUrl(),Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }
}
