package com.albkali.phca.ChildList;

import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;


import com.albkali.phca.Chatting_Activity;
import com.albkali.phca.Login;
import com.albkali.phca.PedChildVaccination;
import com.albkali.phca.R;
import com.albkali.phca.SettingsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Child4Ped extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    FirebaseUser user;
    private TextView txtViewname, txtviewchildPhone, txtviewmotherName, txtviewbloodtype,
            txtviewchildGender, txtviewAge, txtviewHeight, txtviewWeight;
    private TextView txtViewEmail;

    private ImageView imgprofile ;

    private Button btn_child_vacc;
    private FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();

    private AppBarConfiguration mAppBarConfiguration;


    @Nullable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child4_ped);

        getSupportActionBar().setTitle(getString(R.string.children));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent ( Child4Ped.this, Chatting_Activity.class);
                startActivity(intent);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        btn_child_vacc = (Button)findViewById(R.id.button_show_vacc);


        Bundle b = getIntent().getExtras();
        final String id = b.getString("id");

        btn_child_vacc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(getApplicationContext(), PedChildVaccination.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        StorageReference storageReference = firebaseStorage.getReference();


        txtViewname = (TextView) findViewById(R.id.txtViewFname);
        txtViewEmail = (TextView) findViewById(R.id.txtViewchildEmail);


        txtviewchildPhone = (TextView) findViewById(R.id.txtViewchildPhone);
        txtviewmotherName = (TextView) findViewById(R.id.txtViewmahterName);
        txtviewbloodtype = (TextView) findViewById(R.id.txtViewbloodtype);
        txtviewchildGender = (TextView) findViewById(R.id.txtViewchildGender);
        txtviewAge = (TextView) findViewById(R.id.txtViewAge);
        txtviewHeight = (TextView) findViewById(R.id.txtViewHeight);
        txtviewWeight = (TextView) findViewById(R.id.txtViewWeight);

        imgprofile = (ImageView) findViewById(R.id.txtViewImage);


//        user = FirebaseAuth.getInstance().getCurrentUser();
//        String id = user.getUid();

        storageReference.child("ProfileImage").child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerInside().into(imgprofile);

            }
        });



//
//    @Override
//    public void onResume() {
//        super.onResume();
////        final String id = userID.getUid();
////        final String Email = userID.getEmail();
////        String BDate = getArguments().getString("birthDate");
//
////        txtViewEmail.setText(Email);
////
////
////        Log.i("my id", id);
//        try {
//
//
//            if (userID != null) {

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                DocumentReference noteRef =
                        db.collection("child")
                                .document(id);


                DocumentReference noteRef2 =
                        db.collection("child")
                                .document(id)
                                .collection("IBM")
                                .document(id);




                noteRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    // String h = documentSnapshot.getLong("weight") + "" ;
                                    String name = documentSnapshot.getString("childName");
                                    String lastname = documentSnapshot.getString("childLastName");

                                    String childmothername = documentSnapshot.getString("childMotherName");
                                    String chlidboodtype = documentSnapshot.getString("blood");
                                    String childgender = documentSnapshot.getString("gender");
                                    String childPhone = documentSnapshot.getString("phone");
//                                    String childage = documentSnapshot.getLong("birthday") + "";


                                    txtViewname.setText(name + " " + lastname);

                                    txtviewmotherName.setText(childmothername);
                                    txtviewbloodtype.setText(chlidboodtype);
                                    txtviewchildGender.setText(childgender);
                                    txtviewchildPhone.setText(childPhone);


                                    // Map<String, Object> note = documentSnapshot.getData();

                                } else {
//                                Toast.makeText(ProfileFragment.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                noteRef2.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    String childWeight = documentSnapshot.getLong("weight") + "";
                                    String childHeight = documentSnapshot.getLong("height") + "";

                                    txtviewWeight.setText(childWeight);
                                    txtviewHeight.setText(childHeight);


                                    // Map<String, Object> note = documentSnapshot.getData();

                                } else {
//                                Toast.makeText(ProfileFragment.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                db.collection("child")
                        .document(id)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            Date datetest = document.getTimestamp("birthday").toDate();

                            Calendar calander = Calendar.getInstance();
                            calander.setTime(datetest);
                            int userBirthDay = calander.get(Calendar.DAY_OF_MONTH);
                            int userBirthMonth = calander.get(Calendar.MONTH);
                            int userBirthYear = calander.get(Calendar.YEAR);

                            LocalDate userBirthDate = LocalDate.of(userBirthYear, userBirthMonth,userBirthDay );
                            LocalDate currentDate = LocalDate.now(ZoneId.systemDefault());
                            final Period p = Period.between(userBirthDate, currentDate);

                            String y = p.getYears() + "y" + (p.getYears() > 1 ? "s " : " ");
                            String m = (p.getMonths()-1) + "m" + (p.getMonths() > 1 ? "s" : " and ");
                            String d = (p.getDays()+1) + "d" + (p.getDays() > 1 ? "s.\n" : ".\n");
                            txtviewAge.setText(y+m+d);


                        }
                    }
                });





            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(item.getItemId() == R.id.action_logout){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent = new Intent ( this, Login.class);
        startActivity(intent);

    }
    else {

        if(item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
    }
    return super.onOptionsItemSelected(item);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_drawer, menu);
        return true;
    }



}
