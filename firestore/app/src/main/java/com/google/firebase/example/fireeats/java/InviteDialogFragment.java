package com.google.firebase.example.fireeats.java;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.model.Invitee;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Dialog Fragment containing rating form.
 */
public class InviteDialogFragment extends DialogFragment {

    public static final String TAG = "InviteDialog";

    @BindView(R.id.inviteFormText)
    EditText mInviteText;

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
        Invitee invitee = new Invitee(mInviteText.getText().toString());

        if (mInviteListener != null) {
            mInviteListener.onInvite(invitee);
        }

        dismiss();
    }

    @OnClick(R.id.inviteFormCancel)
    public void onCancelClicked(View view) {
        dismiss();
    }
}
