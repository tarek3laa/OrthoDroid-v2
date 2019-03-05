package com.example.elbagory.orthodroid;

import android.content.Intent;
import android.os.Bundle;

import com.example.elbagory.orthodroid.adapters.ImageAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import static com.example.elbagory.orthodroid.adapters.ImageAdapter.IMAGE_URL;

public class ImageActivity extends AppCompatActivity {
    private ViewPager photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        photoView = findViewById(R.id.photo_view);

        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra(IMAGE_URL);
        ImageAdapter imageAdapter = new ImageAdapter(this, list);

        //Glide.with(this).load().into(photoView);
        photoView.setAdapter(imageAdapter);

    }
}
