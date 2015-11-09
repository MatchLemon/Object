package comcall.callphone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ListenerService  extends Service{

	@Override
	public void onCreate() {
		
		super.onCreate();
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onDestroy() {
		
		 Intent localIntent = new Intent();
	        localIntent.setClass(this, CallPhoneLightService.class);  //销毁时重新启动Service
	        this.startService(localIntent);
	}

}
