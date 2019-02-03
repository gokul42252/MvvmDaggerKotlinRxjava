

package com.ct.mvvmdaggerkotlin.ui.login;

import com.ct.mvvmdaggerkotlin.net.ApiInterFace;

public interface LoginNavigator {
    void login(ApiInterFace apiInterFace);

    void navigateToHome();
}
