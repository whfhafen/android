package com.example.an_testone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SecondActivity extends BasicActivity{
	protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.second_layout);
	Intent intent = getIntent();
	String data = intent.getStringExtra("extra_data");
	Button button2 = (Button)findViewById(R.id.button2);
	button2.setText(data);
	button2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//Toast.makeText(SecondActivity.this, "You clicke button2", Toast.LENGTH_SHORT).show();
			Intent intent2 = new Intent();
			intent2.putExtra("data_return", "这是第一个按钮！");
			setResult(RESULT_OK,intent2);
			finish();
		}
	});
	}
	public void onBackPressed(){
		Intent intent = new Intent();
		intent.putExtra("data_return", "这是返回的值！");
		setResult(RESULT_OK,intent);
		finish();
	}
	// public static void actionStart(Context context,String data1,String
	// data2){
	// Intent intent = new Intent(context,SecondActivity.class);
	// intent.putExtra("param1", data1);
	// intent.putExtra("param2", data2);
	// context.startActivity(intent);
	// }
	
	
}
