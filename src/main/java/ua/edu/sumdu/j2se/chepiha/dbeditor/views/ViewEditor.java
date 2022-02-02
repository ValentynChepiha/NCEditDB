package ua.edu.sumdu.j2se.chepiha.dbeditor.views;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Dept;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Emp;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Salgrade;
import ua.edu.sumdu.j2se.chepiha.dbeditor.views.services.DateTransformation;
import ua.edu.sumdu.j2se.chepiha.dbeditor.views.services.ReaderData;
import ua.edu.sumdu.j2se.chepiha.dbeditor.views.services.ValidateData;
import ua.edu.sumdu.j2se.chepiha.dbeditor.views.types.OperationTypes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewEditor {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    // read value int
    private float currentFloat = -1;
    // read value int
    private int currentNumber = -1;
    // read value string
    private String currentString = "";
    // if false wrong value entered
    private boolean currentSelect = false;
    // if true need delete
    private boolean deleteEmployee = false;

    public ViewEditor() {
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public String getCurrentString() {
        return currentString;
    }

    public boolean isDeleteEmployee() {
        return deleteEmployee;
    }

    public void stage404(){
        System.out.println(ANSI_RED + "You enter wrong value!" + ANSI_RESET);
    }

    public void stageEnd(){
        System.out.println("Finish...");
    }

    public void stageStart(){
        showMenu();
        startSelectMenu(OperationTypes.types.MENU);
    }

    public void stageSelectEmployee(int[] arrayAllIdEmployees){
        if(arrayAllIdEmployees==null || arrayAllIdEmployees.length==0){
            System.out.println(ANSI_RED + "There are no employees." + ANSI_RESET);
            return;
        }
        do {
            System.out.println(printAllItems(arrayAllIdEmployees, "Select id employee: ") );
            startSelectMenu(OperationTypes.types.INFO);
        } while (!ValidateData.validateNumberId(currentNumber)
                || !ValidateData.validateIdInList(currentNumber, arrayAllIdEmployees));
    }

    public void stageShowEmployee(Emp employee, Salgrade salgrade, Dept dept){
        System.out.println("\nDOSSIER:");
        System.out.print("Name: " + ANSI_YELLOW + employee.getEname() + ANSI_RESET + " ");
        System.out.println("Hire date: " + ANSI_YELLOW + employee.getHiredate() + ANSI_RESET);

        System.out.print("Deptno: " + ANSI_YELLOW + employee.getDeptno() + ANSI_RESET + " ");
        System.out.print("Dep name: " + ANSI_YELLOW + dept.getDname() + ANSI_RESET + " ");
        System.out.println("Loc: " + ANSI_YELLOW + dept.getLoc() + ANSI_RESET);
        System.out.print("Job: " + ANSI_YELLOW + employee.getJob() + ANSI_RESET + " ");
        System.out.println("Id manager: " + ANSI_YELLOW + employee.getMgr() + ANSI_RESET);

        System.out.print("Sal: " + ANSI_YELLOW + employee.getSal() + ANSI_RESET + " ");
        System.out.print("ETC: " + ANSI_YELLOW + salgrade.getGrade() + ANSI_RESET + " ");
        System.out.println("Comm: " + ANSI_YELLOW + employee.getComm() + ANSI_RESET);
    }

    public void stageDelete(){
        startSelectMenu(OperationTypes.types.DELETE);
    }

    public void stageCreateEmployee(Emp employee, int[] arrAllMgr, int[] arrAllDept ) {
        System.out.println("\nEnter information about new employee:");
        do {
            System.out.print("Name: ");
            currentString = ReaderData.queryString();
        } while(!ValidateData.validateString(currentString));
        employee.setEname(currentString);

        do {
            System.out.print("Job: ");
            currentString = ReaderData.queryString();
        } while(!ValidateData.validateString(currentString));
        employee.setJob(currentString);

        do {
            System.out.println(printAllItems(arrAllMgr, "Our managers: "));
            System.out.print("MGR: ");
            currentNumber = ReaderData.queryInt();
        } while(!ValidateData.validateNumberId(currentNumber)
                || !ValidateData.validateIdInList(currentNumber, arrAllMgr)
        );
        employee.setMgr(currentNumber);

        do {
            System.out.print("Hiredate (dd-mm-yyyy): ");
            currentString = ReaderData.queryString();
        } while(!ValidateData.validateDate(currentString));
        employee.setHiredate(DateTransformation.stringToDate(currentString) );

        do {
            System.out.print("Sal: ");
            currentFloat = ReaderData.queryFloat();
        } while(!ValidateData.validateFloat(currentFloat));
        employee.setSal(currentFloat);

        do {
            System.out.print("Comm: ");
            currentFloat = ReaderData.queryFloat();
        } while(!ValidateData.validateFloat(currentFloat));
        employee.setComm(currentFloat);

        do {
            System.out.println(printAllItems(arrAllDept, "Our departments: "));
            System.out.print("Deptno: ");
            currentNumber = ReaderData.queryInt();
        } while(!ValidateData.validateNumberId(currentNumber));
        employee.setDeptno(currentNumber);
    }

    private void showMenu(){
        System.out.println(ANSI_CYAN);
        System.out.println("Select menu item:");
        System.out.println("["+ ANSI_YELLOW + "1" + ANSI_CYAN + "] Information about the employee.");
        System.out.println("["+ ANSI_YELLOW + "2" + ANSI_CYAN + "] Add an employee");
        System.out.println("["+ ANSI_YELLOW + "3" + ANSI_CYAN + "] Delete the employee");
        System.out.println("["+ ANSI_YELLOW + "4" + ANSI_CYAN + "] Exit" + ANSI_RESET);
    }

    private String printAllItems(int[] arrInt, String start){
        StringBuilder result = new StringBuilder();

        result.append(start).append(ANSI_YELLOW);
        for(int i=0; i<arrInt.length; i++){
            result.append(arrInt[i])
                    .append( i!=arrInt.length-1? ", " : "." + ANSI_RESET);
        }
        return result.toString();
    }

    private void startSelectMenu(OperationTypes.types operation){
        currentSelect = false;
        while(!currentSelect){
            switch (operation){
                case MENU:
                case INFO:
                    queryNumber();
                    break;
                case DELETE:
                    queryYesNo();
                    break;
            }
        }
    }

    private void queryNumber(){
        currentNumber = -1;
        try {
            System.out.print("Enter the number: ");
            Scanner scanner = new Scanner(System.in);
            currentNumber = scanner.nextInt();
            currentSelect = true;
        } catch (InputMismatchException e){
            currentSelect = false;
            System.out.println(ANSI_RED + "You can enter only numbers!!!" + ANSI_RESET);
        }
    }

    private void queryYesNo(){
        currentString = "";
        deleteEmployee = false;

        System.out.print("Are you sure (" + ANSI_YELLOW + "Y/N" + ANSI_RESET + ")? ");
        Scanner scanner = new Scanner(System.in);
        currentString = scanner.nextLine();

        if(currentString.equals("Y") || currentString.equals("N")
                || currentString.equals("y") || currentString.equals("n")){
            currentSelect = true;
            deleteEmployee = currentString.equals("Y") || currentString.equals("y");
        } else {
            currentSelect = false;
        }
    }

}
