package com.example.an_uilayout;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends ArrayAdapter{
	private int resourceId;
	public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
		super(context, textViewResourceId, objects);
		resourceId=textViewResourceId;
	}
	public View getView(int position,View convertView,ViewGroup parent){//重写getView方法
		Fruit fruit = (Fruit) getItem(position);//获取当前项的fruit实例
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view =  LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder= new ViewHolder();
			viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
			viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
			view.setTag(viewHolder);
		}
		else{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.fruitImage.setImageResource(fruit.getImangeId());
		viewHolder.fruitName.setText(fruit.getName());
		return view;
		
	}
	/**
	 * 定义缓存view
	 * */
	class ViewHolder{
		ImageView fruitImage;
		TextView fruitName;
	}

}
