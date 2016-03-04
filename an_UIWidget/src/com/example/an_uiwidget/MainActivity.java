package com.example.an_uiwidget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button button;
	private EditText edit;
	private ImageView imageView;
	private ProgressBar progressbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.button1);
		edit = (EditText)findViewById(R.id.edit_text);
		imageView = (ImageView)findViewById(R.id.image_view);
		progressbar = (ProgressBar)findViewById(R.id.progress_bar);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId()){
				case R.id.button1:
					String inputText = edit.getText().toString();
					Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
					imageView.setImageResource(R.drawable.jell_ff);
					int progress = progressbar.getProgress();
					progress = progress+10;
					progressbar.setProgress(progress);
					// AlertDialog.Builder dialog = new
					// AlertDialog.Builder(MainActivity.this);
					// dialog.setTitle("警告！");
					// dialog.setMessage("你摁错了！");
					// dialog.setCancelable(false);
					// dialog.setPositiveButton("OK", new
					// DialogInterface.OnClickListener() {
					//
					// @Override
					// public void onClick(DialogInterface dialog, int which) {
					//
					// }
					// });
					// dialog.setNegativeButton("cancel", new
					// DialogInterface.OnClickListener() {
					//
					// @Override
					// public void onClick(DialogInterface dialog, int which) {
					//
					// }
					// });
					// dialog.show();
					ProgressDialog pressdialog = new ProgressDialog(MainActivity.this);
					pressdialog.setTitle("请等待！");
					pressdialog.setMessage("loading.....");
					pressdialog.setCancelable(true);//不能通过back键取消
					pressdialog.show();
					break;
				default:
					break;
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
