package com.litz.Tools;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

public class AssetUtil {
	
	private static AssetUtil assertUtil = null;
	private static Context mContext;

	public static AssetUtil getInstance(Context context) {
		
		if(assertUtil == null)
			assertUtil = new AssetUtil();
		
		mContext = context;
		return assertUtil;
	}
	
	public Bitmap createBitmap(String name)
	{
		try {
			return BitmapFactory.decodeStream(mContext.getAssets().open(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public BitmapDrawable getBitDrawable(String name)
	{
		try {
			return new BitmapDrawable(BitmapFactory.decodeStream(mContext.getAssets().open(name)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param view
	 * @param name
	 */
	public void getStateListDrawable(View view,String normalDra,String selDra)
	{
		BitmapDrawable normalzoomin = getBitDrawable(normalDra);  
		BitmapDrawable selectedzoomin = getBitDrawable(selDra); 

		StateListDrawable bgzoomin = new StateListDrawable();
		//获取对应的属性值 Android框架自带的属性 attr  
		//是否按下
		int pressed = android.R.attr.state_pressed;  
		//所在窗口是否处于当前交互窗口
		int window_focused = android.R.attr.state_window_focused;  
		//是否处于聚焦
		int focused = android.R.attr.state_focused;  
		//是否处于被选中状态
		int selected = android.R.attr.state_selected;  
		
		bgzoomin.addState(new int []{pressed , window_focused}, normalzoomin);  
		bgzoomin.addState(new int []{pressed , -focused}, selectedzoomin);  
		bgzoomin.addState(new int []{selected }, selectedzoomin);  
		bgzoomin.addState(new int []{focused }, selectedzoomin);  
		//没有任何状态时显示的图片，我们给它设置我空集合  
		bgzoomin.addState(new int []{}, selectedzoomin);  
		view.setBackgroundDrawable(bgzoomin);
	}
	
}













