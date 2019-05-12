package com.google.firebase.example.fireeats.java;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.example.fireeats.java.adapter.GiftsAdapter;
import com.google.firebase.example.fireeats.java.model.Event;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventDatailActivity extends AppCompatActivity
        implements EventListener<DocumentSnapshot> {

    private static final String TAG = "EventDetail";

    public static final String KEY_EVENT_ID = "key_event_id";

    @BindView(R.id.eventImage)
    ImageView mImageView;

    @BindView(R.id.host)
    TextView mHostView;

    @BindView(R.id.eventCity)
    TextView mCityView;

    @BindView(R.id.eventType)
    TextView mTypeView;

    @BindView(R.id.restaurantPrice)
    TextView mPriceView;

    @BindView(R.id.viewEmptyRatings)
    ViewGroup mEmptyView;

    @BindView(R.id.recyclerRatings)
    RecyclerView mRatingsRecycler;

    private FirebaseFirestore mFirestore;
    private FirebaseUser user;
    private String email;

    private DocumentReference mEventRef;
    private ListenerRegistration mEventRegistration;
    private GiftDialogFragment mGiftsDialog;


    private CollectionReference mGiftsRef;

    private GiftsAdapter mGiftsAdapter;

    private String eventId;
    private boolean isOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);

        // Get event ID from extras
        eventId = getIntent().getExtras().getString(KEY_EVENT_ID);
        if (eventId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_EVENT_ID);
        }

        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        //Firebase user
        user = FirebaseAuth.getInstance().getCurrentUser();

        // Get reference to the event
        mEventRef = mFirestore.collection("events").document(eventId);
        mGiftsDialog = new GiftDialogFragment();
        Bundle args = new Bundle();
        args.putString("eventId", eventId);
        mGiftsDialog.setArguments(args);
        mGiftsRef = mEventRef.collection("gifts");

        // Get invitees
        Query giftsQuery = mGiftsRef.limit(50);

        isOwner=true;
        // RecyclerView
        mGiftsAdapter = new GiftsAdapter(giftsQuery, isOwner) {
            @Override
            protected void onDataChanged() {
                if (getItemCount() == 0) {
                    mRatingsRecycler.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRatingsRecycler.setVisibility(View.VISIBLE);
                    mEmptyView.setVisibility(View.GONE);
                }
            }
        };
        mRatingsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRatingsRecycler.setAdapter(mGiftsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mGiftsAdapter.startListening();
        mEventRegistration = mEventRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mGiftsAdapter.stopListening();
        if (mEventRegistration != null) {
            mEventRegistration.remove();
            mEventRegistration = null;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    @Override
    public void onEvent(DocumentSnapshot snapshot, FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "event:onEvent", e);
            return;
        }

        onEventLoaded(snapshot.toObject(Event.class));
    }

    @SuppressLint("RestrictedApi")
    private void onEventLoaded(Event event) {
        mHostView.setText(event.getName());
        mCityView.setText(event.getCity());
        mTypeView.setText(event.getType());
        FloatingActionButton fabInvitees = (FloatingActionButton)findViewById(R.id.fabInvitees);
        FloatingActionButton fabShowGiftDialog = (FloatingActionButton)findViewById(R.id.fabShowGiftDialog);
        if(!event.getEmail().equals( user.getEmail() )) {
            fabInvitees.setVisibility(View.GONE);
            fabShowGiftDialog.setVisibility(View.GONE);
        }

        // Background image
        Glide.with(mImageView.getContext())
                .load(event.getPhoto())
                .into(mImageView);
    }

    @OnClick(R.id.fabInvitees)
    public void onInviteClicked(View view) {
        Intent intent = new Intent(this, InviteesActivity.class);
        intent.putExtra(InviteesActivity.KEY_EVENT_ID, eventId);
        startActivity(intent);
    }

    @OnClick(R.id.fabShowGiftDialog)
    public void onAddGiftClicked(View view) {
        mGiftsDialog.show(getSupportFragmentManager(), GiftDialogFragment.TAG);
    }


    @OnClick(R.id.restaurantButtonBack)
    public void onBackArrowClicked(View view) {
        onBackPressed();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
