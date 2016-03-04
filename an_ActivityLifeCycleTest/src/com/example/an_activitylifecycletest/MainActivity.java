package com.example.an_activitylifecycletest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG,"onCreate");
		setContentView(R.layout.activity_main);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Button startNormalActivity = (Button)findViewById(R.id.start_normal_activity);
		Button startDialogActivity = (Button)findViewById(R.id.start_dialog_activity);
		startNormalActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,NormalActivity.class);
				startActivity(intent);
			}
		});
		startDialogActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,DialogActivity.class);
				startActivity(intent);
			}
		});
		
	}
	protected void onStart(){
		super.onStart();
		Log.d(TAG,"onstart");
	}
	protected void onResume(){
		super.onResume();
		Log.d(TAG,"onresume");
	}
	protected void onPause(){
		super.onPause();
		Log.d(TAG,"onpause");
	}
	protected void onStop(){
		super.onStop();
		Log.d(TAG,"onStop");
	}
	protected void onDestory(){
		super.onDestroy();
		Log.d(TAG,"onDestory");
	}
	protected void onRestart(){
		super.onRestart();
		Log.d(TAG,"onRestart");
	}
	
}
