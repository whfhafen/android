package com.example.an_broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private IntentFilter intentFilter;
	private NetworkChangeReceiver networkChangeRecriver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//定义要接受的广播
		networkChangeRecriver = new NetworkChangeReceiver();
		registerReceiver(networkChangeRecriver,intentFilter);//注册广播接收器
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.example.an_broadcasttest.MainActivity");
				sendBroadcast(intent);
			}
		});
	}
	protected void onDestroy(){
		super.onDestroy();
		unregisterReceiver(networkChangeRecriver);//注销广播接收器
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
	class NetworkChangeReceiver extends BroadcastReceiver{//BroadcastReceiver是广播接收器的基类

		@Override
		public void onReceive(Context context, Intent intent) {//当网络发生变化时onReceive方法就会执行
			// TODO Auto-generated method stub
			//connectivityManager是一个系统类专门用来管理网络连接的
			ConnectivityManager connectionManager = (ConnectivityManager)
					getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
			if(networkInfo!=null&&networkInfo.isAvailable()){
			Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context, "network is unavaliable", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	public class BootCompleteReceiver extends  BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "kaijiqidong", Toast.LENGTH_SHORT).show();
		}
	}
	//自定义广播
	public static class MyBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context,"receive in my",Toast.LENGTH_SHORT).show();
		}
		
	}
}
