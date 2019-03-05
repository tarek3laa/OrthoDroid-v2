package com.example.elbagory.orthodroid.fragments;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.elbagory.orthodroid.AlarmReceiver;
import com.example.elbagory.orthodroid.Models.Surgery;
import com.example.elbagory.orthodroid.R;
import com.example.elbagory.orthodroid.UpdatePatientActivity;
import com.example.elbagory.orthodroid.Utils;
import com.example.elbagory.orthodroid.adapters.ListImageAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SurgeryFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private static final int PENDING_INTENT_RC = 100;
    private static final Calendar ALARM_TIME = Calendar.getInstance();
    public static final String PATIENT_ID = "pid";
    private EditText etTitle, etAddress;
    private TextView tvTime;
    private Button btNewImages;
    private RecyclerView vpImagesContainer;
    private String time = "00:00 AM";
    private String date = "12/12/1970";
    private int PICK_IMAGE_MULTIPLE = 1;
    static Surgery surgery;
    private Button btSave;
    private int progress = 0;
    private int sizeOfLists = 0;

    private ProgressBar progressBar;
    private TextView textViewProgress;

    Utils utils = new Utils();
    List<String> urls;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surgery, container, false);

        //init view
        btSave = view.findViewById(R.id.save_surgery);
        etTitle = view.findViewById(R.id.editTextTitle);
        etAddress = view.findViewById(R.id.editTextAddress);
        tvTime = view.findViewById(R.id.textViewTime);
        btNewImages = view.findViewById(R.id.buttonNewImage);
        vpImagesContainer = view.findViewById(R.id.images_container);
        vpImagesContainer.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        progressBar = view.findViewById(R.id.progressBar);
        textViewProgress = view.findViewById(R.id.textView_progress);
        try {
            Surgery surgery = UpdatePatientActivity.allInfo.getSurgery();
            if (surgery != null) {

                etTitle.setText(surgery.getTitle());
                etAddress.setText(surgery.getAddress());
                tvTime.setText(surgery.getTime());
                if (surgery.getImages() != null) {
                    ListImageAdapter imageAdapter = new ListImageAdapter(surgery.getImages(), getContext());
                    vpImagesContainer.setAdapter(imageAdapter);
                }

            }else System.out.println("NULL surgery");
        } catch (Exception e) {

        }

        // open dialog to chose date and time
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), SurgeryFragment.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
            }
        });
        // open gallery to chose images
        btNewImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.openGallery(SurgeryFragment.this, PICK_IMAGE_MULTIPLE);
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surgery = new Surgery(etTitle.getText().toString(), etAddress.getText().toString(), tvTime.getText().toString());
                setAlarm(ALARM_TIME.getTimeInMillis());
                UpdatePatientActivity.allInfo.setSurgery(surgery);
                databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(UpdatePatientActivity.patientID)).setValue(UpdatePatientActivity.allInfo);

                TastyToast.makeText(getContext(),"saved",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                uploadToStorage("surgery", urls);
            }
        });


        return view;
    }


    /**
     * This method is called when the user chooses the date
     *
     * @param datePicker
     * @param i          year
     * @param i1         month
     * @param i2         day Of month
     */
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        ALARM_TIME.set(Calendar.YEAR, i);
        ALARM_TIME.set(Calendar.MONTH, i1);
        ALARM_TIME.set(Calendar.DAY_OF_MONTH, i2);

        System.out.println(i + " " + i1 + 1 + " " + i2);
        // save choosing date
        date = String.valueOf(i2) + "/" + String.valueOf(i1 + 1) + "/" + String.valueOf(i);
        // open time picker dialog to set time
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), this, 12, 12, false);
        timePickerDialog.show();
    }

    /**
     * This method is called when the user chooses the date
     *
     * @param timePicker
     * @param i          hour
     * @param i1         minute
     */
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        ALARM_TIME.set(Calendar.HOUR_OF_DAY, i);
        ALARM_TIME.set(Calendar.MINUTE, i1);
        ALARM_TIME.set(Calendar.SECOND, 0);
        // convert time from 24 to 12 hour
        System.out.println(i);
        String state = "am";
        if (i <= 23 && i > 12) {
            i -= 12;
            state = "pm";
        } else if (i == 12) state = "pm";
        else if (i == 0) i += 12;
        // save choosing time
        time = String.valueOf(i) + " : " + String.valueOf(i1) + " " + state;

        tvTime.setText(time + "  " + date);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_MULTIPLE) {
            urls = utils.getImages(resultCode, data);

            // set images to view pager

            ListImageAdapter listImageAdapter = new ListImageAdapter(urls, getContext());
            vpImagesContainer.setAdapter(listImageAdapter);
        }
    }


    private void setAlarm(long time) {

        final long hour = (long) 3.6e+6;

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        intent.putExtra(PATIENT_ID, UpdatePatientActivity.patientID);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), PENDING_INTENT_RC, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, (time - hour), pendingIntent);

    }


    public void uploadToStorage(String path, final List<String> imageUris) {
        if (imageUris == null) {
            System.out.println("NULL");
            return;
        } else {
            System.out.println("NOT NULL");
            sizeOfLists += imageUris.size();
            progressBar.setVisibility(View.VISIBLE);
            textViewProgress.setVisibility(View.VISIBLE);
        }
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        Random random = new Random();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        final StorageReference filePath = storageRef.child(String.valueOf(UpdatePatientActivity.patientID)).child("history").child(path);
        for (String imageUri : imageUris) {
            final String image_name = String.valueOf(random.nextInt((int) 1e9));
            filePath.child(image_name).putFile(Uri.parse(imageUri)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    filePath.child(image_name).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            List<String> list = surgery.getImages();
                            if (list == null)
                                list = new ArrayList<>();
                            list.add(uri.toString());
                            surgery.setImages(list);


                            UpdatePatientActivity.allInfo.setSurgery(surgery);
                            databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(UpdatePatientActivity.patientID)).setValue(UpdatePatientActivity.allInfo);
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
            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    ++progress;
                    if (progress < sizeOfLists) {

                        textViewProgress.setText(progress + " / " + sizeOfLists + " images uploaded\n      pleas wait");
                    } else if (progress == sizeOfLists) {
                        textViewProgress.setText("uploaded Successfully");

                        textViewProgress.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        progress = 0;
                        sizeOfLists = 0;
                    }
                }
            });

        }
    }
}