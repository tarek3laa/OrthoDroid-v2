package com.example.elbagory.orthodroid;

import android.content.Context;
import android.content.SharedPreferences;

public  class CheckLogin {


    private SharedPreferences sharedPreferences;
    private Context context;

    public CheckLogin(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("ISLOGIN",Context.MODE_PRIVATE);

    }



    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ISLOGIN",status);
        editor.commit();

    }

    public boolean readLoginStatus() {

        return sharedPreferences.getBoolean("ISLOGIN",false);
    }



}
