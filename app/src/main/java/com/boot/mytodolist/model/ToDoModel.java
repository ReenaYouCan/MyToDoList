package com.boot.mytodolist.model;

import java.io.Serializable;

/**
 * Created by RA on 2/9/2017.
 * The Plain old java class used to assign getter setters for marshaling or unmarshling,
 * here no such requirement to modify Serialization so using Serializable interface instead of parcelable
 */

public class ToDoModel implements Serializable {

    String todoText;
    int priority;
    int srNo;

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }
}
