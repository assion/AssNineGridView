package com.assionhonty.lib.assninegridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author assionhonty
 * Created on 2018/9/19 8:34.
 * Email：assionhonty@126.com
 * Function:九宫格控件
 */
public class AssNineGridView extends ViewGroup {

    /***全局的图片加载器(初始化设置,否者不显示图片)*/
    private static ImageLoader mImAgeLoader;
    /***单张图片的宽高比(宽/高)*/
    private float singleImgRatio = 1.0f;
    /***填最大显示的图片数*/
    private int maxImgSize = 9;
    /***宫格间距，单位dp*/
    private int gridSpace = 3;
    /***列数*/
    private int columnCount;
    /***行数*/
    private int rowCount;
    /***宫格宽度*/
    private int gridWidth;
    /***宫格高度*/

    private int gridHeight;

    private List<ImageView> imageViews;
    private List<ImageInfo> mImageInfo;
    private AssNineGridViewAdapter mAdapter;

    public AssNineGridView(Context context) {
        this(context, null);
    }

    public AssNineGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AssNineGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        gridSpace = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, gridSpace, dm);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AssNineGridView);
        gridSpace = (int) a.getDimension(R.styleable.AssNineGridView_angv_gridSpace, gridSpace);
        singleImgRatio = a.getFloat(R.styleable.AssNineGridView_angv_singleImgRatio, singleImgRatio);
        maxImgSize = a.getInt(R.styleable.AssNineGridView_angv_maxSize, maxImgSize);
        a.recycle();

        imageViews = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        int totalWidth = width - getPaddingLeft() - getPaddingRight();
        if (mImageInfo != null && mImageInfo.size() > 0) {
            if (mImageInfo.size() == 1) {
                gridWidth = totalWidth;
                gridHeight = (int) (gridWidth / singleImgRatio);
                //图片显示区域大小，不允许超过最大显示范围(300)
                if (gridHeight > totalWidth) {
                    float ratio = totalWidth * 1.0f / gridHeight;
                    gridWidth = (int) (gridWidth * ratio);
                    gridHeight = totalWidth;
                }
            } else if (mImageInfo.size() == 2) {
                // 宫格宽度
                gridWidth = gridHeight = (totalWidth - gridSpace) / 2;
            }else {
                gridWidth = gridHeight = (totalWidth - gridSpace * (columnCount - 1)) / columnCount;
            }
            width = gridWidth * columnCount + gridSpace * (columnCount - 1) + getPaddingLeft() + getPaddingRight();
            height = gridHeight * rowCount + gridSpace * (rowCount - 1) + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mImageInfo == null) {
            return;
        }
        int childrenCount = mImageInfo.size();
        for (int i = 0; i < childrenCount; i++) {
            ImageView childrenView = (ImageView) getChildAt(i);

            int rowNum = i / columnCount;
            int columnNum = i % columnCount;
            int left = (gridWidth + gridSpace) * columnNum + getPaddingLeft();
            int top = (gridHeight + gridSpace) * rowNum + getPaddingTop();
            int right = left + gridWidth;
            int bottom = top + gridHeight;
            childrenView.layout(left, top, right, bottom);

            if (mImAgeLoader != null) {
                mImAgeLoader.onDisplayImage(getContext(), childrenView, mImageInfo.get(i).thumbnailUrl);
            }
        }
    }

    /**
     * 设置适配器
     */
    public void setAdapter(@NonNull AssNineGridViewAdapter adapter) {
        mAdapter = adapter;
        List<ImageInfo> imageInfo = adapter.getImageInfo();

        if (imageInfo == null || imageInfo.isEmpty()) {
            setVisibility(GONE);
            return;
        } else {
            setVisibility(VISIBLE);
        }

        int imageCount = imageInfo.size();
        if (maxImgSize > 0 && imageCount > maxImgSize) {
            imageInfo = imageInfo.subList(0, maxImgSize);
            //再次获取图片数量
            imageCount = imageInfo.size();
        }

        //默认是3列显示，行数根据图片的数量决定
        rowCount = imageCount / 3 + (imageCount % 3 == 0 ? 0 : 1);
        columnCount = 3;
        if (imageCount == 4) {
            rowCount = 2;
            columnCount = 2;
        }


        //保证View的复用，避免重复创建
        if (mImageInfo == null) {
            for (int i = 0; i < imageCount; i++) {
                ImageView iv = getImageView(i);
                if (iv == null) {
                    return;
                }
                addView(iv, generateDefaultLayoutParams());
            }
        } else {
            int oldViewCount = mImageInfo.size();
            int newViewCount = imageCount;
            if (oldViewCount > newViewCount) {
                removeViews(newViewCount, oldViewCount - newViewCount);
            } else if (oldViewCount < newViewCount) {
                for (int i = oldViewCount; i < newViewCount; i++) {
                    ImageView iv = getImageView(i);
                    if (iv == null) {
                        return;
                    }
                    addView(iv, generateDefaultLayoutParams());
                }
            }
        }
        //修改最后一个条目，决定是否显示更多
        if (adapter.getImageInfo().size() > maxImgSize) {
            View child = getChildAt(maxImgSize - 1);
            if (child instanceof AssNineGridViewWrapper) {
                AssNineGridViewWrapper imageView = (AssNineGridViewWrapper) child;
                imageView.setMoreNum(adapter.getImageInfo().size() - maxImgSize);
            }
        }
        mImageInfo = imageInfo;
        requestLayout();
    }

    /**
     * 获得 ImageView 保证了 ImageView 的重用
     */
    private ImageView getImageView(final int position) {
        ImageView imageView;
        if (position < imageViews.size()) {
            imageView = imageViews.get(position);
        } else {
            imageView = mAdapter.generateImageView(getContext());
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.onImageItemClick(getContext(), AssNineGridView.this, position, mAdapter.getImageInfo());
                }
            });
            imageViews.add(imageView);
        }
        return imageView;
    }

    /**
     * 设置宫格间距
     */
    public void setgridSpace(int spacing) {
        gridSpace = spacing;
    }

    /**
     * 设置只有一张图片时的宽高比
     */
    public void setsingleImgRatio(float ratio) {
        singleImgRatio = ratio;
    }

    /**
     * 设置最大图片数
     */
    public void setMaxSize(int maxSize) {
        maxImgSize = maxSize;
    }

    public int getMaxSize() {
        return maxImgSize;
    }

    public static void setImageLoader(ImageLoader imageLoader) {
        mImAgeLoader = imageLoader;
    }

    public static ImageLoader getImageLoader() {
        return mImAgeLoader;
    }

    public interface ImageLoader {
        /**
         * 需要子类实现该方法，以确定如何加载和显示图片
         *
         * @param context   上下文
         * @param imageView 需要展示图片的ImageView
         * @param url       图片地址
         */
        void onDisplayImage(Context context, ImageView imageView, String url);

        /**
         * @param url 图片的地址
         * @return 当前框架的本地缓存图片
         */
        Bitmap getCacheImage(String url);
    }
}
