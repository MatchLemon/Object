package comcall.callphone;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallPhoneLightService extends Service{
	private Camera camera = null;
	private Parameters parameters = null;
	Intent intent;
	@Override
	public IBinder onBind(Intent arg0) {
		this.intent=arg0;
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return  START_STICKY;
	}
	@Override
	public void onDestroy() {
		Log.e("zz", "service����");
		 Intent localIntent = new Intent();
	        localIntent.setClass(this, CallPhoneLightService.class);  //����ʱ��������Service
	        this.startService(localIntent);
	
	}
	@Override
	public void onCreate() {
	
		super.onCreate();
//		Intent intent= new Intent(getApplicationContext(), ListenerService.class);
//		
//		startService(intent);
		TelephonyManager manager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	    
		manager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
	
	}
	  public class MyPhoneStateListener extends PhoneStateListener{
	    	@Override
	    	  public void onCallStateChanged(int state, String incomingNumber) {
	    	   //ע�⣬��������д��super�������棬����incomingNumber�޷���ȡ��ֵ��
	    	   super.onCallStateChanged(state, incomingNumber);
	    	   switch(state){
	    	   case TelephonyManager.CALL_STATE_IDLE:
	    		 //  Toast.makeText(MainActivity.this, "�Ҷ�", Toast.LENGTH_SHORT).show();
	    	   // System.out.println("�Ҷ�");
	    		   threadState=false;
	    	    break;
	    	   case TelephonyManager.CALL_STATE_OFFHOOK:
	    		 //  Toast.makeText(MainActivity.this, "����", Toast.LENGTH_SHORT).show();
	    		   threadState=false;
	    		//   System.out.println("����");
	    	    break;
	    	   case TelephonyManager.CALL_STATE_RINGING:
	    		 //  Toast.makeText(this, "����:�������"+incomingNumber, Toast.LENGTH_SHORT).show();
	    		   threadState=true;
	    		   starThread();
	    		 //  System.out.println("����:�������"+incomingNumber);
	    	    //����������
	    	    break;
	    	   }
	    	  }
	    }
	    boolean threadState = true;

		public void starThread() {

			new Thread() {

				public void run() {
					while (threadState) {

						startLisgt();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
		}

		Thread thread;

		public void startLisgt() {

			thread = new Thread() {
				public void run() {
					int i=0;
					while(++i<8){
						
						
						flashopen();
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						flashclose();
					}

				}

			};
			thread.start();

		}

		private void flashopen() {
			if (camera == null) {
				camera = Camera.open();
			}
			parameters = camera.getParameters();

			parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);

			camera.setParameters(parameters);
			camera.startPreview();
		}

		private void flashclose() {
			if (camera == null) {

				camera = Camera.open();
			}
			parameters = camera.getParameters();

			parameters.setFlashMode(Parameters.FLASH_MODE_OFF);

			camera.setParameters(parameters);
			// camera.release();
		}

}