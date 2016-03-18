package com.smile.smileview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;

import android.view.View;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qq272 on 2016/3/9.
 */
public class MyView extends View  {
    private float width;//控件宽度
    private float height;//空间高度
    private float rect_w;//柱状图的宽度
    private int rect_t1,rect_t2,rect_t3,rect_t4,rect_t5;
    private int random;//动态变换的高度比例
    private boolean status=true;//控件的状态
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x1233){
                MyView.this.invalidate();//重绘更新view
            }
        };
    };

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 控制控件是否跳动  true为跳动，false为停止
     * @param b
     */
    public void change_Status(boolean b){
        status=b;
    }

    /**
     * 拿到控件的状态
     * @return
     */
    public boolean get_Status(){
       // Log.i("smile","状态改变了吗？");
        return status;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //拿到控件的宽高度,并动态计算柱子的宽度
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
       // Log.i("dandy",""+height+"   "+width);
        rect_w= (float) (width*0.9/6);
        random= (int) (height/5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(status==true){
            // 定义一个定时器 ，让画图每个0.5秒执行一次
            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    rect_t1=new Random().nextInt(random);
                    rect_t2=new Random().nextInt(random);
                    rect_t3=new Random().nextInt(random);
                    rect_t4=new Random().nextInt(random);
                    rect_t5=new Random().nextInt(random);
                    // TODO Auto-generated method stub
                    handler.sendEmptyMessage(0x1233);
                }
            }, 300);
        }
        else get_Status();
        super.onDraw(canvas);
        Paint paint=new Paint();

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawLine((float) (width*0.1), (float) (height*0.9), (float) (width*0.9), (float) (height*0.9), paint);
        //画柱状；动态图，就要改变柱状的top值
        RectF r1=new RectF((float) (width*0.1),rect_t1*5,(float)( width*0.1+rect_w),(float) (height*0.9));
        RectF r2=new RectF((float) (width*0.1+rect_w),rect_t2*5,(float)( width*0.1+rect_w*2),(float) (height*0.9));
        RectF r3=new RectF((float) (width*0.1+rect_w*2),rect_t3*5,(float)( width*0.1+rect_w*3),(float) (height*0.9));
        RectF r4=new RectF((float) (width*0.1+rect_w*3),rect_t4*5,(float)( width*0.1+rect_w*4),(float) (height*0.9));
        RectF r5=new RectF((float) (width*0.1+rect_w*4),rect_t5*5,(float)( width*0.1+rect_w*5),(float) (height*0.9));
        Paint paint1=new Paint();
        paint1.setColor(Color.YELLOW);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setAntiAlias(true);
        canvas.drawRect(r1,paint1);
        canvas.drawRect(r2,paint1);
        canvas.drawRect(r3,paint1);
        canvas.drawRect(r4,paint1);
        canvas.drawRect(r5,paint1);
    }


}
