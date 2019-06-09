package com.google.firebase.example.fireeats.java;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.model.Gift;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Dialog Fragment containing rating form.
 */
public class GiftDialogFragment extends DialogFragment {

    public static final String TAG = "GiftDialog";

    private FirebaseFirestore mFirestore;

    @BindView(R.id.giftDescription)
    EditText mGiftDescription;

    @BindView(R.id.giftUrl)
    EditText mGiftUrl;

    interface GiftListener {

        void onGift(Gift gift);

    }

    private GiftListener mGiftListener;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_gift, container, false);
        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof GiftListener) {
            mGiftListener = (GiftListener) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @OnClick(R.id.giftFormButton)
    public void onSubmitClicked(View view) {
        Bundle args = getArguments();
        String eventId = args.getString("eventId");

        mFirestore = FirebaseFirestore.getInstance();
        WriteBatch batch = mFirestore.batch();
        DocumentReference restRef = mFirestore.collection("events").document(eventId).collection("gifts").document();

        Gift gift = new Gift(mGiftDescription.getText().toString(), mGiftUrl.getText().toString(), false);

        batch.set(restRef, gift);

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

        dismiss();
    }

    @OnClick(R.id.giftFormCancel)
    public void onCancelClicked(View view) {
        dismiss();
    }
}
