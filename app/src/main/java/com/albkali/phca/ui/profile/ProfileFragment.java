package com.albkali.phca.ui.profile;

import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.albkali.phca.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
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


public class ProfileFragment extends Fragment {


    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    FirebaseUser user;
    private TextView txtViewname, txtviewchildPhone, txtviewmotherName, txtviewbloodtype,
            txtviewchildGender, txtviewAge, txtviewHeight, txtviewWeight;
    private TextView txtViewEmail;

    private ImageView imgprofile ;

    private FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();



    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        StorageReference storageReference = firebaseStorage.getReference();


        txtViewname = (TextView) v.findViewById(R.id.txtViewFname);
        txtViewEmail = (TextView) v.findViewById(R.id.txtViewchildEmail);


        txtviewchildPhone = (TextView) v.findViewById(R.id.txtViewchildPhone);
        txtviewmotherName = (TextView) v.findViewById(R.id.txtViewmahterName);
        txtviewbloodtype = (TextView) v.findViewById(R.id.txtViewbloodtype);
        txtviewchildGender = (TextView) v.findViewById(R.id.txtViewchildGender);
        txtviewAge = (TextView) v.findViewById(R.id.txtViewAge);
        txtviewHeight = (TextView) v.findViewById(R.id.txtViewHeight);
        txtviewWeight = (TextView) v.findViewById(R.id.txtViewWeight);

        imgprofile = (ImageView) v.findViewById(R.id.txtViewImage);

        user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();

        storageReference.child("ProfileImage").child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).fit().centerInside().into(imgprofile);

            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        final String id = userID.getUid();
        final String Email = userID.getEmail();
//        String BDate = getArguments().getString("birthDate");

        txtViewEmail.setText(Email);


        Log.i("my id", id);
        try {


            if (userID != null) {

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


//            String name = userID.getDisplayName();
                String email = userID.getEmail();


                // Check if user's email is verified
                boolean emailVerified = userID.isEmailVerified();

                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getIdToken() instead.
                String uid = userID.getUid();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}