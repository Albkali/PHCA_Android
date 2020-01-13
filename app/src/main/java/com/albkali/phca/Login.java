package com.albkali.phca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText txtEmail,txtPassword;
    private TextView joinped;
    private Button btn_login,btn_register;

    FirebaseUser user;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser() != null) {
//            user = FirebaseAuth.getInstance().getCurrentUser();
//            String id = user.getEmail();
//            if (id.equals("khalid@gmail.com") || id.equals("ped@gmail.com")) {
//                Intent groceryItemsIntent = new Intent(Login.this,
//                        UserMain.class);
//                startActivity(groceryItemsIntent);
//                finish();
//            } else {
//                Intent groceryItemsIntent = new Intent(Login.this,
//                        UserMain.class);
//                startActivity(groceryItemsIntent);
//                finish();
//            }
//
//        }
        txtEmail = (EditText) findViewById(R.id.etName);

        txtPassword = (EditText) findViewById(R.id.etPassword);
        btn_login = (Button) findViewById(R.id.login_button);
        btn_register = (Button) findViewById(R.id.toRegister_button);


        joinped = (TextView) findViewById(R.id.textView_joinus);


//        if (firebaseAuth.getCurrentUser() != null) {
//            Intent Intent = new Intent(Login_form.this,
//                    MyDrawer.class);
//            startActivity(Intent);
//            finish();
//        }
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                /*firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),UserMain.class));
                                } else {
                                    Log.i("PROBEM","Failed");
                                }

                                // ...
                            }
                        });*/


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = FirebaseAuth.getInstance().getCurrentUser();
                                    String id = user.getEmail();
                                    if (id.equals("khalid@gmail.com") || id.equals("ped@gmail.com")) {
                                        startActivity(new Intent(getApplicationContext(), UserMain.class));
//                                        to show more optione to do condtions of type users , check this link
//     https://stackoverflow.com/questions/50534695/how-to-log-in-two-different-types-of-users-to-different-activities-automatically
                                    } else {
                                        startActivity(new Intent(getApplicationContext(), UserMain.class));
                                    }

                                } else {
                                    //startActivity(new Intent(getApplicationContext(),UserMain.class));
                                    Toast.makeText(Login.this, R.string.login_faild, Toast.LENGTH_SHORT);
                                }

                                // ...
                            }
                        });

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));

            }
        });

    }

    public boolean Click_joinus(View v) {


        final EditText pedName = new EditText(this);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

//        input.setText(getIntent().getStringExtra("image_name"));
        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
        alertDialog.setView(pedName); // uncomment this line
        alertDialog.setTitle(getString(R.string.are_u_ped));
        alertDialog.setMessage(getString(R.string.connect_with_u));

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.send), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        alertDialog.show();

        return true;
    }


}
