package com.boot.mytodolist.utils;

/**
 * Created by RA on 2/9/2017.
 */

public class Constants {

    //Database Constants

    public static final String DATABASE_NAME = "table_todo_list";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_TODO_LIST = "table_todo_list";
    public static final String COLUMN_TODO_TEXT = "column_todo_text";
    public static final String COLUMN_TODO_PRIORITY = "column_todo_priority";
    public static final String COLUMN_TODO_SR_NUMBER = "column_todo_sr_number";

    //Create Table Query
    public static String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO_LIST + "("
            + COLUMN_TODO_SR_NUMBER  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TODO_TEXT + " TEXT,"
            + COLUMN_TODO_PRIORITY + " INTEGER" + ")";

    //Select all query
    public static String SELECT_QUERY = "SELECT  * FROM " + Constants.TABLE_TODO_LIST;

    //Drop Table on version update
    public static String DROP_QUERY = "DROP TABLE IF EXISTS " + Constants.CREATE_TODO_TABLE;


}
