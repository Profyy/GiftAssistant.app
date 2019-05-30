package com.google.firebase.example.fireeats.java.viewmodel;

import android.arch.lifecycle.ViewModel;

/**
 * ViewModel for {@link com.google.firebase.example.fireeats.MainActivity}.
 */

public class MainActivityViewModel extends ViewModel {

    private boolean mIsSigningIn;

    public MainActivityViewModel() {
        mIsSigningIn = false;
    }

    public boolean getIsSigningIn() {
        return mIsSigningIn;
    }

    public void setIsSigningIn(boolean mIsSigningIn) {
        this.mIsSigningIn = mIsSigningIn;
    }

}
