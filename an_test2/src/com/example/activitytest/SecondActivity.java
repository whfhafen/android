package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);
		Intent intent = getIntent();
		String data = intent.getStringExtra("extra_data");
		//TextView textView = (TextView) findViewById(R.id.textView1); 
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(data);
		Button button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("data_return", "The return is ");
				setResult(RESULT_OK,intent);
				finish();
			}
		});
	}
	public void onBackPressed(){
		Intent intent = new Intent();
		intent.putExtra("data_return", "back fooe me");
		setResult(RESULT_OK, intent);
		finish();
	}

}
