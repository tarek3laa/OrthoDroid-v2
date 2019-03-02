package com.example.elbagory.orthodroid;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class Utils {
    public static final int STORAGE_PERMISSION_CODE = 50;

    public void checkPermission(Fragment fragment) {
        if (ContextCompat.checkSelfPermission(fragment.getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(fragment.getContext(), "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {
            requestStoragePermission(fragment);
        }
    }

    private void requestStoragePermission(final Fragment fragment) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(fragment.getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(fragment.getContext())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(fragment.getActivity(),
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(fragment.getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    public void openGallery(Fragment activity, int code) {

       checkPermission(activity);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), code);

     /*   Intent intent = new Intent(activity.getContext(), AlbumSelectActivity.class);
//set limit on number of images that can be selected, default is 10
        intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 20);
        activity.startActivityForResult(intent, code);
*/

    }

    public List<String> getImages(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            /*ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                list.add(images.get(i).path);
                System.out.println(list.get(i));
            }*/

            System.out.println("get");
            if (data.getClipData() != null) {
                final List<String> imagesUri = new ArrayList<>(); //contains all selected images's URI
                int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                for (int i = 0; i < count; i++) { // add images uri to list
                    imagesUri.add(data.getClipData().getItemAt(i).getUri().toString());
                    System.out.println(imagesUri.get(i));

                }
                return imagesUri;
            }


            //  return list;
        }
        return null;
    }

}

