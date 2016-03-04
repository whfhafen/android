package com.example.an_activitylifecycletest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class DialogActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_layout);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
}
