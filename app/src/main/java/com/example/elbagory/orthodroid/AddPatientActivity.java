package com.example.elbagory.orthodroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.elbagory.orthodroid.Models.AllInfo;
import com.example.elbagory.orthodroid.Models.Model_Patient;
import com.example.elbagory.orthodroid.Models.RecyclerViewRow_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddPatientActivity extends AppCompatActivity {
    static EditText etName, etId, etAge, etOccupation, etWeight, etInfo;
    public static final String ALL_PATIENT = "all_patient";
    public static final String PRIMARY_KEY = "Primary_key";
    // this Primary_key help us to get data for this user
    Integer Primary_key = 0;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        databaseReference.child("Primary_key").child("Primary_key").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Primary_key = dataSnapshot.getValue(Integer.class);
                if (Primary_key == null) Primary_key = 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        etName = findViewById(R.id.editTextName);
        etId = findViewById(R.id.editTextID);
        etAge = findViewById(R.id.editTextAge);
        etOccupation = findViewById(R.id.editTextOccupation);
        etWeight = findViewById(R.id.editTextWeight);
        etInfo = findViewById(R.id.editTextInfo);


        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (etName.getText().length() == 0 || etId.getText().length() == 0 ||
                        etAge.getText().length() == 0 || etOccupation.getText().length() == 0 ||
                        etWeight.getText().length() == 0 || etInfo.getText().length() == 0) {
                    TastyToast.makeText(AddPatientActivity.this, "Something Missed....", TastyToast.LENGTH_SHORT, TastyToast.ERROR).show();


                } else {
                    // create new patient
                    Model_Patient model_patient = new Model_Patient(etName.getText().toString(),
                            etId.getText().toString(),
                            etAge.getText().toString(),
                            etOccupation.getText().toString(),
                            etWeight.getText().toString(),
                            etInfo.getText().toString(),
                            ++Primary_key,
                            new GetTimeFromInternet().getTime()
                    );
                    AllInfo allInfo = new AllInfo();
                    allInfo.setPatient(model_patient);

                    RecyclerViewRow_Model recyclerViewRow_model = new RecyclerViewRow_Model(model_patient.getName(), model_patient.getId(), model_patient.getLastUpdate(), model_patient.getPrivate_id());
                    databaseReference.child(ALL_PATIENT).child((Primary_key).toString()).setValue(allInfo);
                    databaseReference.child("RecyclerViewRow").push().setValue(recyclerViewRow_model);
                    updateID();

                    TastyToast.makeText(AddPatientActivity.this, "added", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).show();

                }

            }
        });
    }

    private void updateID() {

        Map<String, Object> map = new HashMap<>();
        map.put(PRIMARY_KEY, Primary_key);
        databaseReference.child(PRIMARY_KEY).updateChildren(map);
        finish();
    }
}
