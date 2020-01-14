package com.albkali.phca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    static final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public static Date getDateFromString(String datetoSaved){
        try {
            Date date = format.parse(datetoSaved);
            return date ;
        } catch (ParseException e){
            return null ;
        }

    }
}
