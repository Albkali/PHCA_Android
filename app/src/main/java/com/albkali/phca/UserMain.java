package com.albkali.phca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserMain extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView par_name, par_email;
    Pediatrician pediatrician;
    //    private static final int PReqCode = 2 ;
//    private static final int REQUESCODE = 2 ;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser userID ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userID = FirebaseAuth.getInstance().getCurrentUser();
        pediatrician = new Pediatrician();

        par_name = (TextView) findViewById(R.id.header_name);
        par_email = (TextView) findViewById(R.id.header_email);


        Toolbar toolbar = findViewById(R.id.ped_toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent ( UserMain.this, Chatting_Activity.class);
                startActivity(intent);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_notifications, R.id.nav_profile,
                R.id.nav_EditProfile,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


//    public void checkAndRequestForPermission() {
//
//
//        if (ContextCompat.checkSelfPermission(MyDrawer.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MyDrawer.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//                Toast.makeText(MyDrawer.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
//
//            }
//
//            else
//            {
//                ActivityCompat.requestPermissions(MyDrawer.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        PReqCode);
//            }
//
//        }
//        else
//            // everything goes well : we have permission to access user gallery
//            openGallery();
//
//    }


//    public void openGallery() {
//        //TODO: open gallery intent and wait for user to pick an image !
//
//        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        galleryIntent.setType("image/*");
//        startActivityForResult(galleryIntent,REQUESCODE);
//    }


    public void ClickToVaccination(View view) {
        Intent intent = new Intent(this, Vaccination.class);
        startActivity(intent);
    }

    public void ClickToPediatrician(View view) {

        Intent intent = new Intent(this, Pediatrician.class);
        startActivity(intent);
    }


    public void click_bnt_mbi(View view) {
        Intent intent = new Intent(this, BMI_Calculate.class);
        startActivity(intent);
    }


    public void ClickToCSHCN(View view) {
        Intent intent = new Intent(this, CSHCN.class);
        startActivity(intent);
    }

    public void ClickToArticle(View view) {
        Intent intent = new Intent(this, ArticleActivity.class);
        startActivity(intent);
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onStart() {

        super.onStart();
        try {


            if (userID != null) {
                super.onResume();
                final String id = userID.getUid();
                final String Email = userID.getEmail();

                par_email.setText(Email);


                FirebaseFirestore db = FirebaseFirestore.getInstance();

                DocumentReference noteRef =
                        db.collection("child")
                                .document(id);
                noteRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    String name = documentSnapshot.getString("childName");
                                    String lastname = documentSnapshot.getString("childLastName");
                                    par_name.setText(name + " " + lastname);

                                } else {
                                }
                            }
                        });


            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}