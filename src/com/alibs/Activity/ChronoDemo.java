package com.alibs.Activity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.alibs.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class ChronoDemo extends Activity {
   //获取日期格式器对象
    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    //定义一个TextView控件对象
    TextView dateAndTimeLabel = null;
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    
    
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);    
            //将页面TextView的显示更新为最新时间
            updateLabel();            
        }        
    };
    


    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        
        //同DatePickerDialog控件
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            updateLabel();
            
        }
    }; 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datatime);
        
        //得到页面设定日期的按钮控件对象
        Button dateBtn = (Button)findViewById(R.id.setDate);
        //设置按钮的点击事件监听器
        dateBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                new DatePickerDialog(ChronoDemo.this,
                        d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();                
            }
        });
        
        Button timeBtn = (Button)findViewById(R.id.setTime);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            
            //同上原理
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ChronoDemo.this,
                        t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE),
                        true).show();
                
            }
        });
        
        dateAndTimeLabel=(TextView)findViewById(R.id.dateAndTime);
        
//        updateLabel();
    }
    
    //更新页面TextView的方法
    private void updateLabel() {
        dateAndTimeLabel.setText(fmtDateAndTime
        .format(dateAndTime.getTime()));
        }
}