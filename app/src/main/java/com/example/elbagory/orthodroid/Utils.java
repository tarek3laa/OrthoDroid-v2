package com.example.elbagory.orthodroid;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("OK");
            System.out.println("get");
            final List<String> imagesUri = new ArrayList<>();
            if (data.getClipData() != null) {

                //contains all selected images's URI
                int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                for (int i = 0; i < count; i++) { // add images uri to list
                    imagesUri.add(data.getClipData().getItemAt(i).getUri().toString());
                    System.out.println(imagesUri.get(i));

                }
                return imagesUri;
            } else if (data.getData() != null) {
                imagesUri.add(data.getData().toString());
                return imagesUri;
            }


        } else System.out.println("NO");
        return null;
    }

}

