package com.stridera.simpletodo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

public class AddItemDialog extends DialogFragment {
    private EditText etItemTitle;
    private Spinner spiPriorities;

    private int priority;
    private int day, month, year;

    public AddItemDialog() {
    }

    public interface AddItemDialogListener {
        void onFinishEditDialog(String title, int priority, Date date);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, parent);
        getDialog().setTitle("Add New Item");

        etItemTitle = (EditText) view.findViewById(R.id.etAddItemTitle);

        spiPriorities = (Spinner) view.findViewById(R.id.addPriorities);
        spiPriorities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priority = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etItemTitle.getText().toString();
                AddItemDialogListener dl = (AddItemDialogListener) getActivity();
                dl.onFinishEditDialog(title, priority, null);
                dismiss();
            }
        });

        return view;
    }
}
