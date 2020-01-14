package com.albkali.phca;

import com.google.firebase.firestore.Exclude;

public class itemCSHCN {

    @Exclude
    private String tTitle;
    @Exclude
    private String dDesc;
    @Exclude
    private String iImage;
    @Exclude
    private String uUrl;


    public itemCSHCN(String title, String desc, String image, String url) {
        this.tTitle = title;
        this.dDesc = desc;
        this.iImage = image;
        this.uUrl = url;
    }
    public  itemCSHCN(){

    }

    public String getTitle() {
        return tTitle;
    }

    public void setTitle(String title) {
        this.tTitle = title;
    }

    public String getDesc() {
        return dDesc;
    }

    public void setDesc(String desc) {
        this.dDesc = desc;
    }

    public String getImage() {
        return iImage;
    }

    public void setImage(String image) {
        this.iImage = image;
    }

    public String getUrl() {
        return uUrl;
    }

    public void setUrl(String url) {
        this.uUrl = url;
    }
}

