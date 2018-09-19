package com.assionhonty.lib.assninegridview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;

/**
 * @author assionhonty
 * Created on 2018/9/19 9:10.
 * Email：assionhonty@126.com
 * Function:
 */
public class AssNineGridViewWrapper extends AppCompatImageView {

    /***显示更多的数量*/
    private int moreNum = 0;
    /***默认的遮盖颜色*/
    private int maskColor = 0x88000000;
    /***显示文字的大小单位sp*/
    private float textSize = 35;
    /***显示文字的颜色*/
    private int textColor = 0xFFFFFFFF;
    /***文字的画笔*/
    private TextPaint textPaint;
    /***要绘制的文字*/
    private String msg = "";

    public AssNineGridViewWrapper(Context context) {
        this(context, null);
    }

    public AssNineGridViewWrapper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AssNineGridViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //转化单位
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, getContext().getResources().getDisplayMetrics());

        textPaint = new TextPaint();
        //文字居中对齐
        textPaint.setTextAlign(Paint.Align.CENTER);
        //抗锯齿
        textPaint.setAntiAlias(true);
        //设置文字大小
        textPaint.setTextSize(textSize);
        //设置文字颜色
        textPaint.setColor(textColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (moreNum > 0) {
            canvas.drawColor(maskColor);
            float baseY = getHeight() / 2 - (textPaint.ascent() + textPaint.descent()) / 2;
            canvas.drawText(msg, getWidth() / 2, baseY, textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Drawable drawableUp = getDrawable();
                if (drawableUp != null) {
                    drawableUp.clearColorFilter();
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                break;
                default:
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }

    public int getMoreNum() {
        return moreNum;
    }

    public void setMoreNum(int moreNum) {
        this.moreNum = moreNum;
        msg = "+" + moreNum;
        invalidate();
    }

    public int getMaskColor() {
        return maskColor;
    }

    public void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
        invalidate();
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(textSize);
        invalidate();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        textPaint.setColor(textColor);
        invalidate();
    }

}
