package com.example.an_notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				//��һ��������ָ����֪ͨͼ�� �ڶ���������ָ����ticket֪ͨ�����ݻ�һ������ ������������֪ͨ����ʱ ��ʱ��
				@SuppressWarnings("deprecation")
				Notification notification = new Notification();
				notification.icon = R.drawable.ic_launcher;
				notification.tickerText="����һ������Ϣ";
				notification.defaults = notification.DEFAULT_ALL;
				notification.when= System.currentTimeMillis();
				long[] vibrates ={0,1000,1000,1000};
				notification.vibrate = vibrates;
				
				Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
				notification.setLatestEventInfo(getBaseContext(), "����һ����ʾ", "������ʾ������", pi);
				
				manager.notify(1,notification);
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
