package com.example.elbagory.orthodroid.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elbagory.orthodroid.Models.RecyclerViewRow_Model;
import com.example.elbagory.orthodroid.R;
import com.example.elbagory.orthodroid.adapters.OnRecyclerViewItemClickListener;
import com.example.elbagory.orthodroid.adapters.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * this is the activity that contains list of patient item
 */

public class HomeActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener {

    public static final String PRIVATE_ID = "private id";
    private List<RecyclerViewRow_Model> recyclerViewRowModels_lists = new ArrayList<>();
    //ProgressDialog
    ProgressDialog pd;
    FloatingActionButton floatingActionButton; // add patient button
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseReference.keepSynced(true);

        pd = new ProgressDialog(this);

        floatingActionButton = findViewById(R.id.fab_add_patient);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddPatientActivity.class));
            }
        });


        // RecyclerView implement
        recyclerView = findViewById(R.id.RecyclerViewPatients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // RecyclerView adapter
        adapter = new RecyclerAdapter(recyclerViewRowModels_lists, this);
        pd.setMessage("loading");
        pd.show();

        loadData();
        adapter.setOnRecyclerViewItemClickListener(this);


    }

    /**
     * load patient info from database
     */
    private void loadData() {
        databaseReference.child("RecyclerViewRow").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                recyclerViewRowModels_lists.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    RecyclerViewRow_Model row = dataSnapshot1.getValue(RecyclerViewRow_Model.class);
                    recyclerViewRowModels_lists.add(row);
                    recyclerView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
                pd.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                pd.dismiss();

            }
        });
    }

    //send position of itme click which is the id
    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.row_main_adapter_linear_layout:
                Intent myIntent = new Intent(HomeActivity.this, UpdatePatientActivity.class);
                int id = recyclerViewRowModels_lists.get(position).getPrivate_id();
                myIntent.putExtra(PRIVATE_ID, id);
                startActivity(myIntent);
                break;
        }
    }
}
