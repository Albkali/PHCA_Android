package com.albkali.phca;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

//import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lodeLocale();
        setContentView(R.layout.settings_activity);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.settings, new SettingsFragment())
//                .commit();
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        // call button with listener
        Button button = (Button)findViewById(R.id.ChangeLanguge);
        button.setOnClickListener(this);
    }

    //by emplement method of OnClickListener in class of settings
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        // Toast.makeText(Settings.this, "Settings clicked", Toast.LENGTH_SHORT).show();
        showChangeLanguageDialog();
    }

    //.....................................................................................................
    public void showChangeLanguageDialog() {

        final String[] listItem = {"English","عربي"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
        mBuilder.setTitle("chose the Languge اختر اللغة...");
        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which==0){

                    setLocale("en");
                    recreate();
                    Intent intent = new Intent(SettingsActivity.this, Login.class);
                    startActivity(intent);
                }
                else if (which==1){

                    setLocale("ar");
                    recreate();
                    Intent intent = new Intent(SettingsActivity.this, Login.class);
                    startActivity(intent);

                }
//                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//                firebaseAuth.signOut();
//                Intent intent = new Intent ( getActivity().);
//                startActivity(intent);

            }
        });

        AlertDialog mDialog  =mBuilder.create();
        mDialog.show();

    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        //save date to sheard prefernce
        SharedPreferences.Editor Editor= getSharedPreferences("settings", MODE_PRIVATE).edit();
        Editor.putString("My languge",lang);
        Editor.apply();
    }

    public  void lodeLocale() {
        //lode languge saved in sheard prefernce
        //SharedPreferences prefes = getPreferences("settings",Activity.MODE_PRIVATE);
        SharedPreferences prefes = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String languge =prefes.getString("My languge","");
        setLocale(languge);
    }
//.................................................................................................................

//    public static class SettingsFragment extends PreferenceFragmentCompat {
//        @Override
//        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//            setPreferencesFromResource(R.xml.root_preferences, rootKey);
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