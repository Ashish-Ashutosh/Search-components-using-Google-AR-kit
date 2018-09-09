package com.example.asus.ar_application;

import android.provider.BaseColumns;

public class TableData {
	
	public TableData()
	{
		
	}
	
	public static abstract class TableInfo implements BaseColumns
	{
		
	public static final String COMPONENT_NAME = "component_name"	;
	public static final	String COMPONENT_DESC  = "description";
	public static final String BOX_REF = "boxRef" ;
	public static final String X_CORD = "x_cord" ;
	public static final String Y_CORD = "y_cord" ;
	public static final String DATABASE_NAME = "componentsdb";
	public static final String TABLE_NAME = "componentstable";



		
	}

}
