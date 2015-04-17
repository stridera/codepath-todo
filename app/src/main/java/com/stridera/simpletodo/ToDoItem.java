package com.stridera.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;

/**
 * Manage the items via SQLite
 */
@Table(name = "ToDoItem")
public class ToDoItem extends Model {
    @Column(name = "title")
    public String Title;

    @Column(name="priority")
    public int Priority;

    public ToDoItem() {
        super();
    }

    public ToDoItem(String title) {
        this(title, 3);
    }

    public ToDoItem(String title, int priority) {
        super();
        this.Title = title;
        this.Priority = priority;
    }

    public static ArrayList<ToDoItem> loadAll() {
        return new Select().from(ToDoItem.class).execute();
    }
}
