package com.alibs.Widget;

import android.R.anim;
import android.R.color;
import android.annotation.SuppressLint;  
import android.content.Context;  
import android.graphics.Canvas;  
import android.graphics.DashPathEffect;  
import android.graphics.Paint;  
import android.graphics.Path;  
import android.graphics.PathEffect;  
import android.util.AttributeSet;  
import android.view.View;  
  
/**
 * 虚线
 * @author litz
 */

public class DashedLineView extends View {  
  
    public DashedLineView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
  
    }  
  
    @SuppressLint("DrawAllocation")  
    @Override  
    protected void onDraw(Canvas canvas) {  
  
        super.onDraw(canvas);  
        Paint paint = new Paint();  
        paint.setStyle(Paint.Style.STROKE);  
        paint.setColor(getResources().getColor(color.tertiary_text_dark));  
  
        Path path = new Path();  
        path.moveTo(0, 5);  
        path.lineTo(this.getWidth(), 5);  
  
        PathEffect effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);  
        paint.setPathEffect(effects);  
        canvas.drawPath(path, paint);  
    }  
  
}  
