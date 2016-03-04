package com.example.an_testone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends BasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		Button button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "you clicked button1", Toast.LENGTH_SHORT).show();
				String data = "这是第二个按钮";
				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
				//SecondActivity.actionStart(MainActivity.this,"data1","data2");
				intent.putExtra("extra_data", data);
				startActivityForResult(intent, 1);
			}
		});
	
}
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		switch(requestCode){
		case 1:
			if(resultCode==RESULT_OK){
				String returnedData = data.getStringExtra("data_return");
				Button button1 = (Button)findViewById(R.id.button1);
				button1.setText(returnedData);
			}
			break;
		default:
		}
	}
	

}
