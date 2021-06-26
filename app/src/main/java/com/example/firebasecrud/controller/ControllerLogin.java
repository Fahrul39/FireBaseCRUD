package com.example.firebasecrud.controller;

import com.example.firebasecrud.model.ModelLogin;
import com.example.firebasecrud.view.IView;

public class ControllerLogin implements IControllerLogin {
    IView loginView;
    public ControllerLogin(IView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void OnLogin(String Email, String Password) {
        ModelLogin musicModel = new ModelLogin(Email, Password);
        int logincode = musicModel.isValid();
        if (logincode == 0){
            loginView.OnLoginError("Isi Email Terlebih Dahulu");
        }
        else if (logincode == 1){
            loginView.OnLoginError("Masukkan Email Anda Dengan Benar");
        }
        else if (logincode == 2){
            loginView.OnLoginError("Isi Password Terlebih Dahulu");
        }
        else if (logincode == 3){
            loginView.OnLoginError("Password Harus Terdiri Dari 6 Karakter Atau Lebih");
        }
        else if (logincode == 4){
            loginView.OnLoginSuccess("Login Berhasil");
        }
    }
}
