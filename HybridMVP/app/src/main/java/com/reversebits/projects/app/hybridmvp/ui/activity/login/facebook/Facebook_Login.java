package com.reversebits.projects.app.hybridmvp.ui.activity.login.facebook;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/14/2016.
 */

class Facebook_Login {

    Activity context;

    UserResponse response;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "Facebook_Login";

    public Facebook_Login(Activity context) {
        mAuth = FirebaseAuth.getInstance();
        this.context = context;
    }

    void initFirebase() {
// Initialize Firebase Auth
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid() + user.getDisplayName() + user.getEmail() + user.getPhotoUrl());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }


    void addAuth() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    void removeAuth() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    protected UserResponse handleFacebookAccessToken(AccessToken token) {
        Log.e(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        response= new UserResponse(task.getResult().getUser().getUid(),
                                task.getResult().getUser().getDisplayName(), task.getResult().getUser().getEmail(), task.getResult().getUser().getPhotoUrl().toString());

                        if (!task.isSuccessful()) {
                            Log.e(TAG, "signInWithCredential", task.getException());
                        }

                    }
                });
        return response;
    }


    public void signout() {
        FirebaseAuth.getInstance().signOut();
    }
}
