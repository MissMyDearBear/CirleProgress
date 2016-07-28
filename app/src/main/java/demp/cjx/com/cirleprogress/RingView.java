package demp.cjx.com.cirleprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * title
 * Created by Bear on 2016/6/10.
 */
public class RingView extends View {
    /**
     * 圆心
     */
    private float centerPointX;
    private float centerPointY;
    private String colorStr;

    /**
     * 设置内环半径
     * @param innerRing_radius
     */
    public void setInnerRing_radius(float innerRing_radius) {
        this.innerRing_radius = innerRing_radius;
    }

    public void setOuterRing_radius(float outerRing_radius) {
        this.outerRing_radius = outerRing_radius;
    }

    public void setOuterRing_width(float outerRing_width) {
        this.outerRing_width = outerRing_width;
    }

    public void setColorStr(String colorStr) {
        this.colorStr = colorStr;
    }

    //内环半径
    private float innerRing_radius;
    //外环半径
    private float outerRing_radius;
    //外环宽度
    private float outerRing_width;
    //外环颜色
    private int outerRing_color;
    public float percentage = 0;

    Paint innerRingPaint;
    Paint outerRingPaint;

    private int count = 0;

    private TextView tv;
    private int usedNum;
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 0) {
                if (count <100) {
                    int ss = usedNum * (count + 1) / 100;
                    tv.setText(ss + "");
                    postInvalidate();
                    sendEmptyMessageDelayed(0, 2);
                    count++;
                }
            }
        }
    };

    public RingView(Context context) {
        super(context);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RingView);
        init(context, mTypedArray);
    }

    public RingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RingView);
        init(context, mTypedArray);
    }

    private void init(Context context, TypedArray mTypedArray) {
        innerRing_radius = mTypedArray.getDimension(R.styleable.RingView_innerRing_radius, 45);
        outerRing_radius = mTypedArray.getDimension(R.styleable.RingView_outerRing_radius, 46);
        outerRing_width = mTypedArray.getDimension(R.styleable.RingView_outerRing_width, 8);
        outerRing_color = mTypedArray.getColor(R.styleable.RingView_outerRing_color, context.getResources().getColor(R.color.color_text_gray));

        outerRing_radius += 5;

        innerRingPaint = new Paint();
        innerRingPaint.setAntiAlias(true);
        innerRingPaint.setColor(context.getResources().getColor(R.color.color_text_gray));
        innerRingPaint.setStyle(Paint.Style.STROKE);
        innerRingPaint.setStrokeWidth(2);

        outerRingPaint = new Paint();
        outerRingPaint.setAntiAlias(true);
        outerRingPaint.setStyle(Paint.Style.STROKE);
        outerRingPaint.setStrokeWidth(outerRing_width);
        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (colorStr != null) {
            outerRingPaint.setColor(Color.parseColor(colorStr));
        } else {
            outerRingPaint.setColor(outerRing_color);
        }
        centerPointX = getWidth() / 2;
        centerPointY = getHeight() / 2;
        //画内环
        canvas.drawCircle(centerPointX, centerPointY, innerRing_radius, innerRingPaint);
        //画外环
        if (count <= 100) {
            RectF oval = new RectF();
            oval.left = (centerPointX - outerRing_radius);
            oval.top = (centerPointY - outerRing_radius);
            oval.right = outerRing_radius * 2 + (centerPointX - outerRing_radius);
            oval.bottom = outerRing_radius * 2 + (centerPointY - outerRing_radius);
            canvas.drawArc(oval, -90, (float) (count * percentage * 360.0 / 100), false, outerRingPaint);
        } else {
            count = 0;
        }
    }

    public synchronized float getPercentage() {
        return percentage;
    }

    public synchronized void setPercentage(float percentage) {
        this.percentage = percentage;
        if (percentage > 1) {
            throw new IllegalArgumentException("percentage not more than 1");
        }

        mHandler.sendEmptyMessage(0);


    }

    public void setChangeText(TextView v, int num, String color) {
        this.tv = v;
        this.usedNum = num;
        tv.setText("0");
        setColorStr(color);
        mHandler.sendEmptyMessage(0);
    }
}
