package com.reversebits.projects.app.hybridmvp.ui.activity.signup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.reversebits.projects.app.hybridmvp.R;
import com.reversebits.projects.app.hybridmvp.common.BaseActivity;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;
import com.reversebits.projects.app.hybridmvp.ui.activity.login.simplelogin.FirebaseLogin;

/**
 * Created by TapanHP on 11/11/2016.
 */

public class SignupActivity extends BaseActivity implements ISignupView {

    SignupPresenter presenter;
    FirebaseLogin login;

    private ImageView ivActSignupProf;
    private EditText etActSignupName, etActSignupMail, etActSignupPass;
    private Button btActSignupReq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        presenter = new SignupPresenter();
        login = new FirebaseLogin(SignupActivity.this);
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
    public void callSignupResult(UserResponse response) {
        //here we weill got response class's object
        presenter.saveUserData(response);
    }


    private void init() {
        ivActSignupProf = (ImageView) findViewById(R.id.iv_signup_image);
        etActSignupMail = (EditText) findViewById(R.id.et_act_signup_mail);
        etActSignupName = (EditText) findViewById(R.id.et_act_signup_name);
        etActSignupPass = (EditText) findViewById(R.id.et_act_signup_pass);
        btActSignupReq = (Button) findViewById(R.id.btn_signup_request);

        ivActSignupProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : UPLOAD AND SET IMAGE HERE
            }
        });

        btActSignupReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(presenter.userLoginAuth(etActSignupMail.getText().toString(),
                        etActSignupPass.getText().toString(),etActSignupName.getText().toString())) {

                    UserResponse resp = login.signUp(etActSignupMail.getText().toString(), etActSignupPass.getText().toString()
                            ,etActSignupName.getText().toString(),"");
                    if(resp != null)
                        callSignupResult(resp);
                }
            }
        });
    }


}
