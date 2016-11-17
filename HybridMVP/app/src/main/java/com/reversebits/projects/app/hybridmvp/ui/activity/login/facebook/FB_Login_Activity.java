package com.reversebits.projects.app.hybridmvp.ui.activity.login.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.reversebits.projects.app.hybridmvp.R;
import com.reversebits.projects.app.hybridmvp.common.BaseActivity;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/14/2016.
 */

public class FB_Login_Activity extends BaseActivity implements I_FB_Login_View {

    FB_Login_Presenter presenter;
    private CallbackManager callbackManager;
    LoginButton loginButton;

    private Facebook_Login login;

    public FB_Login_Activity() {
        presenter = new FB_Login_Presenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_login);
        init();
        login = new Facebook_Login(FB_Login_Activity.this);
        login.initFirebase();
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
    protected void onStop() {
        super.onStop();
        login.removeAuth();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    private void init() {

        loginButton = (LoginButton) findViewById(R.id.btn_login_fb);
        loginButton.setReadPermissions("email", "public_profile");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initFB();
            }
        });
    }

    void initFB() {
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                UserResponse response = login.handleFacebookAccessToken(loginResult.getAccessToken());
                presenter.saveUserData(response);
            }

            @Override
            public void onCancel() {
                // App code
                Log.e("FB_LOGIN_ACTIVITY", "onCancel: ");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.e("FB_LOGIN_ACTIVITY", "onError: ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(
                AccessToken oldAccessToken,
                AccessToken currentAccessToken) {
            if (currentAccessToken == null){
                //User logged out
                login.signout();
                presenter.clearUserData();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            try {
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }catch (Exception e)
            {
                e.getMessage();
            }
        }
    }
}
