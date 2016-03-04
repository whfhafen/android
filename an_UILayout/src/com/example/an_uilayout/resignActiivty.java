package com.example.an_uilayout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class resignActiivty extends Activity{
	private SharedPreferences pref;
	private SharedPreferences.Editor editor; 
	private CheckBox check;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_resign);
		final EditText accoun = (EditText)findViewById(R.id.account);
		final EditText pasd = (EditText)findViewById(R.id.password);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		check = (CheckBox)findViewById(R.id.check);
		boolean isRemember = pref.getBoolean("ischeck", false);
		if(isRemember){
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			accoun.setText(account);
			pasd.setText(password);
			check.setChecked(true);
			
		}
		Button login = (Button)findViewById(R.id.login);
		login.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ac = accoun.getText().toString();
				String ps = pasd.getText().toString();
				editor = pref.edit();
				if(check.isChecked()){
					editor.putBoolean("ischeck", true);
					editor.putString("account", ac);
					editor.putString("password", ps);
				}else{
					editor.clear();
				}
				editor.commit();
			}
		});
	}

}
