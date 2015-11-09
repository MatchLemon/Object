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
//		menu.add(2, 100, 1, "菜单一");
//		menu.add(2, 101, 1, "菜单二");
//		menu.add(2, 102, 1, "菜单三");
//		menu.add(2, 103, 1, "菜单四");
//		menu.add(2, 104, 1, "菜单五");
//		menu.add(2, 105, 1, "菜单六");
//		menu.add(2, 106, 1, "菜单七");
		
	SubMenu build=	menu.addSubMenu("新建");
	SubMenu open=	menu.addSubMenu("打开");

	build.add("文件");
	build.add("音频");
	build.add("图片");
	build.add("视频");
	open.add(2, 1, 1, "快播");
	open.add(2, 2, 1, "优酷");
	open.add(2, 3, 1, "ES浏览器");
	open.add(2, 4, 1, "酷狗");
	
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
//			Toast.makeText(this, "菜单一", Toast.LENGTH_SHORT).show();
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
//			.setPositiveButton("确定", null).show();
//			break;
//
//			
//
//		}
	if(item.getGroupId()==2){
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "快播", Toast.LENGTH_SHORT).show();
			break;
		case 3:
			Toast.makeText(this, "ES浏览器", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(this, "优酷", Toast.LENGTH_SHORT).show();
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
			list.add("小树"+1);
			
		}
		
		return list;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("操作");
		menu.setHeaderIcon(R.drawable.ic_launcher);
		menu.add(1, 200, 2, "复制");
		menu.add(1, 201, 2, "粘贴");
		menu.add(1, 202, 2, "重命名");
		menu.add(1, 203, 2, "分享");
		menu.add(1, 204, 2, "发送");
		menu.add(1,205, 2, "打开");
		
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case 200:
			Toast.makeText(this, "复制", Toast.LENGTH_SHORT).show();
			break;

		
		case 201:
			Toast.makeText(this, "粘贴", Toast.LENGTH_SHORT).show();
			break;
	
		}
		return super.onContextItemSelected(item);
	}
	
}
