package com.albkali.phca;

import java.util.Date;

public class Child {

    private  String ChildName , ChildLastName,password,Email ,childMotherName , Phone ;
    private Integer  Height ,Weight,Birthday ;
    Date bday;
    String Gender ;
    String Blood ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    String photoUrl ;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    String uid ;


    public Child(String photoUrl, String uid) {
        this.photoUrl = photoUrl;
        this.uid = uid;
    }

    public Date getBirthday() {
        return bday;
    }

    public Child() {

    }

    public void setBirthday(Date birthday) {
        bday = birthday;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone( String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


//    public Integer getHeight() {
//        return Height;
//    }

//    public void setHeight(Integer height) {
//        Height = height;
//    }
//
//    public Integer getWeight() {
//        return Weight;
//    }
//
//    public void setWeight(Integer weight) {
//        Weight = weight;
//    }


    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public Child(String childName, String childLastName,  String childMotherName,
                 String phone, Integer height, Integer weight, Date birthday, String gender, String blood) {
        ChildName = childName;
        ChildLastName = childLastName;
        this.childMotherName = childMotherName;
        Phone = phone;
        Height = height;
        Weight = weight;
        bday = birthday;
        Gender = gender;
        Blood = blood;
    }



    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public String getChildLastName() {
        return ChildLastName;
    }

    public void setChildLastName(String childLastName) {
        ChildLastName = childLastName;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }

    public String getChildMotherName() {
        return childMotherName;
    }

    public void setChildMotherName(String childMotherName) {
        this.childMotherName = childMotherName;
    }




}