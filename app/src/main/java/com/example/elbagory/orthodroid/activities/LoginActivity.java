package com.example.elbagory.orthodroid.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.elbagory.orthodroid.R;
import com.sdsmdg.tastytoast.TastyToast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    EditText metUserName, metPassword;
    LinearLayout layout;
    CheckBox mcbRememberMe;
    Button mbtLogin;
    SharedPreferences sharedPreferences;

    private final String FILE_NAME = "com.apps.example.alaa.tarek.loginpage";
    private final String LOGIN_KEY = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // init view
        metUserName = findViewById(R.id.tv_username);
        metPassword = findViewById(R.id.tv_password);
        layout = findViewById(R.id.layout);
        mcbRememberMe = findViewById(R.id.cb_remember_me);
        mbtLogin = findViewById(R.id.bt_login);
        sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(LOGIN_KEY, false)) {
            makeTastyToast("authentication Succeeded", TastyToast.SUCCESS);


            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        mbtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mcbRememberMe.isChecked()) putBoolean(LOGIN_KEY, true);
                goToMainActivity();
                finish();
            }
        });

    }


    /**
     * but boolean in sharedPreferences
     *
     * @param s Key
     * @param b Value
     */


    private void putBoolean(String s, boolean b) {
        sharedPreferences.edit().putBoolean(s, b).apply();
    }


    private void goToMainActivity() {
        String username = metUserName.getText().toString();
        String password = metPassword.getText().toString();
        // check if there is an empty field
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password))

            makeTastyToast("There is an empty field", TastyToast.INFO);
        else {

            // check if username and password are correct
            if (username.equals("admin") && password.equals("admin")) {
                makeTastyToast("authentication Succeeded", TastyToast.SUCCESS);
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else
                makeTastyToast("username or password incorrect", TastyToast.ERROR);


        }
    }

    /**
     * @param s     Messages that appear in the toast
     * @param toast type
     */
    private void makeTastyToast(String s, int toast) {
        TastyToast.makeText(this, s, TastyToast.LENGTH_LONG, toast);
    }
}

