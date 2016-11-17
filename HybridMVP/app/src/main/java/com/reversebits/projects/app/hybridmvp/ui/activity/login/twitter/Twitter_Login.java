package com.reversebits.projects.app.hybridmvp.ui.activity.login.twitter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.reversebits.projects.app.hybridmvp.common.FirebaseCommonClass;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by TapanHP on 11/14/2016.
 */

class Twitter_Login extends FirebaseCommonClass {

    private Activity context;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private UserResponse response;

    private static final String TAG = "Twitter_Login";

    public Twitter_Login(Activity context) {
        this.context = context;
    }

    void initFirebase()
    {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.e(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.e(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };


    }

    private void signOut()
    {
        FirebaseAuth.getInstance().signOut();
    }


    @Override
    public void addAuth() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void removeAuth() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    protected UserResponse handleTwitterSession(TwitterSession session, final String email) {
        Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> authResultTask) {
                        Log.e(TAG, "signInWithCredential:onComplete:" + authResultTask.isSuccessful());

                        Log.e(TAG, "user:" + authResultTask.getResult().getUser().getUid() + "  :  " +  authResultTask.getResult().getUser().getDisplayName() + " : "
                              +  authResultTask.getResult().getUser().getEmail() + " :  "+  authResultTask.getResult().getUser().getPhotoUrl());

                        authResultTask.getResult().getUser().updateEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.e(TAG, "User email address updated.");

                                            response = new UserResponse(authResultTask.getResult().getUser().getUid(),
                                                    authResultTask.getResult().getUser().getDisplayName(),
                                                    email,
                                                    authResultTask.getResult().getUser().getPhotoUrl().toString());

                                        }
                                    }
                                });

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!authResultTask.isSuccessful()) {
                            Log.e(TAG, "signInWithCredential", authResultTask.getException());
                        }
                    }
                });
        return response;
    }
}
