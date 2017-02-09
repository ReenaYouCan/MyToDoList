package com.boot.mytodolist.interfaces;

/**
 * Created by RA on 2/9/2017.
 */

/**
 * To get callback from activity to Database changes
 */
public interface CallBackInterface {
    //Call when delete from list
    void onDelete();
    //call when after adding to list
    void onAdd();
}
