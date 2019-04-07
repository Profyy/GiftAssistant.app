package com.google.firebase.example.fireeats.java;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.model.Event;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.Calendar;

public class NewItemActivity extends BaseActivity {

    final Calendar myCalendar = Calendar.getInstance();

    private static final String TAG = "NewItemActivity";
    private static final String REQUIRED = "Required";

    private FirebaseFirestore mFirestore;

    private Spinner spinner;
    private EditText mHost;
    private EditText mDate;
    private EditText mTime;
    private FloatingActionButton mSubmitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        // Firestore
        mFirestore = FirebaseFirestore.getInstance();


        // Type
        Context context=getApplicationContext();
        String[] categories = context.getResources().getStringArray(R.array.categories);
        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,categories
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner = (Spinner) findViewById(R.id.spinnerCategory);
        spinner.setAdapter(spinnerArrayAdapter);

        // Host
        mHost = findViewById(R.id.host);

        // Date
        mDate = findViewById(R.id.fieldDate);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });


        // Time
        mTime = findViewById(R.id.fieldTime);
        mTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });

        //Submit
        mSubmitButton = findViewById(R.id.fabSubmitNewItem);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateNewEventClicked();
            }
        });
    }

    private void onCreateNewEventClicked() {
        String type = spinner.getSelectedItem().toString();
        String host = mHost.getText().toString();
        String date = mDate.getText().toString();
        String time = mTime.getText().toString();

        WriteBatch batch = mFirestore.batch();
        DocumentReference restRef = mFirestore.collection("events").document();

        Event event = new Event(type, host, date, time);


        // Add event
        batch.set(restRef, event);

        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Write batch succeeded.");
                } else {
                    Log.w(TAG, "write batch failed.", task.getException());
                }
            }
        });
    }
}
