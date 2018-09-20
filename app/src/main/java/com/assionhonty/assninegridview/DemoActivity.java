package com.assionhonty.assninegridview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assionhonty.lib.assninegridview.AssNineGridView;
import com.assionhonty.lib.assninegridview.AssNineGridViewAdapter;
import com.assionhonty.lib.assninegridview.AssNineGridViewClickAdapter;
import com.assionhonty.lib.assninegridview.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author assionhonty
 * Created on 2018.08.08.
 * Email：assionhonty@126.com
 * Function:demo
 */
public class DemoActivity extends AppCompatActivity {
    private List<DemoBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        //添加30个随机假数据
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            List<String> img = new ArrayList<>();
            int random = new Random().nextInt(UrlData.getImageLists().size()) + 1;
            for (int j = 0; j < random; j++) {
                img.add(UrlData.getImageLists().get(j));
            }
            DemoBean demoBean = new DemoBean();
            demoBean.setImages(img);
            mDatas.add(demoBean);
        }

        RecyclerView mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRv.setAdapter(new MyAdapter());

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(DemoActivity.this).inflate(R.layout.demo_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.tv.setText("条目 " + position);
            List<ImageInfo> imageInfos = getImageInfos(position);
            holder.angv.setAdapter(new AssNineGridViewClickAdapter(DemoActivity.this, imageInfos));

//            MyAssAdapter assAdapter = new MyAssAdapter(DemoActivity.this, imageInfos);
//            assAdapter.onImageItemClick(DemoActivity.this,  holder.angv, position, imageInfos);
//            holder.angv.setAdapter(assAdapter);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        private List<ImageInfo> getImageInfos(int position) {
            List<ImageInfo> imageInfos = new ArrayList<>();
            List<String> images = mDatas.get(position).getImages();
            for (String url : images) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBigImageUrl(url);
                imageInfo.setThumbnailUrl(url);
                imageInfos.add(imageInfo);
            }
            return imageInfos;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            AssNineGridView angv;
            TextView tv;

            MyViewHolder(View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
                angv = itemView.findViewById(R.id.angv);
            }
        }

        private class MyAssAdapter extends AssNineGridViewAdapter{
            private Context mContext;
             MyAssAdapter(Context context, List<ImageInfo> imageInfo) {
                super(context, imageInfo);
                 mContext = context;
            }

            @Override
            public void onImageItemClick(Context context, AssNineGridView angv, int index, List<ImageInfo> imageInfo) {
                super.onImageItemClick(context, angv, index, imageInfo);
                Toast.makeText(mContext, "条目"+index+":自定义点击效果", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
