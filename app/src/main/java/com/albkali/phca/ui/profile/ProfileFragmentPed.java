package com.albkali.phca.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.albkali.phca.Pediatrician;
import com.albkali.phca.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragmentPed extends Fragment {

    private  String PedName , PedEmail;
    private FirebaseAuth firebaseAuth;

    FirebaseUser user;
    Pediatrician pediatrician;

    public ProfileFragmentPed(String pedName, String pedEmail) {
        PedName = pedName;
        PedEmail = pedEmail;
    }

    public void setPedName(String pedName) {
        PedName = pedName;
    }

    public String getPedName() {
        return PedName;
    }

    public String getPedEmail() {
        return PedEmail;
    }

    public void setPedEmail(String pedEmail) {
        PedEmail = pedEmail;
    }

    public ProfileFragmentPed() {

    }
    private static final String TAG = "MainActivity";

    private TextView PedName1 , PedEmail1 ,PedPhone1;






    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_ped, container, false);

        pediatrician = new Pediatrician();

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
         FirebaseFirestore db = FirebaseFirestore.getInstance();
         DocumentReference noteRef = db.collection("Pediatrician").document(id);

        PedName1 =v.findViewById(R.id.txtViewPedName);
        PedEmail1 =v.findViewById(R.id.txtViewPedEmail);
        PedPhone1 =v.findViewById(R.id.txtViewPedPhone);


        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String PedName = documentSnapshot.getString("FirstName");
                            String PedEmail = documentSnapshot.getString("PedEmail");
                            String PedLastName = documentSnapshot.getString("LastName");
                            String PedPhone = documentSnapshot.getString("PedPhone");



                            // Map<String, Object> note = documentSnapshot.getData();

                            PedName1.setText( PedName + " " + PedLastName);
                            PedEmail1.setText(PedEmail);
                            PedPhone1.setText(PedPhone);


                        } else {
                            Toast.makeText(getActivity(), "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });


        return v;
    }


}
