package com.Test.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.Test.test.R.drawable;

public class MainActivity extends ActionBarActivity implements OnItemClickListener,OnScrollListener {

	ListView listView;
	SimpleAdapter adapter;
	ArrayAdapter<String> adapter2;
	List<Map<String, Object>> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		listView = (ListView) findViewById(R.id.listView);
		// String[] arry={"大豆1","大豆2","大豆3","大豆4","大豆5"};
		// adapter2=new ArrayAdapter<String>(this,
		// android.R.layout.simple_expandable_list_item_1,arry);
		//
		list=new ArrayList<Map<String,Object>>();
		adapter = new SimpleAdapter(this, getsource(), R.layout.item,
				new String[] { "pic", "text" },
				new int[] { R.id.pic, R.id.text });
		// listView.setAdapter(adapter2);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnScrollListener(this);
	}

	public List<Map<String, Object>> getsource() {
		for (int i = 0; i < 20; i++) {

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pic", drawable.ic_launcher);
			map.put("text", "项目" + i);
			list.add(map);
		}

		return list;
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
	
		
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
	
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	Builder builder = new AlertDialog.Builder(this).setTitle("提示").setMessage("我是项目"+position).setPositiveButton("确定",null);
	builder.show();
	//		String text=listView.getItemAtPosition(position)+"";
	//		Toast.makeText(this,"position="+position+"text="+text, Toast.LENGTH_SHORT).show();
	}
}
