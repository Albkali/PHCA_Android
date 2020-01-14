package com.albkali.phca.ui.Home_Ped_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.albkali.phca.R;


public class Home_Ped_Fragment extends Fragment {

    private Home_Ped_ViewModel Home_Ped_ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Home_Ped_ViewModel =
                ViewModelProviders.of(this).get(Home_Ped_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_ped, container, false);
//        final TextView textView = root.findViewById(R.id.text_ped);
//        slideshowViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}