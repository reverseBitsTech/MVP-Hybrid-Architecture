package com.reversebits.projects.app.hybridmvp.ui.activity.login.simplelogin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/10/2016.
 */

public class FirebaseLogin {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "FirebaseLogin";

    private Activity context;
    UserResponse response = new UserResponse();

    public FirebaseLogin(Activity context) {
        this.context = context;
        initValues();
    }


    public void initValues() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.e(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.e(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    public void addAuthListner() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    protected void removeAuthListner() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    UserResponse signIn(String mail, String pass) {
        mAuth.signInWithEmailAndPassword(mail, pass).
                addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e(TAG, "signInWithEmail:onTaskFailed:");

                        } else {
                            Log.e(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            response.setName(task.getResult().getUser().getDisplayName());
                            response.setPhotoUri(task.getResult().getUser().getPhotoUrl().toString());
                            response.setUserMail(task.getResult().getUser().getEmail());
                            response.setUserID(task.getResult().getUser().getUid());
                        }
                    }

                });
        return response;
    }

    public UserResponse signUp(String mail, String pass,String name , String photoUri) {
        mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e(TAG, "signUpWithEmail:onComplete:" + task.isSuccessful());

                //TODO : Database handling now
//                task.getResult().getUser().getUid()

                if (!task.isSuccessful()) {
                    Log.e(TAG, "signUpWithEmail:failed", task.getException());
                }
            }
        });
        return response;
    }
}

