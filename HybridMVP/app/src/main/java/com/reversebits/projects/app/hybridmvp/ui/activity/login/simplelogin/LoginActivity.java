package com.reversebits.projects.app.hybridmvp.ui.activity.login.simplelogin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reversebits.projects.app.hybridmvp.R;
import com.reversebits.projects.app.hybridmvp.common.BaseActivity;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    LoginPresenter presenter;
    FirebaseLogin login;
    Activity context;

    EditText etMail, etPass;
    Button btLoginReq;

    public LoginActivity() {
        context = LoginActivity.this;
        presenter = new LoginPresenter();
        login = new FirebaseLogin(context);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_login);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        login.addAuthListner();
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
        login.removeAuthListner();
    }

    private void init() {
        etMail = (EditText) findViewById(R.id.et_act_login_mail);
        etPass = (EditText) findViewById(R.id.et_act_login_pass);

        btLoginReq = (Button) findViewById(R.id.btn_login_request);
        btLoginReq.setOnClickListener(this);
//        btSignupReq = (Button) findViewById(R.id.btn_signup_request);
//        btSignupReq.setOnClickListener(this);
    }

    @Override
    public void callLoginResult(UserResponse response) {
        //here we weill got response class's object
       presenter.saveUserData(response);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_request:
                if (presenter.userLoginAuth(etMail.getText().toString(), etPass.getText().toString())) {

                    UserResponse response = login.signIn(etMail.getText().toString(), etPass.getText().toString());
                    if(response!=null)
                        callLoginResult(response);
                }
                break;

        }
    }
}
