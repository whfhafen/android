package com.example.an_notificationtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoActivity extends Activity{
	public static final int TAKE_PHOTO =1;
	public static final int CROP_PHOTO =2;
	private Button takePhoto;
	private ImageView picture;
	private Uri  imageUri;
	protected void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
		setContentView(R.layout.photo_layout);
		takePhoto= (Button)findViewById(R.id.take_photo);
		picture = (ImageView)findViewById(R.id.picture);
		takePhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Environment.getExternalStorageDirectory()获取手机sd卡的根目录 定义文件存储手机摄像头拍摄的照片
				File outputImage = new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
				try{
					//如果图片已经存在那就把它删除
				if(outputImage.exists()){
					outputImage.delete();
				}
				outputImage.createNewFile();
				}catch(IOException e){
					e.printStackTrace();
				}
				//将file对象转化为uri对象
				imageUri = Uri.fromFile(outputImage);
				//将intent的action指定为android.media.action.IMAGE_CAPTURE
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				//指定图片的输出地址
				intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
				//启动相机
				startActivityForResult(intent,TAKE_PHOTO);
			}
		});
	}
	/*
	 * 如果你想在Activity中得到新打开Activity关闭后返回的数据，
	 * 你需要使用系统提供的startActivityForResult(Intent intent,int requestCode)方法
	 * 打开新的Activity，新的Activity关闭后会向前面的Activity传回数据，为了得到传回的数据，
	 * 你必须在前面的Activity中重写onActivityResult(int requestCode, int resultCode,Intent data)方法
	 */
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		switch(requestCode){
		case TAKE_PHOTO:
			if(resultCode==RESULT_OK){
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				//启动裁剪程序
				startActivityForResult(intent,CROP_PHOTO);
			}
			break;
		case CROP_PHOTO:
			if(resultCode==RESULT_OK){
				try {
					//将图片解析成Bitmap对象
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
					picture.setImageBitmap(bitmap);//将裁剪后的照片显示出来
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			break;
			default:
				break;
		}
	}
}
