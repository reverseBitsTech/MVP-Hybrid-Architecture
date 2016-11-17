package com.reversebits.projects.app.hybridmvp.ui.activity.login.facebook;

import com.reversebits.projects.app.hybridmvp.common.BasePresenter;
import com.reversebits.projects.app.hybridmvp.common.IBaseView;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/14/2016.
 */

public class FB_Login_Presenter extends BasePresenter implements I_Fb_Login_Interactor {

    I_FB_Login_View view;
    FB_Login_Async_Presenter asyncPresenter;

    public FB_Login_Presenter() {
        asyncPresenter = new FB_Login_Async_Presenter(this);
    }

    @Override
    protected void attachView(IBaseView view) {
        this.view = (I_FB_Login_View) view;
    }

    @Override
    protected void detachView() {
        this.view = null;
    }

    @Override
    protected void saveUserData(UserResponse response) {
        if (view != null) {
            view.savePref("USER_NAME", response.getName());
            view.savePref("USER_ID", response.getUserID());
            view.savePref("USER_EMAIL", response.getUserMail());
            view.savePref("USER_PHOTO", response.getPhotoUri());
        }
    }
    @Override
    protected void clearUserData() {
        view.savePref("USER_NAME", null);
        view.savePref("USER_ID", null);
        view.savePref("USER_EMAIL", null);
        view.savePref("USER_PHOTO", null);
    }
}
