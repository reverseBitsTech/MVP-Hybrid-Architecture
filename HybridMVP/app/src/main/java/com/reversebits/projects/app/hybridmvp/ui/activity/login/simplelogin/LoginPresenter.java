package com.reversebits.projects.app.hybridmvp.ui.activity.login.simplelogin;

import android.text.TextUtils;

import com.reversebits.projects.app.hybridmvp.common.BasePresenter;
import com.reversebits.projects.app.hybridmvp.common.IBaseView;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/10/2016.
 */

public class LoginPresenter extends BasePresenter implements ILoginInteractor{

    private LoginAsyncPresenter asyncPresenter;

    private ILoginView view;

    public LoginPresenter() {
        asyncPresenter = new LoginAsyncPresenter(this);
    }

    @Override
    protected void attachView(IBaseView view) {
        this.view= (ILoginView) view;
    }

    @Override
    protected void detachView() {
        this.view = null;
    }

    @Override
    protected void saveUserData(UserResponse response) {
        //TODO : Validation of login data
        asyncPresenter.callData();
        view.savePref("USER_NAME",response.getName());
        view.savePref("USER_ID",response.getUserID());
        view.savePref("USER_EMAIL",response.getUserMail());
        view.savePref("USER_PHOTO",response.getPhotoUri());
    }

    @Override
    protected void clearUserData() {

    }

    @Override
    public void loginResp(UserResponse response)
    {
        if(view != null) {
            //call activity's method here
        }
    }

    protected boolean userLoginAuth(String email, String pass) {

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            if (view.validateMail(email)) {
                if(pass.length()> 6 && pass.length() < 14) {
                      return true;
                } else {
                    view.doToast("Must between 6 to 14 words");
                    return false;
                }
            } else {
                view.doToast("Wrong email");
                return false;
            }

        }
        return false;
    }





}
