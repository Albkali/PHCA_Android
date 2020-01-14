package com.albkali.phca.Model;

import java.util.Date;

public class VaccinationItem {
    private String name;
    private String status;
    private String date;
    private Date bdate;
    private String id;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getChid() {
        return id;
    }

    public void setChid(String chid) {
        this.id = chid;
    }


    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public VaccinationItem(){

    }
    public VaccinationItem(String name, String status , String date) {
        this.name = name;
        this.status = status;
        this.date = date;
    }
    public VaccinationItem(String name, String status , String date,Date bdate) {
        this.name = name;
        this.status = status;
        this.date = date;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
