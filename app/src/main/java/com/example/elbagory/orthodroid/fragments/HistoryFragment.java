package com.example.elbagory.orthodroid.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.elbagory.orthodroid.Models.Model_History;
import com.example.elbagory.orthodroid.R;
import com.example.elbagory.orthodroid.activities.UpdatePatientActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdsmdg.tastytoast.TastyToast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * fragment contains History Info
 */
public class HistoryFragment extends Fragment {
    EditText etChronic, etChronicOther, etGastritis, etGastritisOther, etSmoking, etSmokingOther, etPregnancy, etPregnancyOther, etLactation, etLactationOther;
    private Model_History model_history = new Model_History();
    // this Primary_key help us to get data for this user
    private Integer Primary_key = 0;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();
    private CheckBox Chronic, Gastritis, Smoking, Pregnancy, Lactation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_history, container, false);

        try {
            System.out.println("hi");
            //fire base
            etChronic = view.findViewById(R.id.ch);
            etChronicOther = view.findViewById(R.id.other_ch);
            etGastritis = view.findViewById(R.id.ga);
            etGastritisOther = view.findViewById(R.id.other_ga);
            etSmoking = view.findViewById(R.id.smoking);
            etSmokingOther = view.findViewById(R.id.other_smoking);
            etPregnancy = view.findViewById(R.id.pr);
            etPregnancyOther = view.findViewById(R.id.other_pr);
            etLactation = view.findViewById(R.id.la);
            etLactationOther = view.findViewById(R.id.other_la);


            Chronic = view.findViewById(R.id.checkbox_ch);
            Gastritis = view.findViewById(R.id.checkbox_ga);
            Smoking = view.findViewById(R.id.checkbox_smoking);
            Pregnancy = view.findViewById(R.id.checkbox_pr);
            Lactation = view.findViewById(R.id.checkbox_la);


            Chronic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Chronic.isChecked()) {
                        etChronic.setVisibility(View.VISIBLE);
                        etChronicOther.setVisibility(View.VISIBLE);
                    } else {
                        etChronic.setVisibility(View.GONE);
                        etChronicOther.setVisibility(View.GONE);
                    }
                    model_history.setChronic(Chronic.isChecked());

                }
            });
            Gastritis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Gastritis.isChecked()) {
                        etGastritis.setVisibility(View.VISIBLE);
                        etGastritisOther.setVisibility(View.VISIBLE);
                    } else {
                        etGastritis.setVisibility(View.GONE);
                        etGastritisOther.setVisibility(View.GONE);
                    }
                    model_history.setGastritis(Gastritis.isChecked());
                }
            });
            Smoking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Smoking.isChecked()) {
                        etSmoking.setVisibility(View.VISIBLE);
                        etSmokingOther.setVisibility(View.VISIBLE);
                    } else {
                        etSmoking.setVisibility(View.GONE);
                        etSmokingOther.setVisibility(View.GONE);
                    }
                    model_history.setSmoking(Smoking.isChecked());
                }
            });
            Pregnancy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Pregnancy.isChecked()) {
                        etPregnancy.setVisibility(View.VISIBLE);
                        etPregnancyOther.setVisibility(View.VISIBLE);
                    } else {
                        etPregnancy.setVisibility(View.GONE);
                        etPregnancyOther.setVisibility(View.GONE);
                    }
                    //     model_history.setPregnancy(Pregnancy.isChecked());
                }
            });
            Lactation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Lactation.isChecked()) {
                        etLactationOther.setVisibility(View.VISIBLE);
                        etLactation.setVisibility(View.VISIBLE);
                    } else {
                        etLactationOther.setVisibility(View.GONE);
                        etLactation.setVisibility(View.GONE);
                    }
                    model_history.setLactation(Lactation.isChecked());
                }
            });


            // get the value of Primary_key from data base fire base

            Primary_key = UpdatePatientActivity.patientID;

            Button button = view.findViewById(R.id.button2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    model_history = new Model_History(etChronic.getText().toString(), etChronicOther.getText().toString(),
                            etGastritis.getText().toString(), etGastritisOther.getText().toString(),
                            etSmoking.getText().toString(), etSmokingOther.getText().toString(),
                            etPregnancy.getText().toString(), etPregnancyOther.getText().toString(),
                            etLactation.getText().toString(), etLactationOther.getText().toString());
                    model_history.setPSmokingOther("hhhhhh");
                    model_history.setSmoking(Smoking.isChecked());

                    model_history.setChronic(Chronic.isChecked());
                    model_history.setGastritis(Gastritis.isChecked());
                    model_history.setGastritis(Gastritis.isChecked());
                    model_history.setPregnancy(Pregnancy.isChecked());
                    model_history.setLactation(Lactation.isChecked());


                    UpdatePatientActivity.allInfo.setHistory(model_history);
                    System.out.println(model_history);
                    databaseReference.child(PatientFragment.ALL_PATIENT).child(String.valueOf(Primary_key)).setValue(UpdatePatientActivity.allInfo);

                    System.out.println("hellllll");

                    TastyToast.makeText(getActivity(), "Saved", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).show();

                }
            });
            try {


                if (UpdatePatientActivity.allInfo != null) {
                    model_history = UpdatePatientActivity.allInfo.getHistory();


                } else System.out.println("null at all patient ");
                //  rvSmoking,rvChronic,  rvGastritis, rvPregnancy, rvLactation;
                if (model_history != null) {
                    Smoking.setChecked(model_history.isSmoking());
                    Lactation.setChecked(model_history.isLactation());
                    Chronic.setChecked(model_history.isChronic());
                    Gastritis.setChecked(model_history.isGastritis());
                    Pregnancy.setChecked(model_history.isPregnancy());
                    if (Smoking.isChecked()) {
                        if (model_history.getPSmoking() != null)
                            etSmoking.setText(model_history.getPSmoking());
                        if (model_history.getPSmokingOther() != null)
                            etSmokingOther.setText(model_history.getPSmokingOther());
                    } else {
                        etSmoking.setVisibility(View.GONE);
                        etSmokingOther.setVisibility(View.GONE);
                    }
                    if (Chronic.isChecked()) {
                        if (model_history.getPChronic() != null)
                            etChronic.setText(model_history.getPChronic());
                        if (model_history.getPChronicOther() != null)
                            etChronicOther.setText(model_history.getPChronicOther());
                    } else {
                        etChronic.setVisibility(View.GONE);
                        etChronicOther.setVisibility(View.GONE);

                    }
                    if (Gastritis.isChecked()) {
                        if (model_history.getPGastritis() != null)
                            etGastritis.setText(model_history.getPGastritis());
                        if (model_history.getPGastritisOther() != null)
                            etGastritisOther.setText(model_history.getPGastritisOther());
                    } else {
                        etGastritis.setVisibility(View.GONE);
                        etGastritisOther.setVisibility(View.GONE);
                    }
                    if (Pregnancy.isChecked()) {
                        if (model_history.getPPregnancy() != null)
                            etPregnancy.setText(model_history.getPPregnancy());
                        if (model_history.getPPregnancyOther() != null)
                            etPregnancyOther.setText(model_history.getPPregnancyOther());
                    } else {
                        etPregnancy.setVisibility(View.GONE);
                        etPregnancyOther.setVisibility(View.GONE);
                    }
                    if (Lactation.isChecked()) {
                        if (model_history.getPLactation() != null)
                            etLactation.setText(model_history.getPLactation());
                        if (model_history.getPLactationOther() != null)
                            etLactationOther.setText(model_history.getPLactationOther());
                    } else {
                        etLactationOther.setVisibility(View.GONE);
                        etLactation.setVisibility(View.GONE);
                    }

                } else {
                    etSmoking.setVisibility(View.GONE);
                    etSmokingOther.setVisibility(View.GONE);
                    etChronic.setVisibility(View.GONE);
                    etChronicOther.setVisibility(View.GONE);
                    etGastritis.setVisibility(View.GONE);
                    etGastritisOther.setVisibility(View.GONE);
                    etPregnancy.setVisibility(View.GONE);
                    etPregnancyOther.setVisibility(View.GONE);
                    etLactationOther.setVisibility(View.GONE);
                    etLactation.setVisibility(View.GONE);
                    model_history = new Model_History();

                    System.out.println("Null at history");
                }

            } catch (Exception e) {
                Log.e(TAG, "onCreateView: ", e);
            }
        } catch (Exception e) {
            System.out.println("error history " + e);
        }
        return view;
    }


}