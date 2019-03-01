package com.example.elbagory.orthodroid;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.HashMap;

public class ShowAllPatientInfo extends AppCompatActivity {
    static  int  _id = 0;
    //Patient part
     EditText Name, Id, Age, Occupation, Weight, Info;
    //History part
     EditText Chronic, Gastritis, Smoking, Pregnancy, Lactation;
    //Investigation part
    EditText Chemistry, CS, Cytology, Xray, Scanogram, CT, MRI, DEXA, BoneScan;
    //Examination part
     EditText Trauma, Knee, Shoulder, Spine, Pelvis, Foot, Elbow;
    //Patient part
    EditText OperationName, Date, Steps, PersonName, Follow;

     DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_patient_info);

        //fire base
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //receive the id
        Intent mIntent = getIntent();
         _id = mIntent.getIntExtra("intVariableName", 0);// 0 is the default value

        //ProgressDialog
         ProgressDialog pd = new ProgressDialog(this);


        Name = findViewById(R.id.editTextName);
        Id = findViewById(R.id.editTextID);
        Age = findViewById(R.id.editTextAge);
        Occupation = findViewById(R.id.editTextOccupation);
        Weight = findViewById(R.id.editTextWeight);
        Info = findViewById(R.id.editTextInfo);
        //
        Chronic = findViewById(R.id.ch);
        Gastritis = findViewById(R.id.ga);
        Smoking = findViewById(R.id.smoking);
        Pregnancy = findViewById(R.id.pr);
        Lactation = findViewById(R.id.la);
        //
        Chemistry = findViewById(R.id.editTextChemistry);
        CS = findViewById(R.id.editTextCS);
        Cytology = findViewById(R.id.editTextCytology);
        Xray = findViewById(R.id.editTextX_ray);
        Scanogram = findViewById(R.id.editTextScanogram);
        CT = findViewById(R.id.editTextC_T);
        MRI = findViewById(R.id.editTextMRI);
        DEXA = findViewById(R.id.editTextDEXA);
        BoneScan = findViewById(R.id.editTextBone);
        //
        Trauma = findViewById(R.id.editTextTrauma);
        Knee = findViewById(R.id.editTextKnee);
        Shoulder = findViewById(R.id.editTextShoulder);
        Spine = findViewById(R.id.editTextSpine);
        Pelvis = findViewById(R.id.editTextPelvis);
        Foot = findViewById(R.id.editTextFoot);
        Elbow = findViewById(R.id.editTextElbow);
        //
        OperationName = findViewById(R.id.editTextOPname);
        Date = findViewById(R.id.editTextOPDate);
        Steps = findViewById(R.id.editTextSteps);
        PersonName = findViewById(R.id.editTextPersons);
        Follow = findViewById(R.id.editTextFollowUp);
//////////////////////////////////////////////////////////////////////




//methods to get patient data by his id

        GetPatient(databaseReference,"Patients","private_id",_id,pd);
        GetHistory(databaseReference,"History","private_id",_id,pd);
        GetExamination(databaseReference,"Examination","private_id",_id,pd);
        GetInvestigation(databaseReference,"Investigation","private_id",_id,pd);
        GetOperation(databaseReference,"Operation","private_id",_id,pd);

/////////////////////////////////////////////////////////////////////////////////////////////////////



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.saved) {

            GetPatientKey(databaseReference,"Patients","private_id",_id);
            GetHistoryKey(databaseReference,"History","private_id",_id);
            GetExaminationKey(databaseReference,"Examination","private_id",_id);
            GetInvestigationKey(databaseReference,"Investigation","private_id",_id);
            GetOperationKey(databaseReference,"Operation","private_id",_id);
            RecyclerViewRow(databaseReference,"RecyclerViewRow","private_id",_id);

            TastyToast.makeText(this, "Successfully updated", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);

        }


        return super.onOptionsItemSelected(item);
    }

    private void GetPatient(DatabaseReference databaseReference, String childname , String orderchild , int id, final ProgressDialog pd){
        pd.setMessage("loading");
        pd.show();
        final Query query = databaseReference.child(childname).orderByChild(orderchild).equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    String  name = dataSnapshot1.child("name").getValue().toString();
                    String  id = dataSnapshot1.child("id").getValue().toString();
                    String  age = dataSnapshot1.child("age").getValue().toString();
                    String  weight = dataSnapshot1.child("weight").getValue().toString();
                    String  occupation = dataSnapshot1.child("occupation").getValue().toString();
                    String  info = dataSnapshot1.child("info").getValue().toString();

                    Name.setText(name);
                    Id.setText(id);
                    Age.setText(age);
                    Occupation.setText(occupation);
                    Weight.setText(weight);
                    Info.setText(info);

                }

                pd.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();

            }
        });

    }

    private void GetHistory(DatabaseReference databaseReference, String childname , String orderchild , int id, final ProgressDialog pd){
        pd.setMessage("loading");
        pd.show();
        final Query query = databaseReference.child(childname).orderByChild(orderchild).equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    String  pchronic = dataSnapshot1.child("pchronic").getValue().toString();
                    String  pgastritis = dataSnapshot1.child("pgastritis").getValue().toString();
                    String  plactation = dataSnapshot1.child("plactation").getValue().toString();
                    String  ppregnancy = dataSnapshot1.child("ppregnancy").getValue().toString();
                    String  psmoking = dataSnapshot1.child("psmoking").getValue().toString();


                    Chronic.setText(pchronic);
                    Gastritis.setText(pgastritis);
                    Smoking.setText(psmoking);
                    Pregnancy.setText(ppregnancy);
                    Lactation.setText(plactation);



                }

                pd.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();

            }
        });

    }

    private void GetInvestigation(DatabaseReference databaseReference, String childname , String orderchild , int id, final ProgressDialog pd){
        pd.setMessage("loading");
        pd.show();
        final Query query = databaseReference.child(childname).orderByChild(orderchild).equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    String  etBoneScan = dataSnapshot1.child("etBoneScan").getValue().toString();
                    String  etCS = dataSnapshot1.child("etCS").getValue().toString();
                    String  etCT = dataSnapshot1.child("etCT").getValue().toString();
                    String  etChemistry = dataSnapshot1.child("etChemistry").getValue().toString();
                    String  etCytology = dataSnapshot1.child("etCytology").getValue().toString();
                    String  etDEXA = dataSnapshot1.child("etDEXA").getValue().toString();
                    String  etMRI = dataSnapshot1.child("etMRI").getValue().toString();
                    String  etScanogram = dataSnapshot1.child("etScanogram").getValue().toString();
                    String  etXray = dataSnapshot1.child("etXray").getValue().toString();


                    Chemistry.setText(etChemistry);
                    CS.setText(etCS);
                    Cytology.setText(etCytology);
                    Xray.setText(etXray);
                    Scanogram.setText(etScanogram);
                    CT.setText(etCT);
                    MRI.setText(etMRI);
                    DEXA.setText(etDEXA);
                    BoneScan.setText(etBoneScan);


                }

                pd.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();

            }
        });

    }

    private void GetExamination(DatabaseReference databaseReference, String childname , String orderchild , int id, final ProgressDialog pd){
        pd.setMessage("loading");
        pd.show();
        final Query query = databaseReference.child(childname).orderByChild(orderchild).equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    String  etElbow = dataSnapshot1.child("etElbow").getValue().toString();
                    String  etFoot = dataSnapshot1.child("etFoot").getValue().toString();
                    String  etKnee = dataSnapshot1.child("etKnee").getValue().toString();
                    String  etPelvis = dataSnapshot1.child("etPelvis").getValue().toString();
                    String  etShoulder = dataSnapshot1.child("etShoulder").getValue().toString();
                    String  etSpine = dataSnapshot1.child("etSpine").getValue().toString();
                    String  etTrauma = dataSnapshot1.child("etTrauma").getValue().toString();

                    Trauma.setText(etTrauma);
                    Knee.setText(etKnee);
                    Shoulder.setText(etShoulder);
                    Spine.setText(etSpine);
                    Pelvis.setText(etPelvis);
                    Foot.setText(etFoot);
                    Elbow.setText(etElbow);



                }

                pd.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();

            }
        });

    }

    private void GetOperation(DatabaseReference databaseReference, String childname , String orderchild , int id, final ProgressDialog pd){
        pd.setMessage("loading");
        pd.show();
        final Query query = databaseReference.child(childname).orderByChild(orderchild).equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                    String  etDate = dataSnapshot1.child("etDate").getValue().toString();
                    String  etFollow = dataSnapshot1.child("etFollow").getValue().toString();
                    String  etOperationName = dataSnapshot1.child("etOperationName").getValue().toString();
                    String  etPersonName = dataSnapshot1.child("etPersonName").getValue().toString();
                    String  etSteps = dataSnapshot1.child("etSteps").getValue().toString();



                    OperationName.setText(etOperationName);
                    Date.setText(etDate);
                    Steps.setText(etSteps);
                    PersonName.setText(etPersonName);
                    Follow.setText(etFollow);



                }

                pd.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();

            }
        });

    }

   // these methods to get the key of user and update his info.

    private void GetPatientKey(DatabaseReference databaseReference, final String childname , String orderchild , int id){
        databaseReference.child(childname)
                .orderByChild(orderchild)
                .equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            String key = childSnapshot.getKey();

                            HashMap<String, Object> result = new HashMap<>();


                            result.put("name", Name.getText().toString());
                            result.put("id", Id.getText().toString());
                            result.put("age", Age.getText().toString());
                            result.put("weight", Occupation.getText().toString());
                            result.put("occupation", Weight.getText().toString());
                            result.put("info", Info.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child(childname).child(key)
                                    .updateChildren(result);

                              }


                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {

                          }
                      });


    }


    private void GetHistoryKey(DatabaseReference databaseReference, final String childname , String orderchild , int id){
        databaseReference.child(childname)
                .orderByChild(orderchild)
                .equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key = childSnapshot.getKey();

                            HashMap<String, Object> result = new HashMap<>();


                            result.put("pchronic", Chronic.getText().toString());
                            result.put("pgastritis", Gastritis.getText().toString());
                            result.put("plactation", Lactation.getText().toString());
                            result.put("ppregnancy", Pregnancy.getText().toString());
                            result.put("psmoking", Smoking.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child(childname).child(key)
                                    .updateChildren(result);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void GetInvestigationKey(DatabaseReference databaseReference, final String childname , String orderchild , int id){
        databaseReference.child(childname)
                .orderByChild(orderchild)
                .equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key = childSnapshot.getKey();

                            HashMap<String, Object> result = new HashMap<>();


                            result.put("etBoneScan", BoneScan.getText().toString());
                            result.put("etCS", CS.getText().toString());
                            result.put("etCT", CT.getText().toString());
                            result.put("etChemistry", Chemistry.getText().toString());
                            result.put("etCytology", Cytology.getText().toString());
                            result.put("etDEXA", DEXA.getText().toString());
                            result.put("etMRI", MRI.getText().toString());
                            result.put("etScanogram", Scanogram.getText().toString());
                            result.put("etXray", Xray.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child(childname).child(key)
                                    .updateChildren(result);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void GetExaminationKey(DatabaseReference databaseReference, final String childname , String orderchild , int id){
        databaseReference.child(childname)
                .orderByChild(orderchild)
                .equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key = childSnapshot.getKey();

                            HashMap<String, Object> result = new HashMap<>();


                            result.put("etElbow", Elbow.getText().toString());
                            result.put("etFoot", Foot.getText().toString());
                            result.put("etKnee", Knee.getText().toString());
                            result.put("etPelvis", Pelvis.getText().toString());
                            result.put("etShoulder", Shoulder.getText().toString());
                            result.put("etSpine", Spine.getText().toString());
                            result.put("etTrauma", Trauma.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child(childname).child(key)
                                    .updateChildren(result);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void GetOperationKey(DatabaseReference databaseReference, final String childname , String orderchild , int id){
        databaseReference.child(childname)
                .orderByChild(orderchild)
                .equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key = childSnapshot.getKey();

                            HashMap<String, Object> result = new HashMap<>();


                            result.put("etDate", Date.getText().toString());
                            result.put("etFollow", Follow.getText().toString());
                            result.put("etOperationName", OperationName.getText().toString());
                            result.put("etPersonName", PersonName.getText().toString());
                            result.put("etSteps", Steps.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child(childname).child(key)
                                    .updateChildren(result);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void RecyclerViewRow(DatabaseReference databaseReference, final String childname , String orderchild , int id){
        databaseReference.child(childname)
                .orderByChild(orderchild)
                .equalTo(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                            String key = childSnapshot.getKey();

                            HashMap<String, Object> result = new HashMap<>();


                            result.put("name", Name.getText().toString());
                            result.put("id", Id.getText().toString());
                            result.put("time", new GetTimeFromInternet().getTime());

                            FirebaseDatabase.getInstance().getReference().child(childname).child(key)
                                    .updateChildren(result);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }






}
