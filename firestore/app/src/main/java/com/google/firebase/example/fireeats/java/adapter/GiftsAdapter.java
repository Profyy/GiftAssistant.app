package com.google.firebase.example.fireeats.java.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.model.Gift;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftsAdapter extends FirestoreAdapter<GiftsAdapter.ViewHolder> {

    private Boolean isOwner;

    public GiftsAdapter(Query query) {
        super(query);
    }

    @Override
    public GiftsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GiftsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_gifts, parent, false));
    }

    @Override
    public void onBindViewHolder(GiftsAdapter.ViewHolder holder, int position) {
        DocumentSnapshot snapshot = getSnapshot(position);
        holder.bind(snapshot);
        Log.d("GiftsAdapter", "isOwner value is: " + isOwner );
    }

    public void isOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.description)
        TextView mDescription;

        @BindView(R.id.url)
        TextView mUrl;

        @BindView(R.id.btnReserve)
        AppCompatButton mbtnReserve;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot) {
            final DocumentReference docRef = snapshot.getReference();
            final Gift gift = snapshot.toObject(Gift.class);
            mDescription.setText(gift.getDescription());
            mUrl.setText(gift.getUrl());


            if(isOwner) {
                mbtnReserve.setText("DELETE");
                mbtnReserve.setBackgroundColor(Color.RED);
            } else {
                if(gift.isReserved()) {
                    mbtnReserve.setText("RESERVED");
                    mbtnReserve.setBackgroundColor(Color.GRAY);
                } else {
                    mbtnReserve.setText("RESERVE");
                    mbtnReserve.setBackgroundColor(Color.parseColor("#FF43C5A5"));
                }
            }

            mbtnReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isOwner) {
                        docRef.update(
                                "reserved", true,
                                "reservedBy", FirebaseAuth.getInstance().getCurrentUser().getEmail()
                                ).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(itemView.getContext(), "reserved", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}
