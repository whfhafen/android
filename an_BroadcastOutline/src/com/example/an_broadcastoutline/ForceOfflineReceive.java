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
		dialogBuilder.setCancelable(false);//设置为不可取消
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ActivityCollector.finishAll();//销毁所有活动
				Intent intent = new Intent(context,LoginActivity.class);//重新启动LoginActivity活动
				//在广播接收器里启动活动需要给intent添加FLAG_ACTIVITY_NEW_TASK标志
				//If set, this activity will become the start of a new task on this history
				//stack. A task (from the activity that started it to the next task activity) 
				//defines an atomic group of activities that the user can move to.
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		});
		AlertDialog alertDialog = dialogBuilder.create();
		//把对话框的类型设为TYPE_SYSTEM_ALERT才能在广播接收器里弹出
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}
