package ua.edu.sumdu.j2se.chepiha.dbeditor.controllers;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.ModelEditor;
import ua.edu.sumdu.j2se.chepiha.dbeditor.views.ViewEditor;

public class ControllerEditor {

    ModelEditor model;
    ViewEditor view;

    public ControllerEditor() {
        model = new ModelEditor();
        view = new ViewEditor();
    }

    public  void run(){

        do {
            view.stageStart();

            switch(view.getCurrentNumber()){
                case 1:
                    view.stageSelectEmployee(model.getAllEmpno());
                    model.getEmployee(view.getCurrentNumber());
                    if(model.getEmp() != null && model.getEmp().getEmpno()>0){
                        view.stageShowEmployee(model.getEmp(), model.getSalgrade(), model.getDept());
                    }
                    break;
                case 2:
                    view.stageCreateEmployee(model.getNewEmp(), model.getAllMgr(), model.getAllDepno());
                    model.createEmployee();
                    break;
                case 3:
                    view.stageSelectEmployee(model.getAllEmpno());
                    view.stageDelete();
                    if(view.isDeleteEmployee()){
                        model.deleteEmployee(view.getCurrentNumber());
                    }
                    break;
                case 4:
                    view.stageEnd();
                    break;
                default:
                    view.stage404();
            }

        } while (view.getCurrentNumber() != 4);

    }
}
