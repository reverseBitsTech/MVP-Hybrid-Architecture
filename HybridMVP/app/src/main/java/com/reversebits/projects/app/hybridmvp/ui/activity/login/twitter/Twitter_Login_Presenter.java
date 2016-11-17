package com.reversebits.projects.app.hybridmvp.ui.activity.login.twitter;

import com.reversebits.projects.app.hybridmvp.common.BasePresenter;
import com.reversebits.projects.app.hybridmvp.common.IBaseView;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/14/2016.
 */

public class Twitter_Login_Presenter extends BasePresenter implements I_Twitter_Login_Interactor {

    I_Twitter_Login_View view;
    Twitter_Login_AsyncPresenter asyncPresenter;


    public Twitter_Login_Presenter() {
        asyncPresenter = new Twitter_Login_AsyncPresenter(this);
    }

    @Override
    protected void attachView(IBaseView view) {
        this.view = (I_Twitter_Login_View) view;
    }

    @Override
    protected void detachView() {
        this.view = null;
    }

    @Override
    protected void saveUserData(UserResponse response) {
        view.savePref("USER_NAME", response.getName());
        view.savePref("USER_ID", response.getUserID());
        view.savePref("USER_EMAIL", response.getUserMail());
        view.savePref("USER_PHOTO", response.getPhotoUri());
    }

    @Override
    protected void clearUserData() {
        view.savePref("USER_NAME", null);
        view.savePref("USER_ID", null);
        view.savePref("USER_EMAIL", null);
        view.savePref("USER_PHOTO", null);
    }
}
