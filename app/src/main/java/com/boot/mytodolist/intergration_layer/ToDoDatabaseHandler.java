package com.boot.mytodolist.intergration_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.boot.mytodolist.model.ToDoModel;
import com.boot.mytodolist.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reena on 2/9/2017.
 */

public class ToDoDatabaseHandler extends SQLiteOpenHelper {

    /**
     * Default Constructor
     *
     * @param context
     */
    public ToDoDatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    /**
     * Creating Table by using overriden method of sqlite helper
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TODO_TABLE);
    }

    /**
     * Update database if necessary based on database versions
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Write code to update database
        // Drop older table if existed
        db.execSQL(Constants.DROP_QUERY);
        // Create tables again
        onCreate(db);
    }

    /**
     * Add Single ToDo
     *
     * @param model
     */
    public synchronized void addTodo(ToDoModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_TODO_TEXT, model.getTodoText());
        values.put(Constants.COLUMN_TODO_PRIORITY, model.getPriority());
        // Inserting Row
        db.insert(Constants.TABLE_TODO_LIST, null, values);
        //2nd argument is String containing nullColumnHack
        Log.e(getClass().getSimpleName(),"Success");
        db.close(); // Closing database connection
    }


    /**
     * Get All ToDo List
     * @return
     */
    public ArrayList<ToDoModel> getAllDoTo() {
        ArrayList<ToDoModel> todoList = new ArrayList<>();
        // Select All Query

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Constants.SELECT_QUERY, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDoModel model = new ToDoModel();
                model.setSrNo(cursor.getInt(0));
                model.setTodoText(cursor.getString(1));
                model.setPriority(cursor.getInt(2));
                todoList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return todoList;
    }

    /**
     * Delete Single TODO from list
     * @param model
     */
    public void deleteTodo(ToDoModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_TODO_LIST, Constants.COLUMN_TODO_SR_NUMBER + " = ?",
                new String[] { String.valueOf(model.getSrNo()) });
        db.close();
    }
}
