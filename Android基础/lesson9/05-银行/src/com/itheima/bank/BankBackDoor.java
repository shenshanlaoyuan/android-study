package com.itheima.bank;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;


public class BankBackDoor extends ContentProvider {

	private static final int SUCCESS = 1;

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 *   后门 程序 需要有一个 保安 , 在 有程序 去访问 这个  后门程序的时候, 检查一下 是否具有 足够的权限 
	 *  	
	 *  	URiMatcher这 个对象 
	 *  
	 *  
	 */
	
	// matcher ,当  与 传递进来的 uri 进行 匹配, 看 有没有 正确的暗号 , 保安 就去干活了 , 如果 匹配 正确, 就  正常通过 去操作 
	// 后门程序, 否则, 就 会是 NO_MATCH
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	static{
		
		matcher.addURI("com.itheima.db", "accounts", SUCCESS);
	}
	
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		int result = matcher.match(uri);
		
		if(SUCCESS==result){
			//暗号 对了 
			System.out.println("使用后门 程序 修改 了 数据库 中的数据 ");
		}else{
			
			//说明 暗号 不对 
			throw new RuntimeException("暗号 不对,  滚 犊子...");
		}
		
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
