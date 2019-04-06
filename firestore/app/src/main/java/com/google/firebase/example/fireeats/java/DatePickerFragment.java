package com.google.firebase.example.fireeats.java;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.example.fireeats.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Create a new DatePickerDialog instance and return it
        /*
            DatePickerDialog Public Constructors - Here we uses first one
            public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth)
            public DatePickerDialog (Context context, int theme, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth)
         */
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Date date = new Date(year, month, day);

        SimpleDateFormat dayofWeekFormat = new SimpleDateFormat("EEE", Locale.US);
        String dayOfWeek = dayofWeekFormat.format(date);

        SimpleDateFormat monthNameFormat = new SimpleDateFormat("MMM", Locale.US);
        String monthName = monthNameFormat.format(date);

        //Do something with the date chosen by the user
        TextView tv = (TextView) getActivity().findViewById(R.id.fieldDate);

        String stringOfDate =  dayOfWeek + " " + day + "/" + monthName + "/" + year;
        tv.setText(stringOfDate);
    }
}
