package com.example.elbagory.orthodroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class Utils {

    public void openGallery(Fragment activity, int code) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), code);
    }

    public List<String> getImages(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            final List<String> imagesUri = new ArrayList<>();
            if (data.getClipData() != null) {

                //contains all selected images's URI
                int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                for (int i = 0; i < count; i++) { // add images uri to list
                    imagesUri.add(data.getClipData().getItemAt(i).getUri().toString());


                }
                return imagesUri;
            } else if (data.getData() != null) {
                imagesUri.add(data.getData().toString());
                return imagesUri;
            }


        }
        return null;
    }

    /**
     * @param bitmapImage
     * @return path
     */
    @NonNull
    private String saveToInternalStorage(Bitmap bitmapImage, Activity activity, String name) {
        ContextWrapper cw = new ContextWrapper(activity.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, name);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}

