package com.reversebits.projects.app.hybridmvp.common;

import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/10/2016.
 */

public abstract class BasePresenter {

    protected abstract void attachView(IBaseView view);

    protected abstract void detachView();

    protected abstract void saveUserData(UserResponse response);

    protected abstract void clearUserData();

}
