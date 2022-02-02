package ua.edu.sumdu.j2se.chepiha.dbeditor.views.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static ua.edu.sumdu.j2se.chepiha.dbeditor.views.services.ErrorMessage.ERROR_MSG;

public class ValidateData {

    public static boolean validateNumberId(int id){
        if(id>=1){
            return true;
        }
        System.out.println(ERROR_MSG);
        return false;
    }

    public static boolean validateIdInList(int id, int[] arrId){
        for (int value : arrId) {
            if (id == value) {
                return true;
            }
        }
        System.out.println(ERROR_MSG);
        return false;
    };

    public static boolean validateFloat(float value){
        if(value >= 0){
            return true;
        }
        System.out.println(ERROR_MSG);
        return false;
    }

    public static boolean validateString(String string) {
        if(string != null && string.length()>0){
            return true;
        }
        System.out.println(ERROR_MSG);
        return false;
    }

    public static boolean validateDate(String string){
        Pattern DATE_FORMAT = Pattern.compile("^[0123]\\d[-][01]\\d[-][12]\\d\\d\\d$");

        boolean flagCheck = DATE_FORMAT.matcher(string).matches();

        if (flagCheck) {
            SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date y = s.parse(string);
            } catch (ParseException e) {
                System.out.println(ERROR_MSG);
                return false;
            }
        } else {
            System.out.println(ERROR_MSG);
        }
        return flagCheck;
    }

}
