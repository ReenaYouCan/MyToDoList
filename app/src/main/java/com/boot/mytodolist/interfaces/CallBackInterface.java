package com.boot.mytodolist.interfaces;

/**
 * Created by Reena on 2/9/2017.
 */

import com.boot.mytodolist.model.ToDoModel;

/**
 * To get callback from activity to Database changes
 */
public interface CallBackInterface {
    //Call when delete item from list
    void onDelete(int pos);
    //call after item adding to list
    void onAdd(ToDoModel model);
}
