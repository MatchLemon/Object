package com.Gridview.gridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {

	SimpleAdapter adapter;
	List<Map<String, Object>> list;
	GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridView = (GridView) findViewById(R.id.gridView);
list=new ArrayList<Map<String,Object>>();
		adapter=new SimpleAdapter(this,  getSource(), R.layout.item, new String[]{"image","text"}, new int[]{R.id.image,R.id.text});
gridView.setAdapter(adapter);
gridView.setOnItemClickListener(this);
	}

	
	public List<Map<String, Object>> getSource(){
		
		for(int i=0;i<30;i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("image",R.drawable.ic_launcher);
			map.put("text", "应用"+i);
			list.add(map);
		}
		
		return list;
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//		new AlertDialog.Builder(this).setTitle("提示").setMessage("我是项目"+position).setPositiveButton("确定",null).show();
		Toast.makeText(this,gridView.getItemAtPosition(position)+"" , Toast.LENGTH_SHORT).show();
	}
}
