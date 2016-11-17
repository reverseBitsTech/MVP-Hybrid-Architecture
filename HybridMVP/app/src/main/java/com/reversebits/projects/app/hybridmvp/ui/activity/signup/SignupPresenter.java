package com.reversebits.projects.app.hybridmvp.ui.activity.signup;

import android.text.TextUtils;

import com.reversebits.projects.app.hybridmvp.common.BasePresenter;
import com.reversebits.projects.app.hybridmvp.common.IBaseView;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/11/2016.
 */

public class SignupPresenter extends BasePresenter implements ISignupInteractor {
    private ISignupView view;
    private SignupAsyncPresenter asyncPresenter;

    public SignupPresenter() {
        asyncPresenter = new SignupAsyncPresenter(this);
    }

    @Override
    protected void attachView(IBaseView view) {
        this.view = (ISignupView) view;
    }

    @Override
    protected void detachView() {
        this.view = null;
    }

    @Override
    protected void saveUserData(UserResponse response) {
        view.savePref("USER_NAME",response.getName());
        view.savePref("USER_ID",response.getUserID());
        view.savePref("USER_EMAIL",response.getUserMail());
        view.savePref("USER_PHOTO",response.getPhotoUri());
    }

    @Override
    protected void clearUserData() {

    }

    protected boolean userLoginAuth(String email, String pass, String name) {

        if (!TextUtils.isEmpty(name)) {
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
                if (view.validateMail(email)) {
                    if (pass.length() > 6 && pass.length() < 14) {
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
        }
        view.doToast("Wrong name");
        return false;
    }
}
