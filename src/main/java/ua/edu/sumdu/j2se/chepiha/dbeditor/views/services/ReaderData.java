package ua.edu.sumdu.j2se.chepiha.dbeditor.views.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import static ua.edu.sumdu.j2se.chepiha.dbeditor.views.services.ErrorMessage.ERROR_MSG;

public class ReaderData {

    public static String queryString() {
        String result = "";
        try{
            BufferedReader reader = new BufferedReader( new InputStreamReader(System.in) );
            result = reader.readLine();
        } catch (IOException e) {
            System.out.println(ERROR_MSG);
        }
        return result;
    }

    public static int queryInt(){
        int result = -1;
        try {
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextInt();
        } catch (InputMismatchException e){
            System.out.println(ERROR_MSG);
        }
        return result;
    }

    public static float queryFloat(){
        float result = -1;
        try {
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextFloat();
        } catch (InputMismatchException e){
            System.out.println(ERROR_MSG);
        }
        return result;
    }

}
