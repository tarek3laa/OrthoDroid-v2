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

import com.example.elbagory.orthodroid.Models.Model_Examination;
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
import com.sdsmdg.tastytoast.TastyToast;

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
 * fragment contains Examination Info
 */
public class ExaminationFragment extends Fragment {
    private static final int PICK_IMAGE_TRAUMA = 1;
    private static final int PICK_IMAGE_KNEE = 2;
    private static final int PICK_IMAGE_SHOULDER = 3;
    private static final int PICK_IMAGE_SPINE = 4;
    private static final int PICK_IMAGE_PELVIS = 5;
    private static final int PICK_IMAGE_FOOT = 6;
    private static final int PICK_IMAGE_ELBOW = 7;
    private EditText etTrauma, etKnee, etShoulder, etSpine, etPelvis, etFoot, etElbow;
    private ImageView ivTrauma, ivKnee, ivShoulder, ivSpine, ivPelvis, ivFoot, ivElbow;
    private RecyclerView vpTrauma;
    private RecyclerView vpKnee, vpShoulder, vpSpine, vpPelvis, vpFoot, vpElbow;
    int Primary_key = 0;
    Utils utils = new Utils();
    List<String>[] lists = new ArrayList[7];
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference databaseReference = firebaseDatabase.getReference();


    private static Model_Examination model_examination;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_examination, container, false);
        try {


            ivTrauma = view.findViewById(R.id.add_image_trauma);
            ivKnee = view.findViewById(R.id.add_image_knee);
            ivShoulder = view.findViewById(R.id.add_image_shoulder);
            ivSpine = view.findViewById(R.id.add_image_spine);
            ivPelvis = view.findViewById(R.id.add_image_pelvis);
            ivFoot = view.findViewById(R.id.add_image_foot);
            ivElbow = view.findViewById(R.id.add_image_elbow);

            vpTrauma = view.findViewById(R.id.images_container_trauma);
            vpKnee = view.findViewById(R.id.images_container_knee);
            vpShoulder = view.findViewById(R.id.images_container_shoulder);
            vpSpine = view.findViewById(R.id.images_container_spine);
            vpPelvis = view.findViewById(R.id.images_container_pelvis);
            vpFoot = view.findViewById(R.id.images_container_foot);
            vpElbow = view.findViewById(R.id.images_container_elbow);


            Model_Examination examination = UpdatePatientActivity.allInfo.getExamination();

            /*

             */
            //  if (examination.getTraumaImages().size() > 0) vpTrauma.setVisibility(View.VISIBLE);


            //

            /*

             */

            vpTrauma.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            vpKnee.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            vpShoulder.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            vpSpine.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            vpPelvis.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            vpFoot.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            vpElbow.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


            ivTrauma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    utils.openGallery(ExaminationFragment.this, PICK_IMAGE_TRAUMA);
                    vpTrauma.setVisibility(View.VISIBLE);


                }
            });
            ivKnee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    utils.openGallery(ExaminationFragment.this, PICK_IMAGE_KNEE);
                    vpKnee.setVisibility(View.VISIBLE);
                }
            });
            ivShoulder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    utils.openGallery(ExaminationFragment.this, PICK_IMAGE_SHOULDER);

                    vpShoulder.setVisibility(View.VISIBLE);

                }
            });
            ivSpine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    utils.openGallery(ExaminationFragment.this, PICK_IMAGE_SPINE);
                    vpSpine.setVisibility(View.VISIBLE);
                }
            });
            ivPelvis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    utils.openGallery(ExaminationFragment.this, PICK_IMAGE_PELVIS);
                    vpPelvis.setVisibility(View.VISIBLE);
                }
            });
            ivFoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    utils.openGallery(ExaminationFragment.this, PICK_IMAGE_FOOT);
                    vpFoot.setVisibility(View.VISIBLE);
                }
            });
            ivElbow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    utils.openGallery(ExaminationFragment.this, PICK_IMAGE_ELBOW);
                    vpElbow.setVisibility(View.VISIBLE);
                }
            });


            // get the value of Primary_key from data base fire base

            Primary_key = UpdatePatientActivity.patientID;


            // init view
            etTrauma = view.findViewById(R.id.editTextTrauma);
            etKnee = view.findViewById(R.id.editTextKnee);
            etShoulder = view.findViewById(R.id.editTextShoulder);
            etSpine = view.findViewById(R.id.editTextSpine);
            etPelvis = view.findViewById(R.id.editTextPelvis);
            etFoot = view.findViewById(R.id.editTextFoot);
            etElbow = view.findViewById(R.id.editTextElbow);


            Button button = view.findViewById(R.id.button4);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model_examination = new Model_Examination(etTrauma.getText().toString(),
                            etKnee.getText().toString(),
                            etShoulder.getText().toString(),
                            etSpine.getText().toString(),
                            etPelvis.getText().toString(),
                            etFoot.getText().toString(),
                            etElbow.getText().toString(),
                            Primary_key

                    );
                    UpdatePatientActivity.allInfo.setExamination(model_examination);
                    databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);
                    uploadToStorage("trauma", lists[0], PICK_IMAGE_TRAUMA);
                    uploadToStorage("elbow", lists[1], PICK_IMAGE_ELBOW);
                    uploadToStorage("foot", lists[2], PICK_IMAGE_FOOT);
                    uploadToStorage("knee", lists[3], PICK_IMAGE_KNEE);
                    uploadToStorage("pelvis", lists[4], PICK_IMAGE_PELVIS);
                    uploadToStorage("spine", lists[5], PICK_IMAGE_SPINE);
                    uploadToStorage("shoulder", lists[6], PICK_IMAGE_SHOULDER);
                    TastyToast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT, TastyToast.SUCCESS).show();
                }
            });

            try {
                if (examination != null) {
                    if (examination.getEtTrauma() != null)
                        etTrauma.setText(examination.getEtTrauma());
                    if (examination.getEtKnee() != null)
                        etKnee.setText(examination.getEtKnee());
                    if (examination.getEtShoulder() != null)
                        etShoulder.setText(examination.getEtShoulder());
                    if (examination.getEtSpine() != null)
                        etSpine.setText(examination.getEtSpine());
                    if (examination.getEtPelvis() != null)
                        etPelvis.setText(examination.getEtPelvis());
                    if (examination.getEtFoot() != null)
                        etFoot.setText(examination.getEtFoot());
                    if (examination.getEtElbow() != null)
                        etElbow.setText(examination.getEtElbow());


                    ListImageAdapter imageAdapter = new ListImageAdapter(null, getContext());
                    if (examination.getTraumaImages() != null) {
                        imageAdapter.setList(examination.getTraumaImages());
                        vpTrauma.setVisibility(View.VISIBLE);
                        vpTrauma.setAdapter(imageAdapter);
                    }

                    if (examination.getElbowImages() != null) {
                        vpElbow.setVisibility(View.VISIBLE);
                        imageAdapter.setList(examination.getElbowImages());

                        vpElbow.setAdapter(imageAdapter);
                    }
                    if (examination.getFootImages() != null) {
                        vpFoot.setVisibility(View.VISIBLE);
                        imageAdapter.setList(examination.getFootImages());

                        vpFoot.setAdapter(imageAdapter);
                    }
                    if (examination.getKneeImages() != null) {
                        vpKnee.setVisibility(View.VISIBLE);
                        imageAdapter.setList(examination.getKneeImages());
                        vpKnee.setAdapter(imageAdapter);
                    }
                    if (examination.getShoulderImages() != null) {


                        vpShoulder.setVisibility(View.VISIBLE);
                        imageAdapter.setList(examination.getPelvisImages());
                        vpPelvis.setAdapter(imageAdapter);

                    }
                    if (examination.getSpineImages() != null) {
                        vpSpine.setVisibility(View.VISIBLE);
                        imageAdapter.setList(examination.getSpineImages());
                        vpSpine.setAdapter(imageAdapter);
                    }
                    if (examination.getPelvisImages() != null) {
                        vpPelvis.setVisibility(View.VISIBLE);
                        imageAdapter.setList(examination.getShoulderImages());
                        vpShoulder.setAdapter(imageAdapter);
                    }

                }
            } catch (Exception e) {
                Log.e(TAG, "onCreateView: ", e);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<String> urls = utils.getImages(resultCode, data);


        ListImageAdapter image2 = new ListImageAdapter(urls, getContext());

        switch (requestCode) {
            case PICK_IMAGE_TRAUMA:

                vpTrauma.setAdapter(image2);
                lists[0] = urls;
                break;
            case PICK_IMAGE_ELBOW:
                vpElbow.setAdapter(image2);
                lists[1] = urls;
                break;
            case PICK_IMAGE_FOOT:
                vpFoot.setAdapter(image2);

                lists[2] = urls;
                break;
            case PICK_IMAGE_KNEE:
                vpKnee.setAdapter(image2);

                lists[3] = urls;
                break;
            case PICK_IMAGE_PELVIS:
                vpPelvis.setAdapter(image2);
                lists[4] = urls;
                break;
            case PICK_IMAGE_SPINE:
                vpSpine.setAdapter(image2);
                lists[5] = urls;
                break;
            case PICK_IMAGE_SHOULDER:
                vpShoulder.setAdapter(image2);
                lists[6] = urls;
                break;
        }


    }

    private void uploadToStorage(String path, final List<String> imageUris, final int type) {
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
        final StorageReference filePath = storageRef.child(String.valueOf(Primary_key)).child("examination").child(path);
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
                                case PICK_IMAGE_TRAUMA:
                                    list = model_examination.getTraumaImages();
                                    if (list == null)
                                        list = new ArrayList<>();


                                    list.add(uri.toString());
                                    model_examination.setTraumaImages(list);
                                    break;
                                case PICK_IMAGE_ELBOW:
                                    //elbow


                                    list = model_examination.getElbowImages();
                                    if (list == null)
                                        list = new ArrayList<>();
                                    list.add(uri.toString());
                                    model_examination.setElbowImages(list);
                                    break;

                                case PICK_IMAGE_FOOT:
//foot
                                    list = model_examination.getFootImages();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_examination.setFootImages(list);
                                    break;

                                case PICK_IMAGE_KNEE:
                                    //knee

                                    list = model_examination.getKneeImages();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_examination.setKneeImages(list);
                                    break;
                                case PICK_IMAGE_PELVIS:
                                    list = model_examination.getPelvisImages();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_examination.setPelvisImages(list);
                                    //pelvis
                                    break;

                                case PICK_IMAGE_SPINE:
                                    list = model_examination.getSpineImages();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_examination.setSpineImages(list);
                                    //spine
                                    break;
                                case PICK_IMAGE_SHOULDER:

                                    list = model_examination.getShoulderImages();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_examination.setShoulderImages(list);

                                    //shoulder
                                    break;

                            }

                            UpdatePatientActivity.allInfo.setExamination(model_examination);
                            databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("fAIL");
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

