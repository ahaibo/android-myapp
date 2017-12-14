package hai.com.myapp.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

import hai.com.myapp.model.Contact;

/**
 * Created by Administrator on 2017/6/8.
 */

public class ContactUtil {
    private static final String RAW_CONTACTS_URL = "content://com.android.contacts/raw_contacts";
    private static final String CONTACTS_DATA_URL = "content://com.android.contacts/data";

    public static ArrayList<Contact> getContacts(ContentResolver resolver) {
        ArrayList<Contact> list = new ArrayList<>();

        Uri rawContactUri = Uri.parse(RAW_CONTACTS_URL);
        Uri dataUri = Uri.parse(CONTACTS_DATA_URL);

        //先查询 raw_contacts 找到 contact_id
        String[] projection = {"contact_id"};
        Cursor cursor = resolver.query(rawContactUri, projection, null, null, null);
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(0);

            //根据查询到的contactId去data表查询具体信息
            String[] dataProjection = {"mimetype", "data1"};
            String selection = "raw_contact_id=?";
            String[] selectionArgs = {contactId};
            Cursor dataCursor = resolver.query(dataUri, dataProjection, selection, selectionArgs, null);

            //testMimeTypeColumn(resolver, new String[]{contactId});

            Contact contact = new Contact();
            while (dataCursor.moveToNext()) {
                String type = dataCursor.getString(0);
                String result = dataCursor.getString(1);
                switch (type) {
                    case "vnd.android.cursor.item/phone_v2":
                        contact.setPhone(result);
                        break;
                    case "vnd.android.cursor.item/email_v2":
                        contact.setEmail(result);
                        break;
                    case "vnd.android.cursor.item/name_v2":
                        contact.setName(result);
                        break;
                    case "vnd.android.cursor.item/postal-address_v2":
                        contact.setAddress(result);
                        break;
                }
                list.add(contact);
            }
        }
        return list;
    }

    private static String testMimeTypeColumn(ContentResolver resolver, String[] selectionArgs) {
        Uri dataUri = Uri.parse(CONTACTS_DATA_URL);
        String selection = "raw_contact_id=?";
        Cursor cursor = resolver.query(dataUri, null, selection, selectionArgs, null);
        cursor.moveToNext();

        String mimeColumnName = null;
        int columns = cursor.getColumnCount();
        for (int i = 0; i < columns; i++) {
            String columnName = cursor.getColumnName(i);
            if (columnName.contains("mime")) {
                System.out.println(columnName);
                mimeColumnName = columnName;
            }
        }

        return mimeColumnName;
    }


}
