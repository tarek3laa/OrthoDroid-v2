package com.example.elbagory.orthodroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.elbagory.orthodroid.Models.Model_Patient;
import com.example.elbagory.orthodroid.Models.RecyclerViewRow_Model;
import com.example.elbagory.orthodroid.R;
import com.example.elbagory.orthodroid.activities.AddPatientActivity;
import com.example.elbagory.orthodroid.activities.UpdatePatientActivity;
import com.example.elbagory.orthodroid.utils.GetTimeFromInternet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sdsmdg.tastytoast.TastyToast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * this fragment contains patient info
 */
public class PatientFragment extends Fragment {
    static EditText etName, etId, etAge, etOccupation, etWeight, etInfo;
    public static final String ALL_PATIENT = AddPatientActivity.ALL_PATIENT;
    public static final String PRIMARY_KEY = "Primary_key";
    // this Primary_key help us to get data for this user
    Integer Primary_key = 0;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();


    public static void update() {

        Model_Patient model_patient = UpdatePatientActivity.allInfo.getPatient();
        if (model_patient == null) System.out.println("NULL");
        etName.setText(model_patient.getName());
        etId.setText(model_patient.getId());
        etAge.setText(model_patient.getAge());
        etOccupation.setText(model_patient.getOccupation());
        etWeight.setText(model_patient.getWeight());
        etInfo.setText(model_patient.getInfo());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_info, container, false);

        // get the value of Primary_key
        Primary_key = UpdatePatientActivity.patientID;


        //init view
        etName = view.findViewById(R.id.editTextName);
        etId = view.findViewById(R.id.editTextID);
        etAge = view.findViewById(R.id.editTextAge);
        etOccupation = view.findViewById(R.id.editTextOccupation);
        etWeight = view.findViewById(R.id.editTextWeight);
        etInfo = view.findViewById(R.id.editTextInfo);


        //save to DB

        Button button = view.findViewById(R.id.button);
        Button deleteBtn = view.findViewById(R.id.delete);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllInfo();
                getActivity().finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (etName.getText().length() == 0 || etId.getText().length() == 0 ||
                        etAge.getText().length() == 0 || etOccupation.getText().length() == 0 ||
                        etWeight.getText().length() == 0 || etInfo.getText().length() == 0) {
                    TastyToast.makeText(getActivity(), "Something Missed....", TastyToast.LENGTH_SHORT, TastyToast.ERROR).show();


                } else {

                    updatePatientInfo();
                    TastyToast.makeText(getActivity(), "Saved", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).show();

                }

            }
        });


        return view;
    }

    private void deleteAllInfo() {
        UpdatePatientActivity.allInfo = null;
        databaseReference.child(ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child(String.valueOf(Primary_key)).delete();
        updateRecyclerInfo(Primary_key, true);
    }

    private void updatePatientInfo() {
        Model_Patient model_patient
                = new Model_Patient(etName.getText().toString(),
                etId.getText().toString(),
                etAge.getText().toString(),
                etOccupation.getText().toString(),
                etWeight.getText().toString(),
                etInfo.getText().toString(),
                Primary_key,
                new GetTimeFromInternet().getTime()
        );

        // for test
        // List<Pair<String,Pair<String,String>>> list =new ArrayList<>();
        // list.add(new Pair("first",new Pair<>("sce f","sec s")));
        // list.add(new Pair("f",new Pair<>("s f","s s")));
        // model_patient.setPairs(list);
        UpdatePatientActivity.allInfo.setPatient(model_patient);

        databaseReference.child(ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);


        updateRecyclerInfo(Primary_key, false);
    }

    private void updateRecyclerInfo(final Integer id, boolean delete) {
        RecyclerViewRow_Model recyclerView = null;
        if (!delete)
            recyclerView = new RecyclerViewRow_Model(etName.getText().toString(), etId.getText().toString(), new GetTimeFromInternet().getTime(), Primary_key);
        final RecyclerViewRow_Model finalRecyclerView = recyclerView;
        databaseReference.child("RecyclerViewRow").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    RecyclerViewRow_Model row = dataSnapshot1.getValue(RecyclerViewRow_Model.class);
                    if (row.getPrivate_id() == id) {
                        dataSnapshot1.getRef().setValue(finalRecyclerView);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
