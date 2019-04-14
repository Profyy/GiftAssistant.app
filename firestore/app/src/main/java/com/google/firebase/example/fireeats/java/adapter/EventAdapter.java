package com.google.firebase.example.fireeats.java.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.model.Event;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventAdapter extends FirestoreAdapter<EventAdapter.ViewHolder> {
    public interface OnEventSelectedListener {

        void onEventSelected(DocumentSnapshot event);

    }

    private OnEventSelectedListener mListener;

    public EventAdapter(Query query, EventAdapter.OnEventSelectedListener listener) {
        super(query);
        mListener = listener;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new EventAdapter.ViewHolder(inflater.inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.eventHostName)
        TextView eventHostName;

        @BindView(R.id.eventItemType)
        TextView eventItemType;

        @BindView(R.id.eventItemDate)
        TextView eventItemDate;

        @BindView(R.id.eventItemTime)
        TextView eventItemTime;

        @BindView(R.id.eventItemCity)
        TextView eventItemCity;

        @BindView(R.id.eventItemCountry)
        TextView eventItemCountry;

        @BindView(R.id.eventItemImage)
        ImageView imageView;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnEventSelectedListener listener) {

            Event event = snapshot.toObject(Event.class);
            Resources resources = itemView.getResources();

            // Load image
            Glide.with(imageView.getContext())
                    .load(event.getPhoto())
                    .into(imageView);

            eventHostName.setText(event.getHost());
            eventItemType.setText(event.getType());
            eventItemDate.setText(event.getDate());
            eventItemTime.setText(event.getTime() + ",");
            eventItemCity.setText(event.getCity() + ",");
            eventItemCountry.setText(event.getCountry());

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onEventSelected(snapshot);
                    }
                }
            });
        }

    }
}
