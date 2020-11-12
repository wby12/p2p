package com.bw.p2pinvistment1802.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.bw.p2pinvistment1802.R;

public class CircleProgressView extends View {

    private int circle_color;//圆颜色
    private int arc_color;//进度条颜色
    private int text_color;//文字颜色
    private float text_size;//文字大小
    private float round_size;//圆半径大小
    private int circle_stroke;//圆边框宽度

    private int measuredWidth;//progressView宽度
    private int measuredHeight;//progressView高度
    private int progress;//占比
    private int offesAngle;
    private int startAngle = 0;
    private int stopAngle = 1;
    private int progressAngle;

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        circle_color = typedArray.getColor(R.styleable.CircleProgressView_circle_color, Color.BLUE);//圆颜色
        arc_color = typedArray.getColor(R.styleable.CircleProgressView_arc_color, Color.RED);//进度条颜色
        text_color = typedArray.getColor(R.styleable.CircleProgressView_text_color, Color.CYAN);//文字颜色
        text_size = typedArray.getDimension(R.styleable.CircleProgressView_text_size, 40.0f);//文字大小
        round_size = typedArray.getDimension(R.styleable.CircleProgressView_round_size, 200.0f);//圆半径大小
        circle_stroke = typedArray.getInteger(R.styleable.CircleProgressView_circle_stroke, 5);//圆边框宽度
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredHeight = getMeasuredHeight();
        measuredWidth = getMeasuredWidth();

        int widthSize = 0;
        int hightSize = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {

            widthSize = 500;
            measuredWidth = 500;
            round_size = 400;

        } else {
            widthSize = MeasureSpec.getSize(widthMeasureSpec);
        }

        int hightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (hightMode == MeasureSpec.AT_MOST) {
            hightSize = 500;
            measuredHeight = 500;
            round_size = 400;
            setMeasuredDimension(widthSize, hightSize);
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取圆心
        int circle_X = measuredWidth / 2;
        int circle_Y = measuredHeight / 2;
        float radius = round_size / 2;//圆半径

        //画笔
        Paint paint = new Paint();
        paint.setStrokeWidth(circle_stroke);
        paint.setColor(circle_color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        //绘制圆
        canvas.drawCircle(circle_X, circle_Y, radius, paint);

        RectF rectF = new RectF(measuredWidth / 2 - round_size / 2, measuredHeight / 2 - round_size / 2, measuredWidth / 2 + round_size / 2, measuredHeight / 2 + round_size / 2);
        paint.setColor(arc_color);
        paint.setStrokeWidth(circle_stroke);
        canvas.drawArc(rectF, 0, progressAngle, false, paint);
        String progress_text = (progressAngle * 100) / 360 + "%";
        paint.setColor(text_color);
        paint.setTextSize(text_size);
        paint.setStrokeWidth(2);

        Rect rect = new Rect();
        paint.getTextBounds(progress_text, startAngle, stopAngle, rect);
        canvas.drawText(progress_text, measuredWidth / 2 - rect.width() / 2, measuredHeight / 2 + rect.height() / 2, paint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        //扇形的总角度
        offesAngle = (360 * progress) / 100;
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();//触发onDraw方法
            if (progressAngle <= offesAngle) {
                progressAngle += stopAngle;
                handler.sendEmptyMessageDelayed(1, 20);
            }
        }
    };
}