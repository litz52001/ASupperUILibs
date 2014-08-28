package com.alibs.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 可嵌套Editext（当一般的ScrollView使用）
 * @author Administrator
 */
public class ScrollviewEdit extends ScrollView{

	public ScrollviewEdit(Context context) {
		this(context , null);
	}
	
	public ScrollviewEdit(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

}
