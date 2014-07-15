package com.litz.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.litz.R;
import com.litz.Tools.AssetUtil;
import com.litz.Widget.CustomerSpinner;
import com.litz.Widget.RotateImageView;
import com.litz.Widget.SelectPopupWindow;
import com.litz.Widget.DataWheel.DataTimePicker;

public class MainActivity extends Activity {

	
	private RotateImageView rotateImg;
	
	private DataTimePicker dataWheel;
	private Button selectPOP;
	
	SelectPopupWindow sweepWindow;
	private CustomerSpinner spinner;
	
	public static List<Map<String, String>> listMap;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initLocation();
		findViewById();
		setClick();
	}
	
	public void findViewById()
	{
		rotateImg = (RotateImageView)findViewById(R.id.update_progress);
		dataWheel = (DataTimePicker)findViewById(R.id.dataWheel);
		selectPOP = (Button)findViewById(R.id.selectPOP);
		
		spinner = (CustomerSpinner)findViewById(R.id.spinner);
	    spinner.setMap(listMap,"qwe");
	    
	}
	
	public void setClick()
	{
		dataWheel.setOwner(this);
		dataWheel.initialize();
		dataWheel.isShowTime(true);
		
		rotateImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(rotateImg.isStartAnim())
				{
					rotateImg.stopAnim();
				}
				else 
				{
					rotateImg.startAnim();
				}
			}
		});
		
		selectPOP.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sweepWindow = new SelectPopupWindow(MainActivity.this, new OnClickListener() {
					@Override
					public void onClick(View v) {
						sweepWindow.dismiss();
						switch (v.getId()) {
							case R.id.pop_ct:
								Toast.makeText(getBaseContext(), "pop_ct",100).show();
								break;
							case R.id.pop_ic:
								Toast.makeText(getBaseContext(), "pop_ic",100).show();
								break;
						}
					}
				});
				// 显示窗口
				sweepWindow.showAtLocation(selectPOP,
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
				
			}
		});
		
	}
	
    public void initLocation(){          
    	listMap = new ArrayList<Map<String,String>>();
    	for(int i=0;i<5;i++){
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("qwe", "values "+i);
    		listMap.add(map);
    	}
    }
	
}
