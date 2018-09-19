package com.assionhonty.assninegridview;

import android.app.Application;

import com.assionhonty.lib.assninegridview.AssNineGridView;

/**
 * @author assionhonty
 * Created on 2018/9/19 14:44.
 * Email：assionhonty@126.com
 * Function:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AssNineGridView.setImageLoader(new GlideImageLoader());
    }
}
