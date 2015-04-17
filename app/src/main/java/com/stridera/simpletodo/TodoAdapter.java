package com.stridera.simpletodo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mjones on 4/16/15.
 */
public class TodoAdapter extends ArrayAdapter<ToDoItem> {
    public TodoAdapter(Context context, ArrayList<ToDoItem> toDoItem) {
        super(context, 0, toDoItem);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ToDoItem item = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }

        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);

        String title = item.Title;
        String priorityString;
        switch (item.Priority) {
            case 1: priorityString = "High"; break;
            case 2: priorityString = "Medium"; break;
            case 3: priorityString = "Low"; break;
            default: priorityString = "Unknown " + String.valueOf(item.Priority);
        }
        tvTitle.setText(title);
        tvPriority.setText(priorityString);

        return view;
    }
}
