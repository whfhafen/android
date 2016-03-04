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
				//Environment.getExternalStorageDirectory()��ȡ�ֻ�sd���ĸ�Ŀ¼ �����ļ��洢�ֻ�����ͷ�������Ƭ
				File outputImage = new File(Environment.getExternalStorageDirectory(),"tempImage.jpg");
				try{
					//���ͼƬ�Ѿ������ǾͰ���ɾ��
				if(outputImage.exists()){
					outputImage.delete();
				}
				outputImage.createNewFile();
				}catch(IOException e){
					e.printStackTrace();
				}
				//��file����ת��Ϊuri����
				imageUri = Uri.fromFile(outputImage);
				//��intent��actionָ��Ϊandroid.media.action.IMAGE_CAPTURE
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				//ָ��ͼƬ�������ַ
				intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
				//�������
				startActivityForResult(intent,TAKE_PHOTO);
			}
		});
	}
	/*
	 * ���������Activity�еõ��´�Activity�رպ󷵻ص����ݣ�
	 * ����Ҫʹ��ϵͳ�ṩ��startActivityForResult(Intent intent,int requestCode)����
	 * ���µ�Activity���µ�Activity�رպ����ǰ���Activity�������ݣ�Ϊ�˵õ����ص����ݣ�
	 * �������ǰ���Activity����дonActivityResult(int requestCode, int resultCode,Intent data)����
	 */
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		switch(requestCode){
		case TAKE_PHOTO:
			if(resultCode==RESULT_OK){
				Intent intent = new Intent("com.android.camera.action.CROP");
				intent.setDataAndType(imageUri, "image/*");
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				//�����ü�����
				startActivityForResult(intent,CROP_PHOTO);
			}
			break;
		case CROP_PHOTO:
			if(resultCode==RESULT_OK){
				try {
					//��ͼƬ������Bitmap����
					Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
					picture.setImageBitmap(bitmap);//���ü������Ƭ��ʾ����
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
