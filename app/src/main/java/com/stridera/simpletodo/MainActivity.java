package com.stridera.simpletodo;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends ActionBarActivity implements AddItemDialog.AddItemDialogListener {
    ArrayList<ToDoItem> items;
    ArrayAdapter<ToDoItem> itemsAdapter;
    ListView lvItems;

    static final int REQUEST_CODE = 1; // Hash this when it matters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = ToDoItem.loadAll();
        itemsAdapter = new TodoAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    @Override
    public void onFinishEditDialog(String title, int priority, Date date) {
        ToDoItem item = new ToDoItem(title, priority);
        item.save();
        itemsAdapter.add(item);
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View view, int position, long id) {
                        items.get(position).delete();
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        ToDoItem item = items.get(position);
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                        i.putExtra("id", item.getId());
                        i.putExtra("item", item.Title);
                        i.putExtra("priority", item.Priority);
                        i.putExtra("position", position);
                        startActivityForResult(i, REQUEST_CODE);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_new) {
            onAddNew();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onAddNew() {
        FragmentManager fm = getFragmentManager();
        AddItemDialog frag = new AddItemDialog();
        frag.show(fm, "fragment_add_item");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int position = data.getExtras().getInt("position");
            String editedItem = data.getExtras().getString("item");
            int priority = data.getExtras().getInt("priority");
            ToDoItem item = items.get(position);
            item.Title = editedItem;
            item.Priority = priority;
            item.save();
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Item edited.", Toast.LENGTH_SHORT).show();
        }
    }
}