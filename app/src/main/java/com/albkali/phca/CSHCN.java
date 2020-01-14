package com.albkali.phca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class CSHCN extends AppCompatActivity {

    Context ctx;
    private RecyclerView recyclerView;
    private DatabaseReference DBReference;
    //    private final Firestore db;
    FirebaseFirestore db;
    FirebaseFirestore querySearch;

    itemCSHCN art = new itemCSHCN();
    private FirestoreRecyclerAdapter<itemCSHCN, NewsViewHolder> mPeopleRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cshcn);

        getSupportActionBar().setTitle(getString(R.string.CSHCN));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
        DBReference = FirebaseDatabase.getInstance().getReference().child("CSHCN");
        DBReference.keepSynced(true);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycleViewCSHCN);



        // [START fs_order_by_country_population]
        // [START fs_composite_index_chained_query]

//
//        DocumentReference personRef = db.document("article");

//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        CollectionReference citice = firebaseDatabase.getReference();
//        DatabaseReference databaseReference = firebaseDatabase.getReference("jnjjnbj");
//        Query query = FirebaseFirestore.getInstance()
//                .collection("article");
//        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child("News");
//        Query personsQuery = personsRef.orderByKey();
//        Query query = personRef1.orderBy("jnj");
//        personsQuery = FirebaseFirestore.getInstance()
//                .collection("article").document(" " ).collection("reminders")
//                .orderBy("");
//        querySearch = FirebaseFirestore.getInstance()
//                .collection("App").document(" " + userID).collection("reminders")
//                .startAt("title", newText)
//                .endAt("title", newText+"\uf8ff");
//        FirebaseRecyclerOptions<article> personsOptions = new FirebaseRecyclerOptions.Builder<article>()
//                .setQuery(personsQuery , article.class)
//                .build();
        //        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<article>()
//        .setQuery(personsQuery, article.class).build();

        db = FirebaseFirestore.getInstance();

        CollectionReference cities = db.collection("specialneed");
        Query chainedQuery2 = cities.orderBy("desc");

//        Query chainedQuery2 = cities.whereEqualTo("state", "CA").whereLessThan("population", 1000000L);

        FirestoreRecyclerOptions<itemCSHCN> options = new FirestoreRecyclerOptions.Builder<itemCSHCN>()
                .setQuery(chainedQuery2, itemCSHCN.class)
                .build();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mPeopleRVAdapter = new FirestoreRecyclerAdapter<itemCSHCN, NewsViewHolder>(options) {

            @Override
            protected void onBindViewHolder(CSHCN.NewsViewHolder holder, final int position, final itemCSHCN model) {
                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDesc());
                holder.setImage(getBaseContext(), model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(getApplicationContext(), CSHCN_WebView_activity.class);
                        intent.putExtra("id", url);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cshcn_raw, parent, false);

                return new NewsViewHolder(view);
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
        public View mView;
        public NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_titleCSHCN);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_descCSHCN = (TextView)mView.findViewById(R.id.post_descCSHCN);
            post_descCSHCN.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_imageCSHCN);

            Picasso.get().load(image).into(post_image);


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
