package com.example.an_broadcastoutline;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

public class ForceOfflineReceive extends BroadcastReceiver{

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
		dialogBuilder.setTitle("Warning");
		dialogBuilder.setMessage("You are forced ti be offline.");
		dialogBuilder.setCancelable(false);//����Ϊ����ȡ��
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ActivityCollector.finishAll();//�������л
				Intent intent = new Intent(context,LoginActivity.class);//��������LoginActivity�
				//�ڹ㲥���������������Ҫ��intent���FLAG_ACTIVITY_NEW_TASK��־
				//If set, this activity will become the start of a new task on this history
				//stack. A task (from the activity that started it to the next task activity) 
				//defines an atomic group of activities that the user can move to.
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		//�ѶԻ����������ΪTYPE_SYSTEM_ALERT�����ڹ㲥�������ﵯ��
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}
