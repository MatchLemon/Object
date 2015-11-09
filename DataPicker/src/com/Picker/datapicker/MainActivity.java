package com.Picker.datapicker;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends ActionBarActivity implements
		OnDateChangedListener, OnTimeChangedListener {

	Calendar cal;
	TimePicker tpicker;
	DatePicker dPicker;
	int year;
	int month;
	int day;
	int hour;
	int minute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		hour = cal.get(Calendar.HOUR_OF_DAY);
		minute = cal.get(Calendar.MINUTE);
		setTitle("时间" + year + "-" + (month + 1) + "-" + day + "--" + hour
				+ ":" + minute);
		tpicker = (TimePicker) findViewById(R.id.timePicker);
		dPicker = (DatePicker) findViewById(R.id.datePicker);
		tpicker.setOnTimeChangedListener(this);
		dPicker.init(year, month, day, this);
		Dialog();
	}

	@Override
	public void onDateChanged(DatePicker arg0, int year, int month, int day) {
		setTitle("时间" + year + "-" + (month + 1) + "-" + day);
	}

	@Override
	public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
		setTitle(arg1 + ":" + arg2);

	}

	public void Dialog() {

		new DatePickerDialog(this, new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker arg0, int year, int month, int day) {
				setTitle("时间" + year + "-" + (month + 1) + "-" + day);

			}
		}, year, month, day).show();

	}

}
