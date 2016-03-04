package com.example.an_contentresolver1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ListView contactsView;
	ArrayAdapter<String> adapter;
	List<String> contactsList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		contactsView = (ListView)findViewById(R.id.contacts_view);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
		
		contactsView.setAdapter(adapter);
		readContacts();
	}

	private void readContacts() {
		Cursor cursor;
		cursor= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, 
				null, null, null);
		while(cursor.moveToNext()){
			//获取联系人姓名
			String displayName = cursor.getString(cursor.getColumnIndex(
					ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			//获取联系人手机号
			String number = cursor.getString(cursor.getColumnIndex(
					ContactsContract.CommonDataKinds.Phone.NUMBER));
			contactsList.add(displayName+"\n"+number);
		}
		if(cursor!=null){
			cursor.close();
		}
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
}
