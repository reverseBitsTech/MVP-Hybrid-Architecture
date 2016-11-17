package com.reversebits.projects.app.hybridmvp.ui.activity.login.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.reversebits.projects.app.hybridmvp.R;
import com.reversebits.projects.app.hybridmvp.common.BaseActivity;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;


public class Twitter_Login_Activity extends BaseActivity implements I_Twitter_Login_View {

    Twitter_Login_Presenter presenter;
    TwitterLoginButton loginButton;
    Twitter_Login login;
    String email;

    TwitterSession session;
    TwitterAuthToken authToken;

    TwitterAuthClient authClient = new TwitterAuthClient();


    public Twitter_Login_Activity() {
        presenter = new Twitter_Login_Presenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);
        login = new Twitter_Login(Twitter_Login_Activity.this);
        login.initFirebase();
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
        loginButton = (TwitterLoginButton) findViewById(R.id.btn_twitter_login);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // ... do something

                session = Twitter.getSessionManager().getActiveSession();
                authToken = session.getAuthToken();

                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {
                        // Do something with the result, which provides the email address
                        Log.e("requestEmail", "success: " + result.data);
                        email = result.data;
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Do something on failure
                        Log.e("requestEmail", "fail: ");
                    }
                });

                UserResponse response = login.handleTwitterSession(result.data,email);
                if(response != null)
                    presenter.saveUserData(response);

            }

            @Override
            public void failure(TwitterException exception) {
                Log.e("Twiter login", "failed : " + exception.getMessage());
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


}
