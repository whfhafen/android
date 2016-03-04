package com.example.activitytest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class FirstActive extends Activity{
	@Override
	protected void onCreate(Bundle savedInstancestate){
		
		super.onCreate(savedInstancestate);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
		setContentView(R.layout.first_layout);
		Button button1 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				//Toast.makeText(FirstActive.this, "你点击了按钮", Toast.LENGTH_SHORT).show();
				//finish();//结束APP
				//Intent intent = new Intent(Intent.ACTION_VIEW);//定义活动
				//intent.setData(Uri.parse("http://www.baidu.com"));
				//startActivity(intent);//启动活动
				// Intent intent = new Intent(Intent.ACTION_DIAL);
				// intent.setData(Uri.parse("tel:10086"));
				// startActivity(intent);
				String data = "Hello world!";
				Intent intent = new Intent(FirstActive.this,SecondActivity.class);
				intent.putExtra("extra_data",data);
				startActivityForResult(intent,1);
			}
		});
	}
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.add_item:
			Intent intent2 = new Intent("com.example.activitytest.ACTION_START");
			startActivity(intent2);
			break;
		case R.id.remove_item:
			Toast.makeText(this, "你点击了移除按钮", Toast.LENGTH_SHORT).show();
			break;
		default:
		}
		return true;
		
	}
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		switch(requestCode){
		case 1:
			if(resultCode==RESULT_OK){
				String returnedData = data.getStringExtra("data_return");
				TextView textview = (TextView)findViewById(R.id.textView3);
				textview.setText(returnedData);
			}
			break;
		default:
		}
	}
	
}
