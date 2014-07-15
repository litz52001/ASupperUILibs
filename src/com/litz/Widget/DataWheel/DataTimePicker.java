package com.litz.Widget.DataWheel;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.litz.R;
import com.litz.Interface.IWidget;


public class DataTimePicker extends LinearLayout implements IWidget {

	private Context context;
	private View parentView;
	private View childView;
	private LayoutInflater parentInflater, childInflater;
	private Display display;
	protected Activity owner;

//	private EditText showDate;
	private TextView showDate;
	
	private Dialog dialog;
	private static int START_YEAR = 2000, END_YEAR = 2100;
	String DateSplit = "-";

	public DataTimePicker(Context context) {
		super(context);
	}

	public DataTimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		parentInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		parentView = parentInflater.inflate(R.layout.datatimepicker, this, true);

		findViewsById();
		setWidgeBg();
	}

	@Override
	public void findViewsById() {
		showDate = (EditText) parentView.findViewById(R.id.showdialog);
	}
	
	private void setWidgeBg() {
		// TODO Auto-generated method stub
		showDate.setBackgroundDrawable(null);
		showDate.setTextColor(getContext().getResources().getColor(R.color.dimwhite));
	}
	
	@Override
	public void initialize() {
		initGui();
		createAdapter();
		addControlListener();
		setWidgetListener();
	}

	@Override
	public void addControlListener() {
		showDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDateTimePicker();
			}
		});
	}

	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	private void showDateTimePicker() {
		int year = 0;
		int month = 0;
		int day = 0;
		int hour = 0;
		int minute = 0;
		String datePicker = showDate.getText().toString().trim();
		
		if (datePicker.indexOf(DateSplit) < 0) {
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DATE);
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
		} else {
			String[] dateTrack = datePicker.split(DateSplit);
			year = Integer.parseInt(dateTrack[0]);
			month = Integer.parseInt(dateTrack[1]) - 1;
			day = Integer.parseInt(dateTrack[2]);
		}

		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		display = owner.getWindow().getWindowManager()
				.getDefaultDisplay();

		dialog = new Dialog(owner);
		// 找到dialog的布局文件
		childInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		childView = childInflater.inflate(R.layout.datatimepickerdetail, null);

		// 年
		final WheelView wv_year = (WheelView) childView.findViewById(R.id.year);
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setLabel("年");// 添加文字
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

		// 月
		final WheelView wv_month = (WheelView) childView.findViewById(R.id.month);
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setLabel("月");
		wv_month.setCurrentItem(month);

		// 日
		final WheelView wv_day = (WheelView) childView.findViewById(R.id.day);
		wv_day.setCyclic(true);
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setLabel("日");
		wv_day.setCurrentItem(day - 1);

		/*
		 * // 时 final WheelView wv_hours = (WheelView)
		 * childView.findViewById(R.id.hour); wv_hours.setAdapter(new
		 * NumericWheelAdapter(0, 23)); wv_hours.setCyclic(true);
		 * wv_hours.setCurrentItem(hour);
		 * 
		 * // 分 final WheelView wv_mins = (WheelView)
		 * childView.findViewById(R.id.mins); wv_mins.setAdapter(new
		 * NumericWheelAdapter(0, 59, "%02d")); wv_mins.setCyclic(true);
		 * wv_mins.setCurrentItem(minute);
		 */

		// 添加"年"监听
		IOnWheelChanged wheelListener_year = new IOnWheelChanged() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		// 添加"月"监听
		IOnWheelChanged wheelListener_month = new IOnWheelChanged() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);

		// 根据屏幕密度来指定选择器字体的大小
		int textSize = adjustFontSize(display.getWidth(), display.getHeight());
		wv_day.TEXT_SIZE = textSize;
		/*
		 * wv_hours.TEXT_SIZE = textSize; wv_mins.TEXT_SIZE = textSize;
		 */
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;

		Button btn_sure = (Button) childView.findViewById(R.id.btn_datetime_sure);
		Button btn_cancel = (Button) childView.findViewById(R.id.btn_datetime_cancel);
		// 确定
		btn_sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 如果是个数,则显示为"02"的样式
				String parten = "00";
				DecimalFormat decimal = new DecimalFormat(parten);
				// 设置日期的显示 + " "
				// + decimal.format(wv_hours.getCurrentItem()) + ":"
				// + decimal.format(wv_mins.getCurrentItem())
				showDate.setText((wv_year.getCurrentItem() + START_YEAR) + "-"
						+ decimal.format((wv_month.getCurrentItem() + 1)) + "-"
						+ decimal.format((wv_day.getCurrentItem() + 1)));

				dialog.dismiss();
			}
		});
		// 取消
		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
 		// 设置dialog的布局,并显示
		dialog.setContentView(childView,new LayoutParams(adjustWeight(display.getWidth(),
						display.getHeight()), android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		dialog.setCanceledOnTouchOutside(false);

		dialog.show();
	}

	/**
	 * 设置是否显示当前时间
	 */
	public void isShowTime(boolean isShow) {
		String Month = null;
		String Day = null;
		if (isShow) {
			Calendar calendar = Calendar.getInstance();
			int Year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			if (month < 10) {
				Month = "0" + month;
			} else {
				Month = String.valueOf(month);
			}
			int day = calendar.get(Calendar.DATE);
			if (day < 10) {
				Day = "0" + day;
			} else {
				Day = String.valueOf(day);
			}
			showDate.setText(Year + DateSplit + Month + DateSplit + Day);
		}else{
			showDate.setHint("请点选时间");
		}
	}
	
	public String getText(){
		String datePicker = showDate.getText().toString().trim();
		if(datePicker.indexOf(DateSplit)<0){
			return "";
		}else{
			return datePicker.replaceAll(DateSplit,"");
		}
	}

	private int adjustFontSize(int screenWidth, int screenHeight) {
		if (screenWidth <= 240) { // 240X320 屏幕
			return 24;//10;
		} else if (screenWidth <= 320) { // 320X480 屏幕
			return 24;//14;
		} else if (screenWidth <= 480) { // 480X800 或 480X854 屏幕
			return 24;//16;
		} else if (screenWidth <= 540) { // 540X960 屏幕
			return 24;//18;
		} else if (screenWidth <= 800) { // 800X1280 屏幕
			return 24;//20;
		} else { // 大于 800X1280
			return 35;
		}
	}

	private int adjustWeight(int screenWidth, int screenHeight) {
 		Log.e("adjustWeight", screenWidth+"=="+screenHeight);
		Configuration cf = this.getResources().getConfiguration();
		int ori = cf.orientation;
		if (ori == Configuration.ORIENTATION_LANDSCAPE) {
			// 横屏
			if (screenWidth <= 540) { // 540X960 屏幕
				return 410;//screenWidth * 6 / 7;
			} else if (screenWidth <= 800) { // 800X1280 屏幕
				return 450;//screenWidth * 5 / 7;
			} else { // 大于 800X1280
				return screenWidth * 4 / 7;
			}
		} else if (ori == Configuration.ORIENTATION_PORTRAIT) {
			// 竖屏
			if (screenWidth <= 540) { // 540X960 屏幕
				return 410;//screenWidth * 6 / 7;
			} else if (screenWidth <= 800) { // 800X1280 屏幕
				return 450;//screenWidth * 5 / 7;
			} else { // 大于 800X1280
				return screenWidth * 5 / 7;
			}
		}
		return screenWidth * 5 / 7;
//		return 410;
	}

	@Override
	public void setOwner(Activity owner) {
		this.owner = owner;
	}

	@Override
	public Activity getOwner() {
		return this.owner;
	}

	@Override
	public void initGui() {
	}

	@Override
	public void createAdapter() {
	}

	@Override
	public void updateAdapter(boolean type) {
	}

	@Override
	public void setWidgetListener() {
	}

	@Override
	public void setViewAdapter(BaseAdapter adapter) {
	}

	@Override
	public void bindViewAdapter(BaseAdapter adapter) {
	}

}
