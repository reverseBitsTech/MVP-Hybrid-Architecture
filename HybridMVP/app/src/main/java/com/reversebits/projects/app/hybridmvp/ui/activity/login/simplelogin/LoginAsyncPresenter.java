package com.reversebits.projects.app.hybridmvp.ui.activity.login.simplelogin;

/**
 * Created by TapanHP on 11/10/2016.
 */

public class LoginAsyncPresenter
{
    private ILoginInteractor interactor;

    public LoginAsyncPresenter(ILoginInteractor interactor) {
        this.interactor = interactor;
    }

    public void callData()
    {
        //this method is responsible to call async methods like retrofit calls

        //at last will call presenter's method

    }
}
