package com.menu.optionmenu;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_menu);
		 init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
//		menu.add(2, 100, 1, "�˵�һ");
//		menu.add(2, 101, 1, "�˵���");
//		menu.add(2, 102, 1, "�˵���");
//		menu.add(2, 103, 1, "�˵���");
//		menu.add(2, 104, 1, "�˵���");
//		menu.add(2, 105, 1, "�˵���");
//		menu.add(2, 106, 1, "�˵���");
		
	SubMenu build=	menu.addSubMenu("�½�");
	SubMenu open=	menu.addSubMenu("��");

	build.add("�ļ�");
	build.add("��Ƶ");
	build.add("ͼƬ");
	build.add("��Ƶ");
	open.add(2, 1, 1, "�첥");
	open.add(2, 2, 1, "�ſ�");
	open.add(2, 3, 1, "ES�����");
	open.add(2, 4, 1, "�ṷ");
	
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//
//		switch (id) {
//		case 100:
//			Toast.makeText(this, "�˵�һ", Toast.LENGTH_SHORT).show();
//			break;
//		case 102:
//
//			break;
//		case 103:
//
//			break;
//		case 104:
//
//			break;
//		case 105:
//			new AlertDialog.Builder(this).setMessage("message")
//			.setPositiveButton("ȷ��", null).show();
//			break;
//
//			
//
//		}
	if(item.getGroupId()==2){
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "�첥", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Toast.makeText(this, "ES�����", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(this, "�ſ�", Toast.LENGTH_SHORT).show();
			break;

		}
		
	}
		
		return true;
	}
	
	
	public void init(){
		ListView listView=(ListView) findViewById(R.id.listview);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
		listView.setAdapter(adapter);
		this.registerForContextMenu(listView);
		
	}
	public List<String> getData(){
		List<String> list=new ArrayList<String>();
		for (int i = 1; i < 11; i++) {
			list.add("С��"+1);
			
		}
		
		return list;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("����");
		menu.setHeaderIcon(R.drawable.ic_launcher);
		menu.add(1, 200, 2, "����");
		menu.add(1, 201, 2, "ճ��");
		menu.add(1, 202, 2, "������");
		menu.add(1, 203, 2, "����");
		menu.add(1, 204, 2, "����");
		menu.add(1,205, 2, "��");
		
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case 200:
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
			break;

		
		case 201:
			Toast.makeText(this, "ճ��", Toast.LENGTH_SHORT).show();
			break;
	
		}
		return super.onContextItemSelected(item);
	}
	
}
