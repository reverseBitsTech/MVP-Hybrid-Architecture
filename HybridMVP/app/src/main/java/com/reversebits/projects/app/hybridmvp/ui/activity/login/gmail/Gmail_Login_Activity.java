package com.reversebits.projects.app.hybridmvp.ui.activity.login.gmail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.reversebits.projects.app.hybridmvp.R;
import com.reversebits.projects.app.hybridmvp.common.BaseActivity;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/14/2016.
 */

public class Gmail_Login_Activity extends BaseActivity implements I_Gmail_Login_View{

    Gmail_Login_Presenter presenter;
    SignInButton loginButton;
    private Gmail_Login login;
    private UserResponse response;

    public Gmail_Login_Activity() {
        presenter = new Gmail_Login_Presenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_login);
        login = new Gmail_Login(Gmail_Login_Activity.this);
        login.initGoogle();
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        login.addAuth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        login.removeAuth();
    }

    private void init() {
        loginButton = (SignInButton)findViewById(R.id.btn_gmail_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.signIn();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Gmail_Login.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                response = login.firebaseAuthWithGoogle(account);

                if (response != null)
                    presenter.saveUserData(response);
            } else {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this,"Gmail login failed",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
