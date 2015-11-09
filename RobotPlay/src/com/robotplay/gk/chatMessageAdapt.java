package com.robotplay.gk;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.robotplay.gk.bean.ChatMessage;
import com.robotplay.gk.bean.ChatMessage.Type;

public class chatMessageAdapt extends BaseAdapter {

	public LayoutInflater minflater;
	List<ChatMessage> mData;

	public chatMessageAdapt(Context context, List<ChatMessage> mData) {
		minflater = LayoutInflater.from(context);
		this.mData = mData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public int getItemViewType(int position) {
		ChatMessage chatMessage = mData.get(position);
		if (chatMessage.getType() == Type.INCOMING) {
			return 0;
		}
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		ChatMessage chatMessage = mData.get(arg0);
		ViewHoder hoder = null;
		if (v == null) {

			if (getItemViewType(arg0) == 0) {
				v = minflater.inflate(R.layout.item_from_msg, arg2, false);
				hoder = new ViewHoder();
				hoder.mMsg = (TextView) v.findViewById(R.id.id_from_msg_in);
				hoder.mDate = (TextView) v.findViewById(R.id.id_from_date);
			}else{
				
				v = minflater.inflate(R.layout.item_to_msg, arg2, false);
				hoder = new ViewHoder();
				hoder.mMsg = (TextView) v.findViewById(R.id.id_to_msg);
				hoder.mDate = (TextView) v.findViewById(R.id.id_to_date);
				
			}
			v.setTag(hoder);
		}
		else{
			hoder=(ViewHoder) v.getTag();
		}
		hoder.mMsg.setText(chatMessage.getMsg());
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		hoder.mDate.setText(sf.format(chatMessage.getDate()));
		return v;
	}

	public final class ViewHoder {
		TextView mMsg;
		TextView mDate;
	}
}
