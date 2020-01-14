package com.albkali.phca;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BMI_Calculate extends AppCompatActivity {

    EditText weight, height;
    TextView resulttext;
    String calculation, BMIresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__calculate);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttext = findViewById(R.id.result);
        getSupportActionBar().setTitle(getString(R.string.calculate_bmi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
    }

    public void calculateBMI(View view){
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();


        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) / 100;


        float bmi = weightValue / (heightValue * heightValue);


        if (bmi < 16) {
            BMIresult = getString(R.string.severely_under_weight);
        } else if (bmi < 18.5) {
            BMIresult = getString(R.string.under_weight);
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            BMIresult = getString(R.string.normal_weight);
        } else if (bmi >= 25 && bmi <= 29.9) {
            BMIresult = getString(R.string.over_weight);
        } else {
            BMIresult = getString(R.string.obese);
        }


        calculation = getString(R.string.bmi_is) + bmi + "\n" + getString(R.string.the_status_is) + BMIresult;


        resulttext.setText(calculation);
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
