package com.itheima.displaycontact;

import java.util.List;

import com.itheima.displaycontact.domain.ContactInfo;
import com.itheima.displaycontact.utils.ContactsUtils;

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
		
		List<ContactInfo> list = ContactsUtils.displayContact(this);
		
		for (ContactInfo contactInfo : list) {
			System.out.println(contactInfo);
		}
		
	}

}
