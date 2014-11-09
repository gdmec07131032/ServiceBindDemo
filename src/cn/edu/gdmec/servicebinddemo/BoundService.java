package cn.edu.gdmec.servicebinddemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BoundService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "OnCreate", 1000).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "OnDestroy", 1000).show();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "OnUnbind", 1000).show();
		return super.onUnbind(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "OnBind", 1000).show();
		return mBinder;
	}
	
	public long Avg(long a,long b){
		return (a+b)/2;
	}
	public class LocalBinder extends Binder{
		BoundService getService(){
			return BoundService.this;
			
		}
	}
	private IBinder mBinder = new LocalBinder();
	public static long PI=(long) 2.1415926535798932;

}
