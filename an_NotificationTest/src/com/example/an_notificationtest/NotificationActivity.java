package com.example.an_notificationtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationActivity extends Activity{
	private TextView content;
	private TextView number;
	private EditText to;
	private Button send;
	private EditText msg;
	private IntentFilter receiveFilter;
	private MassageReceiver messageReceiver;
	private IntentFilter sendFilter;
	private SendStatusReceiver sendStatusReceiver;
	protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.notification_layout);
	NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	//1是通知的ID
	manager.cancel(1);
	content = (TextView)findViewById(R.id.content);
	number = (TextView)findViewById(R.id.number);
	// 当Intent在组件间传递时，组件如果想告知Android系统自己能够响应和处理哪些Intent，那么就需要用到IntentFilter对象
	receiveFilter = new IntentFilter();
	receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
	//定义广播接受器用来接收广播receiveFilter
	receiveFilter.setPriority(100);
	messageReceiver = new MassageReceiver();
	registerReceiver(messageReceiver,receiveFilter);
	
	to = (EditText)findViewById(R.id.to);
	msg = (EditText)findViewById(R.id.msg);
	send = (Button)findViewById(R.id.send);
	send.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(to.getText().toString(), null, msg.getText().toString(), null, null);
		}
	});
	
	sendFilter = new IntentFilter();
	sendFilter.addAction("SENT_SMS_ACTION");
	sendStatusReceiver = new SendStatusReceiver();
	registerReceiver(sendStatusReceiver,sendFilter);
	}
	class SendStatusReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if(getResultCode()==RESULT_OK){
				Toast.makeText(context, "fasonghengg", Toast.LENGTH_SHORT).show();
				
			}else{
				Toast.makeText(context, "send failed", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	protected void onDestory(){
		super.onDestroy();
		unregisterReceiver(messageReceiver);
	}
	//广播接收器定义
 class MassageReceiver extends BroadcastReceiver{
	 public void onReceive(Context context,Intent intent){
		 //从intent中取出Bundle对象
		 Bundle bundle = intent.getExtras();
		 //使用pud秘钥提取一个SMS pdus数组，其中每一个pdu都表示一条短信消息
		 Object[] pdus = (Object[])bundle.get("pdus");
		 //使用SmsMessage的createFromPdu方法将每一个pdu字节数组转换为SmsMessage对象，调用这个对象的getOriginatingAddress
		 //方法就能获取到短信的内容 然后将每一个短信的内容拼接起来就得到了完整的短信
		 SmsMessage[] messages = new SmsMessage[pdus.length];
		 for(int i=0;i<messages.length;i++){
			 messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
		 }
		 String adress = messages[0].getOriginatingAddress();
		 String fullMessage = "";
		 for(SmsMessage message:messages){
			 fullMessage+=message.getMessageBody();
		 }
		 content.setText(fullMessage);
		 number.setText(adress);
		 //截断广播
		 abortBroadcast();
	 }
 }
}
