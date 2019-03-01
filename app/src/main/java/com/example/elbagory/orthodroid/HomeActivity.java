package com.example.elbagory.orthodroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elbagory.orthodroid.Models.RecyclerViewRow_Model;
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
    List<RecyclerViewRow_Model> recyclerViewRowModels_lists = new ArrayList<>();
    //ProgressDialog
    ProgressDialog pd;

    FloatingActionButton floatingActionButton; // add patient button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //fire base
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        pd = new ProgressDialog(this);

        // init view
        floatingActionButton = findViewById(R.id.fab_add_patient);


        /**
         * list item ===> {@link R.layout.list_item }
         *
         * name ===> {@link R.id.textViewName}
         * id ===>{@link R.id.textViewID}
         * last update ===> {@link R.id.textViewLastUpdate}
         */

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddPatientActivity.class));
            }
        });


        // RecyclerView implement
        final RecyclerView recyclerView = findViewById(R.id.RecyclerViewPatients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // RecyclerView adapter
        final RecyclerAdapter adapter = new RecyclerAdapter(recyclerViewRowModels_lists, this);


        pd.setMessage("loading");
        pd.show();
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


        adapter.setOnRecyclerViewItemClickListener(this);


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
