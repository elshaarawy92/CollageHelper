package com.example.collagehelper.activity.login.view;

public interface ILoginView {
    void pwdWrong();
    void typeWrong();
    void loginSuccessBySeller();
    void loginSuccessByCustomer();
    void loginFailure();
    void userNotExist();
}
