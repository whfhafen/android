package com.example.an_uilayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public String[] data = {"Apple","Orange","Grape","Pineapple","Strawberry","Cherry","Mango","Bingo",
			"pear","Banana","Watermelon"};
	private List<Fruit> fruitList = new ArrayList<Fruit>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button3);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId()){
				case R.id.button3:
					Intent intent = new Intent(MainActivity.this,resignActiivty.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
		initFruits();
		FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
		ListView listview = (ListView)findViewById(R.id.list_view);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Fruit fruit = fruitList.get(position);
				Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	private void initFruits() {
		Fruit apple = new Fruit("Apple",R.drawable.ic_launcher);
		fruitList.add(apple);
		Fruit orange = new Fruit("Orange",R.drawable.ic_launcher);
		fruitList.add(orange);
		Fruit grape = new Fruit("Grape",R.drawable.ic_launcher);
		fruitList.add(grape);
		Fruit pineapple = new Fruit("Pineapple",R.drawable.ic_launcher);
		fruitList.add(pineapple);
		Fruit strawberry = new Fruit("Strawberry",R.drawable.ic_launcher);
		fruitList.add(strawberry);
		Fruit cherry = new Fruit("Cherry",R.drawable.ic_launcher);
		fruitList.add(cherry);
		Fruit mango = new Fruit("Mango",R.drawable.ic_launcher);
		fruitList.add(mango);
		Fruit bingo = new Fruit("Bingo",R.drawable.ic_launcher);
		fruitList.add(bingo);
		Fruit pear = new Fruit("pear",R.drawable.ic_launcher);
		fruitList.add(pear);
		Fruit banana = new Fruit("Banana",R.drawable.ic_launcher);
		fruitList.add(banana);
		Fruit watermelon = new Fruit("Watermelon",R.drawable.ic_launcher);
		fruitList.add(watermelon);
		
		
	}
//	public void onClick(View v){
//		switch(v.getId()){
//		case R.id.button3:
//			Intent intent = new Intent(MainActivity.this,resignActiivty.class);
//			startActivity(intent);
//			break;
//		default:
//			break;
//		}
//	}
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
