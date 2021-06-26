package com.example.firebasecrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebasecrud.controller.ControllerLogin;
import com.example.firebasecrud.controller.IControllerLogin;
import com.example.firebasecrud.view.IView;

public class MainLogin extends Activity implements IView {
    EditText email, password;
    CheckBox lihatpass;
    Button loginb;
    IControllerLogin loginPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        loginb = (Button) findViewById(R.id.buttonb);
        lihatpass = (CheckBox) findViewById(R.id.showpass);
        loginPresenter = new ControllerLogin(this);
        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.OnLogin(email.getText().toString().trim(),
                        password.getText().toString().trim());
            }
        });
        lihatpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lihatpass.isChecked()) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void OnLoginSuccess(String message) {
        if (email.getText().toString().equals("kelompok6@gmail.com") &&
                password.getText().toString().equals("kelompok6")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Email Dan Password Tidak Cocok!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
