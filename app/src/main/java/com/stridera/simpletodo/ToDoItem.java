package com.stridera.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Collection;

/**
 * Manage the items via SQLite
 */
@Table(name = "ToDoItem")
public class ToDoItem extends Model {

    @Column(name="id", key = true, unique = true)
    public long id;

    @Column(name = "title")
    public String Title;

    @Column(name="priority")
    public int Priority;

    public ToDoItem() {
        super();
    }

    public ToDoItem(int id, String title) {
        super();
        this.Title = title;
        this.Priority = 3;
    }


    public static Collection<? extends String> loadAll() {
        return null;
    }
}
