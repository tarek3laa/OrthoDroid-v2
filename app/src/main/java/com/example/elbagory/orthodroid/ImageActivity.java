package com.example.elbagory.orthodroid;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elbagory.orthodroid.adapters.ImageAdapter;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        photoView = findViewById(R.id.photo_view);
        Intent intent = getIntent();
        Picasso.get().load(intent.getStringExtra(ImageAdapter.IMAGE_URL)).into(photoView);
    }
}
