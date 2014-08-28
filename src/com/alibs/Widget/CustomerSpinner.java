package com.alibs.Widget;

import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibs.R;

public class CustomerSpinner extends Spinner {

	public Context context;
	private Dialog dialog = null;
	private List<String> list = null;//  list存储所要显示的数据 
	private List<Map<String,String>> mapList = null;
	
	private int listType = LIST;//默认为0 list<String>  1为List<Map<String,String>>类型
	public static int LIST = 0;
	public static int MAP = 1;
	
	private String key;
	
	public CustomerSpinner(Context context) {
		super(context);
	}

	public CustomerSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = getContext();
		  if (isInEditMode()) {
	            return;
	        }
	        //为MyDateSpinner设置adapter，主要用于显示spinner的text值
		  CustomerSpinner.this.setAdapter(new BaseAdapter() {
	 
	            @Override
	            public int getCount() {
	                return 1;
	            }
	 
	            @Override
	            public Object getItem(int arg0) {
	                return null;
	            }
	 
	            @Override
	            public long getItemId(int arg0) {
	                return 0;
	            }
	 
	            @Override
	            public View getView(int arg0, View arg1, ViewGroup arg2) {
	                TextView text = new TextView(CustomerSpinner.this.getContext());
	                if(listType == LIST)
	                	text.setText(list.get(arg0));
	                else if(listType == MAP) 
	                	text.setText(mapList.get(arg0).get(key));
	                
	                text.setTextColor(Color.BLACK);
	                text.setSingleLine(true);
	                text.setTextSize(18);
	                return text;
	            }
	        });
		
	}

	@Override
	public boolean performClick() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View view = inflater.inflate(R.layout.customspinner, null);
		ListView listview = (ListView) view.findViewById(R.id.formcustomspinner_list);
		
		ListviewAdapter adapters = null;
		if(listType == LIST)
			adapters = new ListviewAdapter(context, getList());
		else if(listType == MAP)
			adapters = new ListviewAdapter(context, getMap(),key);
		
		listview.setAdapter(adapters);
		listview.setOnItemClickListener(onItemClick);
		dialog = new Dialog(context,R.style.AndroidPosDialog);//创建Dialog并设置样式主题
		LayoutParams params = new LayoutParams(450, LayoutParams.WRAP_CONTENT);
		dialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
		dialog.show();   
		dialog.addContentView(view, params);
		return true;
	}

	OnItemClickListener onItemClick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			setSelection(position);
			
			if (dialog != null) 
			{
				dialog.dismiss();
				dialog = null;
			}
		}
	};
	
	public String getSelectItem()
	{
		if(listType == LIST)
			return list.get(getSelectedItemPosition());
		else {
			return mapList.get(getSelectedItemPosition()).get(key);
		} 
	}
	
	public String getSelectItem(String key)
	{
		if(listType == LIST)
			return list.get(getSelectedItemPosition());
		else {
			return mapList.get(getSelectedItemPosition()).get(key);
		} 
	}
	
	public void setList(List<String> list) {
		this.list = list;
		this.listType = LIST;
	}
	
	public List<String> getList() {
		return list;
	}
	
	public void setMap(List<Map<String, String>> map,String key)
	{
		this.mapList = map;
		this.key = key;
		this.listType = MAP;
	}
	
	public List<Map<String, String>> getMap() {
		return mapList;
	}

	class ListviewAdapter extends BaseAdapter {
		private Context context;
		private List<String> list = null;
		private List<Map<String, String>> mapList;
		private String key;
		
		public ListviewAdapter(Context context,List<String> list){
			this.context = context;
			this.list = list;
		}
		
		public ListviewAdapter(Context context,List<Map<String, String>> list,String key){
			this.context = context;
			this.mapList = list;
			this.key = key;
		}
		
		@Override
		public int getCount() {
			if(listType == LIST)
				return list == null ? 0 : list.size();
			else {
				return mapList == null ? 0 : mapList.size();
			}
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ViewHolder viewHolder = null;
			if(arg1 == null){
				viewHolder = new ViewHolder();
				LayoutInflater inflater = LayoutInflater.from(context);
				arg1 = inflater.inflate(R.layout.custspinner_item, null);
				viewHolder.itemView = (TextView)arg1.findViewById(R.id.itemText);
				arg1.setTag(viewHolder);
			}else
				viewHolder = (ViewHolder) arg1.getTag();
			
			if(listType == LIST)
				viewHolder.itemView.setText(list.get(arg0));
			else if(listType == MAP)
				viewHolder.itemView.setText(mapList.get(arg0).get(key));
				
			return arg1;
		}
		
		
		class ViewHolder {
			public TextView itemView;
		}

	}
}
