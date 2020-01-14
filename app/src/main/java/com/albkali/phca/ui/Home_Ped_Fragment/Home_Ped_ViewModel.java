package com.albkali.phca.ui.Home_Ped_Fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Home_Ped_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Home_Ped_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ped fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}