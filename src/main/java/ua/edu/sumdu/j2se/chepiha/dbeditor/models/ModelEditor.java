package ua.edu.sumdu.j2se.chepiha.dbeditor.models;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.OracleDeptDAO;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.OracleEmpDAO;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.OracleSalgradeDAO;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Dept;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Emp;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Salgrade;

import java.util.ArrayList;
import java.util.List;

public class ModelEditor {

    OracleDeptDAO modelDept;
    OracleEmpDAO modelEmp;
    OracleSalgradeDAO modelSalgrade;

    Emp emp = new Emp();
    Emp newEmp = null;
    Salgrade salgrade = new Salgrade();
    Dept dept = new Dept();

    public ModelEditor() {
        modelDept = new OracleDeptDAO();
        modelEmp = new OracleEmpDAO();
        modelSalgrade = new OracleSalgradeDAO();
    }

    public void getEmployee(int id){
        emp = modelEmp.getOne(id);
        if(emp != null && emp.getEmpno()>0){
            salgrade = modelSalgrade.getOne(emp.getSal());
            dept = modelDept.getOne(emp.getDeptno());
        }
    }

    public int[] getAllEmpno(){
        List<Emp> listAllEmployee = new ArrayList<>();
        modelEmp.getAll(listAllEmployee);
        return listAllEmployee
                .stream()
                .mapToInt(Emp::getEmpno)
                .sorted()
                .toArray();
    }

    public int[] getAllMgr(){
        List<Integer> listAllEmployee = new ArrayList<>();
        modelEmp.getAllMgr(listAllEmployee);
        return listAllEmployee
                .stream()
                .mapToInt(Integer::intValue)
                .filter(index -> index>0)
                .sorted()
                .toArray();
    }

    public int[] getAllDepno(){
        List<Dept> listAllDept = new ArrayList<>();
        modelDept.getAll(listAllDept);
        return listAllDept
                .stream()
                .mapToInt(Dept::getDeptno)
                .sorted()
                .toArray();
    }

    public Emp getEmp() {
        return emp;
    }

    public Salgrade getSalgrade() {
        return salgrade;
    }

    public Dept getDept() {
        return dept;
    }

    public void deleteEmployee(int id){
        if(id>0){
            modelEmp.delete(id);
        }
    }

    public Emp getNewEmp(){
        newEmp = new Emp();
        return newEmp;
    }

    public void createEmployee(){
        modelEmp.create(newEmp);
    }
}
