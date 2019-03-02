package com.example.elbagory.orthodroid.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elbagory.orthodroid.Models.Model_History;
import com.example.elbagory.orthodroid.R;
import com.example.elbagory.orthodroid.UpdatePatientActivity;
import com.example.elbagory.orthodroid.Utils;
import com.example.elbagory.orthodroid.adapters.ListImageAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * fragment contains History Info
 */
public class HistoryFragment extends Fragment {
    private static final int STORAGE_PERMISSION_CODE = 50;
    EditText etChronic, etGastritis, etSmoking, etPregnancy, etLactation;
    private static Model_History model_history;
    // this Primary_key help us to get data for this user

    private Integer Primary_key = 0;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ImageView ivSmoking, ivChronic, ivGastritis, ivPregnancy, ivLactation;
    private RecyclerView rvSmoking, rvChronic, rvGastritis, rvPregnancy, rvLactation;

    private static final int PICK_IMAGE_SMOKING = 100, PICK_IMAGE_GASTRITIS = 101, PICK_IMAGE_PREGNANCY = 102, PICK_IMAGE_LACTATION = 103, PICK_IMAGE_CHRONIC = 104;
    private Utils utils = new Utils();
    List<String>[] lists = new ArrayList[5];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_history, container, false);

        //fire base
        etChronic = view.findViewById(R.id.ch);
        etGastritis = view.findViewById(R.id.ga);
        etSmoking = view.findViewById(R.id.smoking);
        etPregnancy = view.findViewById(R.id.pr);
        etLactation = view.findViewById(R.id.la);


        ivSmoking = view.findViewById(R.id.add_image_smoking);
        ivChronic = view.findViewById(R.id.add_image_chronic);
        ivGastritis = view.findViewById(R.id.add_image_gastritis);
        ivPregnancy = view.findViewById(R.id.add_image_pregnancy);
        ivLactation = view.findViewById(R.id.add_image_lactation);


        rvSmoking = view.findViewById(R.id.images_container_smoking);
        rvChronic = view.findViewById(R.id.images_container_chronic);
        rvGastritis = view.findViewById(R.id.images_container_gastritis);
        rvPregnancy = view.findViewById(R.id.images_container_pregnancy);
        rvLactation = view.findViewById(R.id.images_container_lactation);


        rvSmoking.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvChronic.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPregnancy.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvGastritis.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvLactation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        ivSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvSmoking.setVisibility(View.VISIBLE);
                //utils.
                utils.openGallery(HistoryFragment.this, PICK_IMAGE_SMOKING);
            }
        });
        ivChronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rvChronic.setVisibility(View.VISIBLE);
                utils.openGallery(HistoryFragment.this, PICK_IMAGE_CHRONIC);

            }
        });
        ivGastritis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvGastritis.setVisibility(View.VISIBLE);

                utils.openGallery(HistoryFragment.this, PICK_IMAGE_GASTRITIS);

            }
        });
        ivPregnancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvPregnancy.setVisibility(View.VISIBLE);
                utils.openGallery(HistoryFragment.this, PICK_IMAGE_PREGNANCY);

            }
        });
        ivLactation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvLactation.setVisibility(View.VISIBLE);
                utils.openGallery(HistoryFragment.this, PICK_IMAGE_LACTATION);

            }
        });


        // get the value of Primary_key from data base fire base

        Primary_key = UpdatePatientActivity.patientID;

        Button button = view.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                model_history = new Model_History(etChronic.getText().toString(),

                        etGastritis.getText().toString(),
                        etSmoking.getText().toString(),
                        etPregnancy.getText().toString(),
                        etLactation.getText().toString(),
                        Primary_key);
                UpdatePatientActivity.allInfo.setHistory(model_history);

                databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);


                uploadToStorage("smoking", lists[0], PICK_IMAGE_SMOKING);
                uploadToStorage("gastritis", lists[1], PICK_IMAGE_GASTRITIS);
                uploadToStorage("pregnancy", lists[2], PICK_IMAGE_PREGNANCY);
                uploadToStorage("lactation", lists[3], PICK_IMAGE_LACTATION);
                uploadToStorage("chronic", lists[4], PICK_IMAGE_CHRONIC);


                Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();

            }
        });
        try {
            Model_History history = null;
            if (UpdatePatientActivity.allInfo != null)
                history = UpdatePatientActivity.allInfo.getHistory();
            else System.out.println("null at all patient ");
            //  rvSmoking,rvChronic,  rvGastritis, rvPregnancy, rvLactation;
            if (history != null) {
                if (history.getPSmoking() != null)
                    etSmoking.setText(history.getPSmoking());
                if (history.getPChronic() != null)
                    etChronic.setText(history.getPChronic());
                if (history.getPGastritis() != null)
                    etGastritis.setText(history.getPGastritis());
                if (history.getPPregnancy() != null)
                    etPregnancy.setText(history.getPPregnancy());
                if (history.getPLactation() != null)
                    etLactation.setText(history.getPLactation());
                if (history.getImagesSmoking() != null) {
                    ListImageAdapter imageAdapter = new ListImageAdapter(null, getContext());

                    rvSmoking.setVisibility(View.VISIBLE);
                    imageAdapter.setList(history.getImagesSmoking());
                    rvSmoking.setAdapter(imageAdapter);
                }
                if (history.getImagesChronic() != null) {
                    ListImageAdapter imageAdapter = new ListImageAdapter(null, getContext());

                    rvChronic.setVisibility(View.VISIBLE);
                    imageAdapter.setList(history.getImagesChronic());
                    rvChronic.setAdapter(imageAdapter);
                }
                if (history.getImagesGastritis() != null) {
                    ListImageAdapter imageAdapter = new ListImageAdapter(null, getContext());

                    rvGastritis.setVisibility(View.VISIBLE);
                    imageAdapter.setList(history.getImagesGastritis());
                    rvGastritis.setAdapter(imageAdapter);
                }
                if (history.getImagesPregnancy() != null) {
                    ListImageAdapter imageAdapter = new ListImageAdapter(null, getContext());

                    rvPregnancy.setVisibility(View.VISIBLE);
                    imageAdapter.setList(history.getImagesPregnancy());
                    rvPregnancy.setAdapter(imageAdapter);
                }
                if (history.getImagesLactation() != null) {
                    ListImageAdapter imageAdapter = new ListImageAdapter(null, getContext());

                    rvLactation.setVisibility(View.VISIBLE);
                    imageAdapter.setList(history.getImagesLactation());
                    rvLactation.setAdapter(imageAdapter);
                }

            } else System.out.println("Null at history");
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: ", e);
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("from history");
        List<String> urls = utils.getImages(resultCode, data);
        ListImageAdapter image2 = new ListImageAdapter(urls, getContext());
        if (urls == null) System.out.println("NULL url");
        switch (requestCode) {
            case PICK_IMAGE_SMOKING:
                System.out.println("yes");
                for (int i = 0; i < urls.size(); i++) {
                    System.out.println(urls.get(i));
                }
                rvSmoking.setAdapter(image2);
                lists[0] = urls;
                break;
            case PICK_IMAGE_GASTRITIS:
                rvGastritis.setAdapter(image2);
                lists[1] = urls;
                break;
            case PICK_IMAGE_PREGNANCY:
                rvPregnancy.setAdapter(image2);

                lists[2] = urls;
                break;
            case PICK_IMAGE_LACTATION:
                rvLactation.setAdapter(image2);

                lists[3] = urls;
                break;
            case PICK_IMAGE_CHRONIC:
                rvChronic.setAdapter(image2);

                lists[4] = urls;
                break;
        }


    }

    public void uploadToStorage(String path, final List<String> imageUris, final int type) {
        if (imageUris == null) {
            System.out.println("NULL");
            return;
        } else {
            System.out.println("NOT NULL");
        }
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        Random random = new Random();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        final StorageReference filePath = storageRef.child(String.valueOf(Primary_key)).child("history").child(path);
        for (String imageUri : imageUris) {
            final String image_name = String.valueOf(random.nextInt((int) 1e9));
            filePath.child(image_name).putFile(Uri.parse(imageUri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filePath.child(image_name).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            List<String> list;
                            switch (type) {
                                case PICK_IMAGE_SMOKING:
                                    list = model_history.getImagesSmoking();
                                    if (list == null)
                                        list = new ArrayList<>();
                                    list.add(uri.toString());
                                    model_history.setImagesSmoking(list);
                                    break;
                                case PICK_IMAGE_PREGNANCY:


                                    list = model_history.getImagesPregnancy();
                                    if (list == null)
                                        list = new ArrayList<>();
                                    list.add(uri.toString());
                                    model_history.setImagesPregnancy(list);
                                    break;

                                case PICK_IMAGE_LACTATION:
                                    list = model_history.getImagesLactation();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_history.setImagesLactation(list);
                                    break;

                                case PICK_IMAGE_CHRONIC:

                                    list = model_history.getImagesChronic();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_history.setImagesChronic(list);
                                    break;

                                case PICK_IMAGE_GASTRITIS:
                                    list = model_history.getImagesGastritis();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_history.setImagesGastritis(list);


                            }

                            UpdatePatientActivity.allInfo.setHistory(model_history);
                            databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("fAIL " + e);
                        }
                    });


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("fail  " + e);
                }
            });

        }
    }


}