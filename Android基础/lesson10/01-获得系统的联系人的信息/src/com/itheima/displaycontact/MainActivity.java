package com.itheima.displaycontact;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void displayContacts(View v){
		
		// 读取 联系人的信息:  raw_contact , data
		// provider的 authorities: com.android.contacts
		
		// 暗号 :raw_contacts
		// 暗号 : data
		
		
		Uri contact_uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri data_uri    = Uri.parse("content://com.android.contacts/data");
		
		// 获得与 后门程序打交道的 Resolver对象
		ContentResolver resolver = getContentResolver();
		
		// select contact_id from raw_contact;
		Cursor contacts_cursor = resolver.query(contact_uri, new String[]{"contact_id"}, null, null, null);
		while(contacts_cursor.moveToNext()){
			
			String id = contacts_cursor.getString(0);
			
			System.out.println("id :" + id);
			//根据 id 去查询  data, 只要 是 id 相同的 data的数据, 就都是同一个  联系人的信息
			Cursor dataCursor = resolver.query(data_uri, new String[]{"data1","mimetype"}, "raw_contact_id=?", new String[]{id}, null);
			
			while(dataCursor.moveToNext()){
				
				String data = dataCursor.getString(0);
				String type = dataCursor.getString(1);
				System.out.println("data :" + data);
				System.out.println("type :" + type);
			}
			
			dataCursor.close();
			System.out.println("===========");
		}
		contacts_cursor.close();
		
	}

}
