package com.stridera.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


public class EditItemActivity extends ActionBarActivity {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EditText etEditField = (EditText) findViewById(R.id.etEditItem);
        Spinner priorities = (Spinner) findViewById(R.id.priorities);

        Intent intent = getIntent();

        position = intent.getIntExtra("position", 0);

        String item = intent.getStringExtra("item");
        int priority = intent.getIntExtra("priority", 0);

        etEditField.setText(item);
        priorities.setSelection(priority - 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemSave(View v) {
        EditText etEditField = (EditText) findViewById(R.id.etEditItem);
        Spinner priorities = (Spinner) findViewById(R.id.priorities);
        int priority = priorities.getSelectedItemPosition() + 1;

        Intent data = new Intent();
        data.putExtra("item", etEditField.getText().toString());
        data.putExtra("priority", priority);
        data.putExtra("position", position);
        setResult(RESULT_OK, data);
        finish();
    }
}
