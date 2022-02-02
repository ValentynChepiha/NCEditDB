package ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.factory.OracleDAOFactory;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.interfaces.DAOForCRUD;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Dept;

import java.sql.*;
import java.util.List;

public class OracleDeptDAO implements DAOForCRUD<Dept> {

    public OracleDeptDAO(){
        super();
    }

    private Dept parse(ResultSet resultSet){
        Dept dept = new Dept();
        try {
            int deptno = resultSet.getInt("DEPTNO");
            String dname = resultSet.getString("DNAME");
            String loc = resultSet.getString("LOC");
            dept = new Dept(deptno, dname, loc);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dept;
    }

    @Override
    public void getAll(List<Dept> listDepts) {
        String sql = "select * from dept";
        if(listDepts.size()>0){
            listDepts.clear();
        }
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()){
                listDepts.add(parse(resultSet));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Dept getOne(int id) {
        Dept dept = new Dept();
        String sql = "select * from dept where deptno = ?";
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                dept = parse(resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dept;
    }

    @Override
    public void create(Dept entity) {
    }


    @Override
    public void delete(int id) {
    }

}
