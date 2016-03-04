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
	//1��֪ͨ��ID
	manager.cancel(1);
	content = (TextView)findViewById(R.id.content);
	number = (TextView)findViewById(R.id.number);
	// ��Intent������䴫��ʱ�����������֪Androidϵͳ�Լ��ܹ���Ӧ�ʹ�����ЩIntent����ô����Ҫ�õ�IntentFilter����
	receiveFilter = new IntentFilter();
	receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
	//����㲥�������������չ㲥receiveFilter
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
	//�㲥����������
 class MassageReceiver extends BroadcastReceiver{
	 public void onReceive(Context context,Intent intent){
		 //��intent��ȡ��Bundle����
		 Bundle bundle = intent.getExtras();
		 //ʹ��pud��Կ��ȡһ��SMS pdus���飬����ÿһ��pdu����ʾһ��������Ϣ
		 Object[] pdus = (Object[])bundle.get("pdus");
		 //ʹ��SmsMessage��createFromPdu������ÿһ��pdu�ֽ�����ת��ΪSmsMessage���󣬵�����������getOriginatingAddress
		 //�������ܻ�ȡ�����ŵ����� Ȼ��ÿһ�����ŵ�����ƴ�������͵õ��������Ķ���
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
		 //�ضϹ㲥
		 abortBroadcast();
	 }
 }
}
