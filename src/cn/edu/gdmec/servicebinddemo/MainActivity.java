package cn.edu.gdmec.servicebinddemo;

import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	Button btn1,btn2,btn3,btn4;
	TextView mytv;
	Intent myit = new Intent("cn.edu.gdmec.boundservice");
	boolean isbound =false;
	BoundService myboundservice;
	
	ServiceConnection mConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			isbound = false;
			
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			myboundservice = ((BoundService.LocalBinder)arg1).getService();
			isbound = true;
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mytv = (TextView) findViewById(R.id.textView1);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
		
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bindService(myit,mConnection,Context.BIND_AUTO_CREATE);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unbindService(mConnection);
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				long a =Math.round(Math.random()*100);
				long b =Math.round(Math.random()*100);
				if(isbound){
				long avg = myboundservice.Avg(a, b);
				mytv.setText("("+String.valueOf(a)+"+"+String.valueOf(b)+")/2="+String.valueOf(avg));
				}
			}
		});
		btn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isbound){
				String str = String.valueOf(myboundservice.PI);
				mytv.setText(str);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
