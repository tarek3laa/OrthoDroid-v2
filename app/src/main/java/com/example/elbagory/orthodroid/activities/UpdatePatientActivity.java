package com.example.elbagory.orthodroid.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.elbagory.orthodroid.Models.AllInfo;
import com.example.elbagory.orthodroid.R;
import com.example.elbagory.orthodroid.adapters.SectionPageAdapter;
import com.example.elbagory.orthodroid.fragments.ExaminationFragment;
import com.example.elbagory.orthodroid.fragments.HistoryFragment;
import com.example.elbagory.orthodroid.fragments.InvestigationFragment;
import com.example.elbagory.orthodroid.fragments.OperationFragment;
import com.example.elbagory.orthodroid.fragments.PatientFragment;
import com.example.elbagory.orthodroid.fragments.SurgeryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class UpdatePatientActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static AllInfo allInfo;
    public static int patientID = -1;
    ViewPager viewPager;
    ProgressDialog pd;
    private boolean isFirstTime = true;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        try {
            pd = new ProgressDialog(this);
            pd.setMessage("loading");
            pd.show();
            Intent intent = getIntent();
            patientID = intent.getIntExtra(HomeActivity.PRIVATE_ID, -1);


            databaseReference.keepSynced(true);
            loadAllData();


        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }
    }

    private void loadAllData() {
        databaseReference.child(AddPatientActivity.ALL_PATIENT).child(String.valueOf(patientID)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UpdatePatientActivity.allInfo = dataSnapshot.getValue(AllInfo.class);

                if (isFirstTime) {
                    isFirstTime = false;
                    viewPager = findViewById(R.id.container);
                    setSectionPageAdapter(viewPager);
                    TabLayout tabLayout = findViewById(R.id.tabs);
                    tabLayout.setupWithViewPager(viewPager);
                    PatientFragment.update();
                }
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * add all fragment and fragment title to the  adapter,and set this adapter to view pager
     *
     * @param viewPager container that contains fragments
     */
    private void setSectionPageAdapter(ViewPager viewPager) {
        try {


            SectionPageAdapter pageAdapter = new SectionPageAdapter(getSupportFragmentManager());
            pageAdapter.addFragment(new PatientFragment(), "Patient Info");
            pageAdapter.addFragment(new HistoryFragment(), "History");
            pageAdapter.addFragment(new InvestigationFragment(), "Investigation");
            pageAdapter.addFragment(new ExaminationFragment(), "Examination");
            pageAdapter.addFragment(new OperationFragment(), "Operation");
            pageAdapter.addFragment(new SurgeryFragment(), "Surgery");

            viewPager.setAdapter(pageAdapter);

        } catch (Exception e) {
            Log.e(TAG, "setSectionPageAdapter: ", e);
        }
    }


}
