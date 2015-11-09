package comcall.callphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;

public class BroadcastReceiverReset extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent arg1) {
	Intent intent=new Intent(arg0,CallPhoneLightService.class);
	arg0.startService(intent);
	  System.out.println("成功启动");
	  
	  Log.e("zz", "启动成功");
		
	}
}
