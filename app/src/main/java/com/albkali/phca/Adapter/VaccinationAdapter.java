package com.albkali.phca.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.albkali.phca.Model.VaccinationItem;
import com.albkali.phca.R;

import java.util.List;


public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.VaccinationItemsViewHolder> {

    public List<VaccinationItem> post;
    Context context;


//    private LayoutInflater mInflater;

    public VaccinationAdapter(Context context, List<VaccinationItem> postList) {
        this.context=context;
        post = postList;

        //this.mInflater = LayoutInflater.from(context);
    }
    public class VaccinationItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView Vname;
        public TextView Vstatus;
        public  TextView Vdate;
        VaccinationItem documentSnapshot;

        // DocumentSnapshot documentSnapshot;


        public VaccinationItemsViewHolder(@NonNull View itemView) {

            super(itemView);
            Vname = (TextView) itemView.findViewById(R.id.Vacci_name);
            Vstatus = (TextView) itemView.findViewById(R.id.vacc_status);
            Vdate  = (TextView) itemView.findViewById(R.id.vacc_date);

        }
//        void bind(int listIndex){
//            nameTextView.setText(mData.get(listIndex).getName());
//            messageButton.setText(mData.get(listIndex).getName());
//
//
//        }
    }


    @NonNull
    @Override
    public VaccinationItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =LayoutInflater.from(context).inflate(R.layout.item_vaccinations,viewGroup,false);
        return new VaccinationItemsViewHolder(v);

//        int layoutIdForListItem=R.layout.item_vaccinations;
//        boolean shouldAttachToParentImmediately=false;
//
//        View view = inflater.inflate(layoutIdForListItem,viewGroup,shouldAttachToParentImmediately);
//        VaccinationItemsViewHolder viewHolder = new
//                VaccinationItemsViewHolder(view);
        //View view = mInflater.inflate(R.layout.item_vaccinations, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccinationItemsViewHolder holder, int i) {

//        holder.documentSnapshot = post.get(i);
//
//        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
//        String date = sfd.format(((Timestamp) holder.documentSnapshot.get("drop_off_timestamp")).toDate());

//        holder.documentSnapshot = mData.get(i);
//
//        String Fname = holder.documentSnapshot.get("Fname").toString();
//        String Lname = holder.documentSnapshot.get("Lname").toString();
        VaccinationItem p = post.get(i);

        holder.Vname.setText(p.getName());
        holder.Vstatus.setText(p.getStatus());
        holder.Vdate.setText(p.getDate());

//        vaccinationItemsViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return post.size();
    }


}
