package com.albkali.phca;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.albkali.phca.Model.VaccinationItem;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Vacci_for_Ped extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference DBReference;
    //    private final Firestore db;
    FirebaseFirestore db;
    FirebaseFirestore querySearch;

    VaccinationItem art = new VaccinationItem();
    private FirestoreRecyclerAdapter<VaccinationItem, NewsViewHolder> mPeopleRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacci_for__ped);


        getSupportActionBar().setTitle(getString(R.string.show_vaccination_schedule));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar


        DBReference = FirebaseDatabase.getInstance().getReference().child("News");
        DBReference.keepSynced(true);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycleView);

        db = FirebaseFirestore.getInstance();

        CollectionReference cities = db.collection("vaccinations");
        Query chainedQuery2 = cities.orderBy("age");

//        Query chainedQuery2 = cities.whereEqualTo("state", "CA").whereLessThan("population", 1000000L);

        FirestoreRecyclerOptions<VaccinationItem> options = new FirestoreRecyclerOptions.Builder<VaccinationItem>()
                .setQuery(chainedQuery2, VaccinationItem.class)
                .build();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mPeopleRVAdapter = new FirestoreRecyclerAdapter<VaccinationItem, NewsViewHolder>(options) {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void onBindViewHolder(Vacci_for_Ped.NewsViewHolder holder, final int position, final VaccinationItem model) {
                holder.setName(model.getName());
                holder.setAge(model.getAge());
                holder.setType(model.getType());

//vacc
//                VaccinationItem p = post.get(i);
//
//                holder.Vname.setText(p.getName());
//                holder.Vstatus.setText(p.getStatus());
//                holder.Vdate.setText(p.getDate());

//                holder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        final String url = model.getUrl();
//                        Intent intent = new Intent(getApplicationContext(), Article_WebView_activity.class);
//                        intent.putExtra("id", url);
//                        startActivity(intent);
//                    }
//                });
            }

            @Override
            public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_vacci_for_ped, parent, false);

                return new NewsViewHolder(view);

                //vacc
//                View v =LayoutInflater.from(context).inflate(R.layout.item_vaccinations,viewGroup,false);
//                return new VaccinationItemsViewHolder(v);
            }
        };

        recyclerView.setAdapter(mPeopleRVAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();


    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setName(String title){
            TextView vacci_name = (TextView)mView.findViewById(R.id.Vacci_name);
            vacci_name.setText(title);
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void setAge(String age){
            TextView vacci_date = (TextView)mView.findViewById(R.id.vacc_month);



            vacci_date.setText(age+ " month");
        }
        public void setType(String status){
            TextView vacci_status = (TextView)mView.findViewById(R.id.vacc_type);

            vacci_status.setText(status);


        }

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

