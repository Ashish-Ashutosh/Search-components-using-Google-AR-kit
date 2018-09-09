package com.example.asus.ar_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asus.ar_application.TableData.TableInfo;

import java.util.ArrayList;

public class DatabaseOperations extends SQLiteOpenHelper {
	public static final int database_version = 1;
	public String CREATE_QUERY = "CREATE TABLE "+ TableInfo.TABLE_NAME+"("+ TableInfo.COMPONENT_NAME+" TEXT," + TableInfo.COMPONENT_DESC+" TEXT,"+ TableInfo.BOX_REF+" INTEGER,"+ TableInfo.X_CORD+" INTEGER,"+ TableInfo.Y_CORD+" INTEGER);";
	//public String CREATE_QUERY = "CREATE TABLE "+TableInfo.TABLE_NAME+"("+TableInfo.COMPONENT_NAME+" TEXT,"+TableInfo.COMPONENT_DESC+" TEXT,"+TableInfo.BOX_REF+" TEXT);";

	public DatabaseOperations(Context context) {
		super(context, TableInfo.DATABASE_NAME, null, database_version);
		Log.d("Database operations", "Database created");
		
	}

	@Override
	public void onCreate(SQLiteDatabase sdb) {
		
		sdb.execSQL(CREATE_QUERY);
		Log.d("Database operations", "Table created");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public void putInformation(DatabaseOperations dop, String name, String description, String ref, int X_coord, int Y_cood)
	{
	SQLiteDatabase SQ = dop.getWritableDatabase();
	ContentValues cv = new ContentValues();
	cv.put(TableInfo.COMPONENT_NAME, name);
	cv.put(TableInfo.COMPONENT_DESC, description);
	cv.put(TableInfo.BOX_REF, ref);
	cv.put(TableInfo.X_CORD, X_coord);
	cv.put(TableInfo.Y_CORD, Y_cood);
	long k = SQ.insert(TableInfo.TABLE_NAME, null, cv);
	Log.d("Database operations", "One raw inserted");
	}
	
	public Cursor getInformation(DatabaseOperations dop, String itemName)
	{
		SQLiteDatabase SQ = dop.getReadableDatabase();
		String[] coloumn = {TableInfo.COMPONENT_DESC, TableInfo.BOX_REF, TableInfo.X_CORD, TableInfo.Y_CORD};
		Cursor CR = SQ.query(TableInfo.TABLE_NAME,coloumn, TableInfo.COMPONENT_NAME + " =? ", new String[] {itemName}, null, null, null);
		return CR;
	}

	public ArrayList<String> getAllNames(DatabaseOperations dop)
	{
		ArrayList<String> allNames = new ArrayList<String>();
		SQLiteDatabase SQ = dop.getReadableDatabase();
		String[] coloumns = {TableInfo.COMPONENT_NAME};
		Cursor CR = SQ.query(TableInfo.TABLE_NAME,coloumns, null, null, null, null, null);

		if (CR.getCount() > 0){
			CR.moveToFirst();
			while(!CR.isAfterLast()){
				allNames.add(CR.getString(0));
				CR.moveToNext();
			}
		}
		CR.close();

		return allNames;
	}
}
