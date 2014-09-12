package com.alibs.Activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.alibs.R;

public class time extends Activity {
    private TextView tv1, tv2;
    private Button b1, b2;
    private Calendar c;
    private int _year, _month, _day, _hour, _minute;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        c = Calendar.getInstance();
        _year = c.get(Calendar.YEAR);
        _month = c.get(Calendar.MONTH);
        _day = c.get(Calendar.DAY_OF_MONTH);
        _hour = c.get(Calendar.HOUR);
        _minute = c.get(Calendar.MINUTE);
        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(0);
            }
        });
        b2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(1);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 0) {
            return new DatePickerDialog(this, new OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year,
                        int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    _year = year;
                    _month = monthOfYear;
                    _day = dayOfMonth;
                    tv1.setText(_year + ":" + (_month + 1) + ":" + _day);
                }

            }, _year, _month, _day);
        } else
            return new TimePickerDialog(this, new OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    // TODO Auto-generated method stub
                    _hour = hourOfDay;
                    _minute = minute;
                    tv2.setText(_hour + ":" + _minute);
                }

            }, _hour, _minute, false);
    }
}