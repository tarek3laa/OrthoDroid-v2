package com.example.elbagory.orthodroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elbagory.orthodroid.Models.Model_Operation;
import com.example.elbagory.orthodroid.R;
import com.example.elbagory.orthodroid.activities.UpdatePatientActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdsmdg.tastytoast.TastyToast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.elbagory.orthodroid.activities.UpdatePatientActivity.allInfo;

/**
 * fragment contains Operation info
 */
public class OperationFragment extends Fragment {
    EditText etOperationName, etDate, etSteps, etPersonName, etFollow;

    private int Primary_key = 0, private_id;
    String name, id, time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation, container, false);


        //fire base
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();


        // get the value of Primary_key from data base fire base

        Primary_key = UpdatePatientActivity.patientID;


        // init view
        etOperationName = view.findViewById(R.id.editTextOPname);
        etDate = view.findViewById(R.id.editTextOPDate);
        etSteps = view.findViewById(R.id.editTextSteps);
        etPersonName = view.findViewById(R.id.editTextPersons);
        etFollow = view.findViewById(R.id.editTextFollowUp);


        Button button = view.findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Model_Operation model_operation = new Model_Operation(etOperationName.getText().toString(),
                        etDate.getText().toString(),
                        etSteps.getText().toString(),
                        etPersonName.getText().toString(),
                        etFollow.getText().toString(),
                        Primary_key
                );
                UpdatePatientActivity.allInfo.setOperation(model_operation);
                databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);


                TastyToast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT, TastyToast.SUCCESS).show();


            }
        });
        try {

            Model_Operation operation = null;

            if (allInfo.getOperation() != null) operation = allInfo.getOperation();
            if (operation != null) {
                // , , etPersonName, etFollow;
                if (operation.getEtOperationName() != null)
                    etOperationName.setText(operation.getEtOperationName());
                if (operation.getEtDate() != null)
                    etDate.setText(operation.getEtDate());
                if (operation.getEtSteps() != null)
                    etSteps.setText(operation.getEtSteps());
                if (operation.getEtPersonName() != null)
                    etPersonName.setText(operation.getEtPersonName());
                if (operation.getEtFollow() != null)
                    etFollow.setText(operation.getEtFollow());
            }

        } catch (Exception e) {

        }


        return view;
    }
}
