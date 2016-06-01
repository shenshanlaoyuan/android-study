package com.itheima.messgehelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactListActivity extends Activity {
	
	String[] contacts ={
			
			"13987654321",
			"18217180124",
			"13687987653",
			"13687987222",
			"13687987555",
			"13687987666",
	};
	
	private ListView contacts_lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.contactslist);
		contacts_lv = (ListView) findViewById(R.id.contacts_lv);
		
		contacts_lv.setAdapter(new ArrayAdapter<String>(this, R.layout.contact_item, contacts));
		
		//添加一个 item 被点击的 监听器
		contacts_lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String contact = contacts[position];
				Intent intent = new Intent();
				intent.putExtra("contact", contact);
				setResult(0, intent);
				finish();
			}
		});
	}
}
