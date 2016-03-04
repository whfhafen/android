package com.example.an_broadcastoutline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;
	
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		accountEdit = (EditText)findViewById(R.id.account);
		String inputText = load();
		if(!TextUtils.isEmpty(inputText)){
			accountEdit.setText(inputText);
			accountEdit.setSelection(inputText.length());//将光标移动到指定位置，这里是移动到最后
		}
		passwordEdit = (EditText)findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String account = accountEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				if(account.equals("admin")&&password.equals("12345")){
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(LoginActivity.this, "你输入的信息有误",Toast.LENGTH_SHORT).show();
					
				}
			}
		});
	}
	protected void onDestroy(){
		super.onDestroy();
		String text = accountEdit.getText().toString();
		save(text);
	}
	private void save(String text) {
		// TODO Auto-generated method stub
		FileOutputStream out = null;
		BufferedWriter write = null;
		try {
			out = openFileOutput("data", MODE_PRIVATE);
			write = new BufferedWriter(new OutputStreamWriter(out));
			write.write(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(write!=null){
				try {
					write.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private String load() {
		// TODO Auto-generated method stub
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuilder content = new StringBuilder();
		try {
			in = openFileInput("data");
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while((line=reader.readLine())!=null){
				content.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}

}
