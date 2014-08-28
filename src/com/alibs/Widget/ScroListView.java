package com.alibs.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解决scrollview与listview共存问题
 * @author Administrator
 *
 */
public class ScroListView extends ListView {

	public ScroListView(Context context) {
		super(context);
	}

	public ScroListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScroListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
	            MeasureSpec.AT_MOST);  
	    super.onMeasure(widthMeasureSpec, expandSpec);  
	}

}