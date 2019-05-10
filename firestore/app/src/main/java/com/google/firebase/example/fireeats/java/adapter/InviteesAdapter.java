package com.google.firebase.example.fireeats.java.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.model.Invitee;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteesAdapter  extends FirestoreAdapter<InviteesAdapter.ViewHolder> {

    public InviteesAdapter(Query query) {
        super(query);
    }


    @Override
    public InviteesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InviteesAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_invitees, parent, false));
    }

    @Override
    public void onBindViewHolder(InviteesAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position).toObject(Invitee.class));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private static final SimpleDateFormat FORMAT  = new SimpleDateFormat(
                "MM/dd/yyyy", Locale.US);

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.email)
        TextView email;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Invitee invitee) {
            name.setText(invitee.getName());
            email.setText(invitee.getEmail());
        }
    }
}
