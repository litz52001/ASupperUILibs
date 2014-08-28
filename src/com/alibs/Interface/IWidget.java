package com.alibs.Interface;

import android.app.Activity;
import android.widget.BaseAdapter;

public interface IWidget
{
	public void initialize();

	public void initGui();

	public void createAdapter();

	public void updateAdapter(boolean type);

	public void findViewsById();

	public void setWidgetListener();

	public void addControlListener();

	public void setOwner(Activity owner);

	public Activity getOwner();

	public void setViewAdapter(BaseAdapter adapter);

	public void bindViewAdapter(BaseAdapter adapter);

}
