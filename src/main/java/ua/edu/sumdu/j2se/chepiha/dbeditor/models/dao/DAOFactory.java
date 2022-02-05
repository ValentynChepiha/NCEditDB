package ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.interfaces.DAOForCRUD;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Dept;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Emp;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Salgrade;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.types.DBTypes;

public abstract class DAOFactory {

    public abstract DAOForCRUD<Dept> getDeptDAO();
    public abstract DAOForCRUD<Emp> getEmpDAO();
    public abstract DAOForCRUD<Salgrade> getSalgradeDAO();

    public static DAOFactory getDAOFactory(DBTypes dbTypes){
        switch (dbTypes){
            case MYSQL:
            case POSTGRES:
                return null;
            case ORACLE:
                return new OracleDAOFactory();
        }
        return null;
    }
}
