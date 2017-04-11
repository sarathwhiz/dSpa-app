package com.Whiz.vaishali.deSpa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.Whiz.vaishali.deSpa.setget_category.SetGetCart;

import java.util.ArrayList;

public class DatabaseClass extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "ContactTable.db";
	private static final int DATABASE_VERSION = 1;
	Context context;
	SQLiteDatabase mDatabase;

	private static final String TABLE_CONTACTS = "table_contacts";

	private static final String ID = "c_id";
	private static final String NAME = "c_name";
	private static final String PHONE_NO = "c_phone_no";

	CustomAdapterCart custcart;

	public DatabaseClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		// TODO Auto-generated constructor stub

		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		mDatabase = db;

		Toast.makeText(context, "oncreate", Toast.LENGTH_LONG).show();

		String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT,"
				+ PHONE_NO + " TEXT " + ")";

		db.execSQL(CREATE_TABLE_CONTACTS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		mDatabase = db;
		onCreate(db);

	}

	void addContacts(SetGetCart contact) {
		mDatabase = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(NAME, contact.getName_cart());
		values.put(PHONE_NO, contact.getPrice_cart());

		// Inserting Row
		mDatabase.insert(TABLE_CONTACTS, null, values);

		mDatabase.close();

	}

	public ArrayList<SetGetCart> getContacts() {
		ArrayList<SetGetCart> contactList = new ArrayList<SetGetCart>();
		mDatabase = this.getWritableDatabase();

		// Select All Query

		Cursor cursor = mDatabase.rawQuery("select * from " + TABLE_CONTACTS,
				null);

		if (cursor.moveToFirst()) {

			do {
				SetGetCart contact = new SetGetCart();
				contact.setId(cursor.getInt(0));
				contact.setName_cart(cursor.getString(1));
				contact.setPrice_cart(cursor.getString(2));

				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		mDatabase.close();
		return contactList;
	}

	 public void deleteContacts(int id) {

	 String deleteSQL = "DELETE FROM " + TABLE_CONTACTS + " where " + ID
	 + " = " + id + "";

	 mDatabase = getReadableDatabase();
	 mDatabase.execSQL(deleteSQL);
	 mDatabase.close();

	 }

	public void deleteAll()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS,null,null);
		db.close();
	}

//	public void deleteAll(SetGetCart setGetCart) {
//
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.delete(TABLE_CONTACTS,null,null);
//		db.close();
//	}

}
