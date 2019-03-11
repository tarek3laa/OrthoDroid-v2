package com.example.elbagory.orthodroid;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
