package comcall.callphone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {
	Switch switchLisht;
	Intent intentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_main);

        final IntentFilter filter = new IntentFilter();  
        filter.addAction(Intent.ACTION_SCREEN_OFF);  
        filter.addAction(Intent.ACTION_SCREEN_ON);  
        registerReceiver(new BroadcastReceiverReset(), filter); 
        
        
        
        
        
//
//        Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:1111"));
//
//        startActivity(intent);
        switchLisht= (Switch) findViewById(R.id.light_switch);
     

switchLisht.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
	if(arg1){
		intentService=new Intent(MainActivity.this, CallPhoneLightService.class);
		   startService(intentService);
	}else{
		if(intentService!=null){
			
			stopService(intentService);
		}
	}
		
	}
});
    }
    
   @Override
protected void onDestroy() {
	
	super.onDestroy();
}
   
   
   
   
}
