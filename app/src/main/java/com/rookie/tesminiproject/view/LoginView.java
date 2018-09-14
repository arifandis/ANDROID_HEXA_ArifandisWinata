package com.rookie.tesminiproject.view;

import com.rookie.tesminiproject.model.Login;

public interface LoginView {
    interface View{
        void showLoading();
        void hideLoading();
        void showDataProfile(Login profile);
    }

    interface Presenter{
        void getDataProfile();
    }
}
