package com.reversebits.projects.app.hybridmvp.ui.activity.signup;

import com.reversebits.projects.app.hybridmvp.common.IBaseView;
import com.reversebits.projects.app.hybridmvp.service.response.UserResponse;

/**
 * Created by TapanHP on 11/11/2016.
 */

public interface ISignupView extends IBaseView {

    void callSignupResult(UserResponse response);
}
