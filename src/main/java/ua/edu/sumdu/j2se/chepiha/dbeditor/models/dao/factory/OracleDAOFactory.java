package ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.factory;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.OracleDeptDAO;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.OracleEmpDAO;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.OracleSalgradeDAO;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.interfaces.DAOForCRUD;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Dept;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Emp;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Salgrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDAOFactory extends DAOFactory {

    public static final String DRIVER = "oracle.jdbc.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";
    public static final String DB_USER = "dbnctest";
    public static final String DB_PASS = "nctest";

    @Override
    public DAOForCRUD<Dept> getDeptDAO() {
        return new OracleDeptDAO();
    }

    @Override
    public DAOForCRUD<Emp> getEmpDAO() {
        return new OracleEmpDAO();
    }

    @Override
    public DAOForCRUD<Salgrade> getSalgradeDAO() {
        return new OracleSalgradeDAO();
    }

    public static Connection createConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
