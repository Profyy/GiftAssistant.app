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
import com.google.firebase.example.fireeats.java.model.Invitee;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Dialog Fragment containing rating form.
 */
public class InviteDialogFragment extends DialogFragment {

    public static final String TAG = "InviteDialog";

    private FirebaseFirestore mFirestore;

    @BindView(R.id.inviteName)
    EditText mInviteName;

    @BindView(R.id.inviteEmail)
    EditText mInviteEmail;

    interface InviteListener {

        void onInvite(Invitee invitee);

    }

    private InviteListener mInviteListener;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_invite, container, false);
        unbinder = ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof InviteListener) {
            mInviteListener = (InviteListener) context;
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

    @OnClick(R.id.inviteFormButton)
    public void onSubmitClicked(View view) {
        Bundle args = getArguments();
        String eventId = args.getString("eventId");

        mFirestore = FirebaseFirestore.getInstance();
        WriteBatch batch = mFirestore.batch();
        DocumentReference restRef = mFirestore.collection("events").document(eventId).collection("invited").document();

        Invitee invitee = new Invitee(mInviteName.getText().toString(), mInviteEmail.getText().toString());

        batch.set(restRef, invitee);

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

        DocumentReference eventRef = mFirestore.collection("events").document(eventId);
        eventRef.update("invited", FieldValue.arrayUnion(mInviteEmail.getText().toString()));

        dismiss();
    }

    @OnClick(R.id.inviteFormCancel)
    public void onCancelClicked(View view) {
        dismiss();
    }
}
