package com.example.an_serverse;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	protected static final int UPDATE_TEXT = 1;
	private TextView text;
	private Button change;
	private Handler handler= new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case UPDATE_TEXT:
				text.setText("Nice to meet you");
				break;
			default:
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView)findViewById(R.id.text);
		change = (Button)findViewById(R.id.change_text);
		change.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	class DownloadTask extends AsyncTask<Void,Integer,Boolean>{
		protected void onPreExecute(){
			progressDialog.show(getBaseContext(), "dowloading", null);
		}
		@Override
		protected Boolean doInBackground(Void... params) {
			while(true){
				int downloadPercent = doDownload();
				publishProgress(downloadPercent);
				if(downloadPercent>=100){
					break;
				}
			}
			return true;
		}
		protected void onProgressUpdate(Integer... values){
			progressdDialog.setMessage("Downloaded"+values[0]+"%");
		}
		protected void onPostExecute(Boolean result){
			progressDialog.dismiss();
			if(result){
				Toast.makeText(getBaseContext(), "Download Success", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(getBaseContext(), "Download Failed", Toast.LENGTH_LONG).show();
			}
			
		}
		private int doDownload() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.change_text:
			new Thread(new Runnable(){

				@Override
				public void run() {
					Message message = new Message();
					message.what = UPDATE_TEXT;
					handler.sendMessage(message);
					
				}
				
			}).start();
			break;
		default:
			break;
		}
	}
}
