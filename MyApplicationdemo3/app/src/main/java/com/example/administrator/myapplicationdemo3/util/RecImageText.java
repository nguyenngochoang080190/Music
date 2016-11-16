package com.example.administrator.myapplicationdemo3.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myapplicationdemo3.R;

/**
 * Created by Administrator on 11/6/2016.
 */

public class RecImageText extends View implements View.OnTouchListener {
    Paint paintCirle, paintBitmap;
    int alpha = 100;
    Bitmap mBitmap;
    int radius;
    int centerX, centerY, width, height;
    int nomalColor, pressColor;
    int strokeWidth = 5;
    boolean checkBoolean = false;
    int i = 1;
    TypedArray padding;

    public RecImageText(Context context) {
        super(context);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecImageText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        getData(context, attrs);
        init();
    }

    public RecImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getData(context, attrs);
        init();
    }

    public RecImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        getData(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, centerX - mBitmap.getWidth() / 2, centerY - mBitmap.getHeight() / 2, paintBitmap);
        canvas.drawCircle(centerX, centerY, radius, paintCirle);
    }

    public void init() {
        paintCirle = new Paint();
        paintCirle.setColor(nomalColor);
        paintCirle.setStyle(Paint.Style.STROKE);
        paintCirle.setStrokeWidth(strokeWidth);
        paintCirle.setAlpha(alpha);
        paintCirle.setTextSize(20);
        paintBitmap = new Paint();
        paintBitmap.setStyle(Paint.Style.STROKE);
        paintBitmap.setStrokeWidth(strokeWidth);
        paintBitmap.setAlpha(255);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x1, y1;
        x1 = (int) event.getX();
        y1 = (int) event.getY();
        int d = (int) Math.sqrt((x1 - centerX) * (x1 - centerX) + (y1 - centerY) * (y1 - centerY));
        if (d <= radius + strokeWidth / 2) {
            if (event.getAction() == MotionEvent.ACTION_UP)
                paintCirle.setColor(nomalColor);
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                paintCirle.setColor(pressColor);
            paintCirle.setAlpha(alpha);
            setDrawable(getResources().getDrawable(R.mipmap.header_icon));
            postInvalidate();
        }
        return true;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        centerX = width / 2;
        centerY = height / 2;
        radius = (centerX > centerY ? centerY : centerX) - strokeWidth / 2;
        float s = (float) mBitmap.getWidth() / mBitmap.getHeight();
        if (s >= 1)
            mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) (2 * s * (radius + strokeWidth / 2)), 2 * (radius + strokeWidth / 2), false);
        else
            mBitmap = Bitmap.createScaledBitmap(mBitmap, 2 * (radius + strokeWidth / 2), (int) (2 * s * (radius + strokeWidth / 2)), false);
        mBitmap = getCirleBitmap(mBitmap);
        checkBoolean = true;
    }

    public void getData(Context context, AttributeSet attrs) {
        TypedArray src = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myCustom, 0, 0);
        TypedArray nomalColor = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myCustom, 0, 0);
        TypedArray pressColor = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myCustom, 0, 0);
        TypedArray strokeWidth = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myCustom, 0, 0);
        TypedArray alpha = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myCustom, 0, 0);
        padding = context.getTheme().obtainStyledAttributes(attrs, R.styleable.myCustom, 0, 0);
        try {
            Drawable mDrawable = src.getDrawable(R.styleable.myCustom_src);
            this.nomalColor = nomalColor.getColor(R.styleable.myCustom_nomalColor, context.getResources().getColor(R.color.colorAccent));
            this.pressColor = pressColor.getColor(R.styleable.myCustom_pressColor, context.getResources().getColor(R.color.colorPrimary));
            this.strokeWidth = strokeWidth.getInt(R.styleable.myCustom_strokeWidth, this.strokeWidth);
            this.alpha = alpha.getInt(R.styleable.myCustom_alpha_, this.alpha);
            if (mDrawable != null) {
                mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
            }
        } catch (Exception e) {
        } finally {
            nomalColor.recycle();
            pressColor.recycle();
            strokeWidth.recycle();
            alpha.recycle();
        }

    }

    public Bitmap getCirleBitmap(Bitmap bitmap) {
        int width, height, min;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        min = Math.min(width, height);
        Bitmap out = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(out);
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setStyle(Paint.Style.FILL);
        Rect rectDst = new Rect(0, 0, min, min);
        Rect rectSrc = new Rect((width - height) / 2, 0, (width + height) / 2, min);
        canvas.drawCircle(min / 2, min / 2,
                min / 2 - paint.getStrokeWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rectSrc, rectDst, paint);
        return out;
    }

    public void setDrawable(Drawable drawable) {
        mBitmap = ((BitmapDrawable) drawable).getBitmap();
        float s = (float) mBitmap.getWidth() / mBitmap.getHeight();
        radius = (centerX > centerY ? centerY : centerX) - strokeWidth / 2;
        if (radius<=0)
        {
            radius=Math.min(mBitmap.getWidth(),mBitmap.getHeight())/2 - strokeWidth / 2;;
        }
        else
        {
            if (s >= 1)
                mBitmap = Bitmap.createScaledBitmap(mBitmap,
                        (int) (2 * s * (radius + strokeWidth / 2)), 2 * (radius + strokeWidth / 2), false);
            else
                mBitmap = Bitmap.createScaledBitmap(mBitmap,
                        2 * (radius + strokeWidth / 2), (int) (2 * s * (radius + strokeWidth / 2)), false);
        }
        mBitmap = getCirleBitmap(mBitmap);
        postInvalidate();
    }
}
