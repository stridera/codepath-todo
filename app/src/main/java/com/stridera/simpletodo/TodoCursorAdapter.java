package com.stridera.simpletodo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by mjones on 4/16/15.
 */
public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);

        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        int priority = cursor.getInt(cursor.getColumnIndexOrThrow("priority"));
        String priorityString;
        switch (priority) {
            case 1: priorityString = "High"; break;
            case 2: priorityString = "Medium"; break;
            case 3: priorityString = "Low"; break;
            default: priorityString = "Unknown " + String.valueOf(priority);
        }
        tvTitle.setText(title);
        tvPriority.setText(priorityString);
    }
}
