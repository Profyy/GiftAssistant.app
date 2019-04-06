package com.google.firebase.example.fireeats.java;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    private EditText mDate;
    private FloatingActionButton mSubmitButton;
    private Button mSetDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        mDate = findViewById(R.id.fieldDate);
        mSubmitButton = findViewById(R.id.fabSubmitNewItem);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateNewEventClicked();
            }
        });


        mSetDateButton = findViewById(R.id.setDateButton);
        mSetDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });
    }

    private void onCreateNewEventClicked() {
        WriteBatch batch = mFirestore.batch();
        DocumentReference restRef = mFirestore.collection("events").document();

        Event event = new Event("birthday");

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
