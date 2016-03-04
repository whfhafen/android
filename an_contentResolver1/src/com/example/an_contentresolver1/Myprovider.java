package com.example.an_contentresolver1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class Myprovider extends ContentProvider{
	public static final int TABLE1_DIR = 0;
	public static final int TABLE1_ITEM = 1;
	public static final int TABLE2_DIR = 2;
	public static final int TABLE2_ITEM = 3;
	private static UriMatcher uriMatcher;
	//对UriMatcher进行初始化操作  添加几条希望匹配的uri格式
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("com.example.an_contentresolver1.provider", "table1", TABLE1_DIR);
		uriMatcher.addURI("com.example.an_contentresolver1.provider", "table1/#", TABLE1_ITEM);
		uriMatcher.addURI("com.example.an_contentresolver1.provider", "table2", TABLE2_DIR);
		uriMatcher.addURI("com.example.an_contentresolver1.provider", "table2/#", TABLE2_ITEM);

	}
	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		switch(uriMatcher.match(uri)){
		case TABLE1_DIR:
			break;
		case TABLE1_ITEM:
			break;
		case TABLE2_DIR:
			break;
		case TABLE2_ITEM:
			break;
		default:
			break;
		}
		return null;
	}
	

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)){
		case TABLE1_DIR:
			return "vnd.android.cursor.dir/vnd.com.example.an_contentresolver1.provider";
		case TABLE1_ITEM:
			return "vnd.android.cursor.item/vnd.com.example.an_contentresolver1.provider";
		case TABLE2_DIR:
			return "vnd.android.cursor.dir/vnd.com.example.an_contentresolver1.provider";
		case TABLE2_ITEM:
			return "vnd.android.cursor.item/vnd.com.example.an_contentresolver1.provider";
		default:
			break;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
