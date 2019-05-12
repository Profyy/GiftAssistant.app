package com.google.firebase.example.fireeats.java.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.model.Gift;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftsAdapter extends FirestoreAdapter<GiftsAdapter.ViewHolder> {

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
        holder.bind(getSnapshot(position).toObject(Gift.class));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private static final SimpleDateFormat FORMAT  = new SimpleDateFormat(
                "MM/dd/yyyy", Locale.US);

        @BindView(R.id.description)
        TextView mDescription;

        @BindView(R.id.url)
        TextView mUrl;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Gift gift) {
            mDescription.setText(gift.getDescription());
            mUrl.setText(gift.getUrl());
        }
    }
}
