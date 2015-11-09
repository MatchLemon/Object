package com.bar.progressbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	ProgressBar bar;
	TextView textView;
	Button add, reduce, resert, addDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.fragment_main);
		setProgressBarIndeterminateVisibility(true);
		setProgressBarVisibility(true);
		setProgress(500);
		intt();
	}

	private void intt() {
		bar = (ProgressBar) findViewById(R.id.horiz);
		textView = (TextView) findViewById(R.id.text);
		add = (Button) findViewById(R.id.add);
		reduce = (Button) findViewById(R.id.reduce);
		resert = (Button) findViewById(R.id.reset);
		addDialog = (Button) findViewById(R.id.addDialog);
		add.setOnClickListener(this);
		reduce.setOnClickListener(this);
		resert.setOnClickListener(this);
		addDialog.setOnClickListener(this);
		bar.setMax(100);
		int max = bar.getMax();
		int f = bar.getProgress();
		textView.setText("当前进度" + (int) f / (float) max * 100 + "%");
	}

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.add:
			bar.incrementProgressBy(10);

			break;
		case R.id.reduce:
			bar.incrementProgressBy(-10);
			break;
		case R.id.reset:

			break;
		case R.id.addDialog:
			 ProgressDialog dialog = new ProgressDialog(MainActivity.this);
			 dialog.setTitle("进度条对话框");
			 dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			 dialog.setMessage("你在下载呢");
			 dialog.setIcon(R.drawable.ic_launcher);
			 dialog.setMax(100);
			 dialog.incrementProgressBy(50);
			 dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",new DialogInterface.OnClickListener() {
			@Override
			 public void onClick(DialogInterface arg0, int arg1) {
			Toast.makeText(MainActivity.this, "哈哈",
			 Toast.LENGTH_SHORT).show();
			 }
			 });
			 dialog.show();
			break;

			
		}
		textView.setText("当前进度" + (int) bar.getMax()/(float) bar.getProgress() * 100 + "%");

	}

}
