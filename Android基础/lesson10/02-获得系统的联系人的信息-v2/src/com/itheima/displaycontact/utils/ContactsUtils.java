package com.itheima.displaycontact.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.itheima.displaycontact.domain.ContactInfo;

public class ContactsUtils {

	public static List<ContactInfo> displayContact(Context context) {

		List<ContactInfo> list = new ArrayList<ContactInfo>();

		Uri contact_uri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		Uri data_uri = Uri.parse("content://com.android.contacts/data");

		// 获得 与后门程序打交道 的 resolver对象
		ContentResolver resolver = context.getContentResolver();

		Cursor contact_cursor = resolver.query(contact_uri,
				new String[] { "contact_id" }, null, null, null);

		while (contact_cursor.moveToNext()) {

			String id = contact_cursor.getString(0);
			if (id != null) {

				ContactInfo info = new ContactInfo();
				Cursor dataCursor = resolver.query(data_uri, new String[] {
						"data1", "mimetype" }, "raw_contact_id=?",
						new String[] { id }, null);

				while (dataCursor.moveToNext()) {

					String data1 = dataCursor.getString(0);
					String type = dataCursor.getString(1);

					if ("vnd.android.cursor.item/name".equals(type)) {
						info.setName(data1);
					} else if ("vnd.android.cursor.item/email_v2".equals(type)) {
						info.setEmail(data1);
					} else if ("vnd.android.cursor.item/im".equals(type)) {
						info.setQq(data1);
					} else if ("vnd.android.cursor.item/phone_v2".equals(type)) {
						info.setPhone(data1);
					}

				}
				list.add(info);
				dataCursor.close();
			}
		}
		contact_cursor.close();
		return list;
	}

}
