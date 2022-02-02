package ua.edu.sumdu.j2se.chepiha.dbeditor.views.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTransformation {

    public static Date stringToDate(String string){
        Date result = null;
        SimpleDateFormat template = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date utilDate = template.parse(string);
            result = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
