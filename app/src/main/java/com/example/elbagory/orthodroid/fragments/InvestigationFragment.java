package com.example.elbagory.orthodroid.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elbagory.orthodroid.Models.Model_Investigation;
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

import static com.example.elbagory.orthodroid.UpdatePatientActivity.allInfo;

/**
 * fragment contains Investigation Info
 */
public class InvestigationFragment extends Fragment {
    EditText etChemistry, etCS, etCytology, etXray, etScanogram, etCT, etMRI, etDEXA, etBoneScan;

    int Primary_key = 0;


    private ImageView ivChemistry, ivCS, ivCytology, ivXray, ivScanogram, ivCT, ivMRI, ivDexa, ivBone;
    private RecyclerView rvChemistry, rvCS, rvCytology, rvXray, rvScanogram, rvCT, rvMRI, rvDexa, rvBone;
    private static final int PICK_IMAGE_CHEMISTRY = 200, PICK_IMAGE_CS = 201, PICK_IMAGE_CYTOLOGY = 202, PICK_IMAGE_XRAY = 203, PICK_IMAGE_SCANOGRAM = 204, PICK_IMAGE_CT = 205, PICK_IMAGE_MRI = 206, PICK_IMAGE_DEXA = 207, PICK_IMAGE_BONE = 208;
    Utils utils = new Utils();
    List<String>[] lists = new ArrayList[9];
    private static Model_Investigation model_investigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investigation, container, false);

        ivChemistry = view.findViewById(R.id.add_image_chemistry);
        ivCS = view.findViewById(R.id.add_image_cs);
        ivCytology = view.findViewById(R.id.add_image_cytology);
        ivXray = view.findViewById(R.id.add_image_x_ray);
        ivScanogram = view.findViewById(R.id.add_image_scanogram);
        ivCT = view.findViewById(R.id.add_image_CT);
        ivMRI = view.findViewById(R.id.add_image_MRI);
        ivDexa = view.findViewById(R.id.add_image_DEXA);
        ivBone = view.findViewById(R.id.add_image_bone);


        rvChemistry = view.findViewById(R.id.images_container_chemistry);
        rvCS = view.findViewById(R.id.images_container_cs);
        rvCytology = view.findViewById(R.id.images_container_cytology);
        rvXray = view.findViewById(R.id.images_container_x_ray);
        rvScanogram = view.findViewById(R.id.images_container_scanogram);
        rvCT = view.findViewById(R.id.images_container_CT);
        rvMRI = view.findViewById(R.id.images_container_MRI);
        rvDexa = view.findViewById(R.id.images_container_DEXA);
        rvBone = view.findViewById(R.id.images_container_bone);


        rvChemistry.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCS.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCytology.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvXray.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvScanogram.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCT.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMRI.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvDexa.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvBone.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        ivChemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvChemistry.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_CHEMISTRY);
            }
        });
        ivCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvCS.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_CS);
            }
        });
        ivCytology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvCytology.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_CYTOLOGY);
            }
        });
        ivXray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvXray.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_XRAY);
            }
        });
        ivScanogram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvScanogram.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_SCANOGRAM);
            }
        });
        ivCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvCT.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_CT);
            }
        });
        ivMRI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvMRI.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_MRI);
            }
        });
        ivDexa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvDexa.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_DEXA);
            }
        });
        ivBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvBone.setVisibility(View.VISIBLE);
                utils.openGallery(InvestigationFragment.this, PICK_IMAGE_BONE);
            }
        });
        //fire base
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        // get the value of Primary_key from data base fire base

        Primary_key = UpdatePatientActivity.patientID;


        // init view
        etChemistry = view.findViewById(R.id.editTextChemistry);
        etCS = view.findViewById(R.id.editTextCS);
        etCytology = view.findViewById(R.id.editTextCytology);
        etXray = view.findViewById(R.id.editTextX_ray);
        etScanogram = view.findViewById(R.id.editTextScanogram);
        etCT = view.findViewById(R.id.editTextC_T);
        etMRI = view.findViewById(R.id.editTextMRI);
        etDEXA = view.findViewById(R.id.editTextDEXA);
        etBoneScan = view.findViewById(R.id.editTextBone);


        Button button = view.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                model_investigation = new Model_Investigation(etChemistry.getText().toString(),
                        etCS.getText().toString(),
                        etCytology.getText().toString(),
                        etXray.getText().toString(),
                        etScanogram.getText().toString(),
                        etCT.getText().toString(),
                        etMRI.getText().toString(),
                        etDEXA.getText().toString(),
                        etBoneScan.getText().toString(),
                        Primary_key
                );

                allInfo.setInvestigation(model_investigation);


                databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(allInfo);
                uploadToStorage("Chemistry", lists[0], PICK_IMAGE_CHEMISTRY);
                uploadToStorage("CS", lists[1], PICK_IMAGE_CS);
                uploadToStorage("cytology", lists[2], PICK_IMAGE_CYTOLOGY);
                uploadToStorage("x_ray", lists[3], PICK_IMAGE_XRAY);
                uploadToStorage("scanogram", lists[4], PICK_IMAGE_SCANOGRAM);


                uploadToStorage("CT", lists[5], PICK_IMAGE_CT);
                uploadToStorage("MRI", lists[6], PICK_IMAGE_MRI);
                uploadToStorage("DEXA", lists[7], PICK_IMAGE_DEXA);
                uploadToStorage("BONE", lists[8], PICK_IMAGE_BONE);


                TastyToast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT, TastyToast.SUCCESS).show();

            }
        });
        try {

            Model_Investigation investigation = null;
            if (allInfo.getInvestigation() != null) investigation = allInfo.getInvestigation();
            if (investigation != null) {
                // etChemistry, etCS, etCytology, etXray, etScanogram, etCT, etMRI, etDEXA, etBoneScan;
                if (investigation.getEtChemistry() != null)
                    etChemistry.setText(investigation.getEtChemistry());
                if (investigation.getEtCS() != null) etCS.setText(investigation.getEtCS());
                if (investigation.getEtCytology() != null)
                    etCytology.setText(investigation.getEtCytology());
                if (investigation.getEtXray() != null) etXray.setText(investigation.getEtXray());
                if (investigation.getEtScanogram() != null)
                    etScanogram.setText(investigation.getEtScanogram());
                if (investigation.getEtCT() != null) etCT.setText(investigation.getEtCT());
                if (investigation.getEtMRI() != null) etMRI.setText(investigation.getEtMRI());
                if (investigation.getEtDEXA() != null) etDEXA.setText(investigation.getEtDEXA());
                if (investigation.getEtBoneScan() != null)
                    etBoneScan.setText(investigation.getEtBoneScan());

            }

        } catch (Exception e) {

        }


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<String> urls = utils.getImages(resultCode, data);
        if (urls == null) return;
        ListImageAdapter image2 = new ListImageAdapter(urls, getContext());
        switch (requestCode) {
            case PICK_IMAGE_CHEMISTRY:

                rvChemistry.setAdapter(image2);
                lists[0] = urls;
                break;
            case PICK_IMAGE_CS:
                rvCS.setAdapter(image2);
                lists[1] = urls;
                break;
            case PICK_IMAGE_CYTOLOGY:
                rvCytology.setAdapter(image2);

                lists[2] = urls;
                break;
            case PICK_IMAGE_XRAY:
                rvXray.setAdapter(image2);

                lists[3] = urls;
                break;
            case PICK_IMAGE_SCANOGRAM:
                rvScanogram.setAdapter(image2);

                lists[4] = urls;
                break;
            case PICK_IMAGE_CT:
                rvCT.setAdapter(image2);

                lists[5] = urls;
                break;
            case PICK_IMAGE_MRI:
                rvMRI.setAdapter(image2);

                lists[6] = urls;
                break;
            case PICK_IMAGE_DEXA:
                rvDexa.setAdapter(image2);

                lists[7] = urls;
                break;
            case PICK_IMAGE_BONE:
                rvDexa.setAdapter(image2);

                lists[8] = urls;
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
                                case PICK_IMAGE_CHEMISTRY:
                                    list = model_investigation.getImagesChemistry();
                                    if (list == null)
                                        list = new ArrayList<>();
                                    list.add(uri.toString());
                                    model_investigation.setImagesChemistry(list);
                                    break;
                                case PICK_IMAGE_CS:


                                    list = model_investigation.getImagesCS();
                                    if (list == null)
                                        list = new ArrayList<>();
                                    list.add(uri.toString());
                                    model_investigation.setImagesCS(list);
                                    break;

                                case PICK_IMAGE_CYTOLOGY:
                                    list = model_investigation.getImagesCytology();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_investigation.setImagesCytology(list);
                                    break;

                                case PICK_IMAGE_XRAY:

                                    list = model_investigation.getImagesXray();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_investigation.setImagesXray(list);
                                    break;

                                case PICK_IMAGE_SCANOGRAM:
                                    list = model_investigation.getImagesScanogram();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_investigation.setImagesScanogram(list);


                                case PICK_IMAGE_CT:
                                    list = model_investigation.getImagesCT();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_investigation.setImagesCT(list);
                                    break;
                                //*****
                                case PICK_IMAGE_MRI:
                                    list = model_investigation.getImagesMRI();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_investigation.setImagesMRI(list);
                                    break;

                                case PICK_IMAGE_DEXA:
                                    list = model_investigation.getImagesDEXA();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_investigation.setImagesDEXA(list);
                                    break;


                                case PICK_IMAGE_BONE:
                                    list = model_investigation.getImagesBone();
                                    if (list == null)
                                        list = new ArrayList<>();

                                    list.add(uri.toString());
                                    model_investigation.setImagesBone(list);
                                    break;


                            }

                            allInfo.setInvestigation(model_investigation);
                            databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(allInfo);
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