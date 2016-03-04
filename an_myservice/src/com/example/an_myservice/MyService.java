package com.example.an_myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	public void onCreate(){
		super.onCreate();
		Log.d("myservice","onCreate executed");
	}
	
	public int onStartCommand(Intent intent,int flags,int startId){
		Log.d("Myservice", "onStartCommand executed");
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void onDestory(){
		super.onDestroy();
		Log.d("onDestory", "onDestory executed");
	}
}
