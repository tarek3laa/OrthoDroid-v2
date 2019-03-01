package com.example.elbagory.orthodroid.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.elbagory.orthodroid.ImageActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    public static final String IMAGE_URL = "image_url";
    private List<String> images;
    private Context context;

    public ImageAdapter(Context context, List<String> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get().load(images.get(position)).resize(50,50).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ImageActivity.class);
                intent.putExtra(IMAGE_URL,images.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}