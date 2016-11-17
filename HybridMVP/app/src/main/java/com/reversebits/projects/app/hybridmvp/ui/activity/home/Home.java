package com.reversebits.projects.app.hybridmvp.ui.activity.home;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.reversebits.projects.app.hybridmvp.R;
import com.reversebits.projects.app.hybridmvp.common.BaseActivity;
import com.reversebits.projects.app.hybridmvp.ui.activity.login.facebook.FB_Login_Activity;
import com.reversebits.projects.app.hybridmvp.ui.activity.login.gmail.Gmail_Login_Activity;
import com.reversebits.projects.app.hybridmvp.ui.activity.login.simplelogin.LoginActivity;
import com.reversebits.projects.app.hybridmvp.ui.activity.login.twitter.Twitter_Login_Activity;
import com.reversebits.projects.app.hybridmvp.ui.activity.signup.SignupActivity;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Home extends BaseActivity implements View.OnClickListener {

    Button btnSignin;
    SignInButton btnGoogleSignin;
    LoginButton btnFbSignin;
    TwitterLoginButton btnTwitterSignin;

    private Button loginButton;
    private Button loginButtonGmail;
    private Button loginButtonTwitter;
    private Button loginButtonFacebook;
    private Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        printKeyHash();
    }


    private void init() {
        loginButton = (Button) findViewById(R.id.login_button);
        loginButtonGmail = (Button) findViewById(R.id.btn_gmail_login);
        loginButtonTwitter = (Button) findViewById(R.id.login_button_twitter);
        loginButtonFacebook = (Button) findViewById(R.id.login_button_facebook);
        signupButton = (Button) findViewById(R.id.signup_button);

        loginButton.setOnClickListener(this);
        loginButtonGmail.setOnClickListener(this);
        loginButtonTwitter.setOnClickListener(this);
        loginButtonFacebook.setOnClickListener(this);
        signupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                startActivity(new Intent(Home.this, LoginActivity.class));
                break;
            case R.id.btn_gmail_login:
                startActivity(new Intent(Home.this, Gmail_Login_Activity.class));
                break;
            case R.id.login_button_facebook:
                startActivity(new Intent(Home.this, FB_Login_Activity.class));
                break;
            case R.id.login_button_twitter:
                startActivity(new Intent(Home.this, Twitter_Login_Activity.class));
                break;
            case R.id.signup_button:
                startActivity(new Intent(Home.this, SignupActivity.class));
                break;
        }
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.meetwo", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}




