package com.reversebits.projects.app.hybridmvp.ui.activity.login.simplelogin;

import com.reversebits.projects.app.hybridmvp.common.IBaseView;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/10/2016.
 */

public interface ILoginView extends IBaseView {

    void callLoginResult(UserResponse response);

}
