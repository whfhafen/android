package com.example.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ThirdActivity extends Activity{
	protected void onCreate(Bundle savesInstanceState){
		super.onCreate(savesInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.third_layout);
	}
}
