package com.albkali.phca;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.albkali.phca.Adapter.VaccinationAdapter;
import com.albkali.phca.Model.VaccinationItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.albkali.phca.NotificationChannels.CHANNEL_1_ID;


public class Vaccination extends AppCompatActivity {

    private static FirebaseFirestore db ;
    private NotificationManagerCompat notificationManager;

    RecyclerView recyclerView;
    ArrayList<VaccinationItem> posts;
    VaccinationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        getSupportActionBar().setTitle(getString(R.string.vacc_child_home_btn));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar

        notificationManager = NotificationManagerCompat.from(this);
        //call check method to check vaccination date
        check();
        //call notification method to send notification
        notification();

        recyclerView = findViewById(R.id.rv_vacc_items);
        db = FirebaseFirestore.getInstance();
        try {
            //access vaccination in firebase
            db.collection("vaccination")
                    .document("QAv2QZFhih39UGOKTTVM")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            DocumentSnapshot document = task.getResult();

                            posts = new ArrayList<>();

                            if (task.isSuccessful()) {

                                //to convert timestone to normal date
                                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");

                                //call first vacc from firestore
                                String name = task.getResult().get("1to4_name").toString();
                                String status = task.getResult().get("1to4_status").toString();
                                String date = sfd.format(task.getResult().get("1to4_date"));

                                //call second vacc from firestore
                                String name2 = task.getResult().get("cc_name").toString();
                                String status2 = task.getResult().get("cc_status").toString();
                                String date2 = sfd.format(task.getResult().get("cc_date"));

                                //send to each item in resyclerView
                                posts.add(new VaccinationItem(name, status, date));
                                posts.add(new VaccinationItem(name2, status2, date2));

                                //initialize adapter
                                adapter = new VaccinationAdapter(Vaccination.this, posts);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(Vaccination.this));

                            } else {
                                Log.d("ItinerariesSearch", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(Vaccination.this, "error", Toast.LENGTH_LONG).show();
            }
        }

    public void notification() {
        Intent activityIntent = new Intent(this, Vaccination.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0 , activityIntent,0);

        //initialize notification to send via notificatin manager class
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_hospital)
                .setContentTitle("Vaccination alert")
                .setContentText("your child vaccination date is close!!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setColor(Color.GRAY)
                .build();

        //send to notificathion manager
        notificationManager.notify(1, notification);

    }
    public void check () {

        db = FirebaseFirestore.getInstance();
        db.collection("vaccination")
                .document("QAv2QZFhih39UGOKTTVM")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                Date userDate = document.getDate("cc_date");
                                Date currentDate = Calendar.getInstance().getTime();
                                String status = task.getResult().get("cc_status").toString();

                                //check if the vaccination date is passed and not done yet
//                                if ( userDate.compareTo(currentDate) >= 0 && status.equals("waiting") ){
//                                    Toast.makeText(Vaccination.this, "comming", Toast.LENGTH_LONG).show();
//
//                                }else{
//                                    Toast.makeText(Vaccination.this, "gone", Toast.LENGTH_LONG).show();
//
//                                }

//                                Log.d("ItinerariesSearch", userDate.compareTo(c), task.getException());


                            }
                        }
                    }
                });
    }
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

