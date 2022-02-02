package ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.factory.OracleDAOFactory;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.interfaces.DAOForCRUD;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Salgrade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OracleSalgradeDAO implements DAOForCRUD<Salgrade> {

    public OracleSalgradeDAO(){
        super();
    }

    private Salgrade parse(ResultSet resultSet){
        Salgrade salgrade = new Salgrade();
        try {
            int grade = resultSet.getInt("GRADE");
            float minsal = resultSet.getFloat("MINSAL");
            float hisal = resultSet.getFloat("HISAL");
            salgrade = new Salgrade(grade, minsal, hisal);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return salgrade;
    }


    @Override
    public void getAll(List<Salgrade> listSalgrades) {
        if(listSalgrades.size()>0){
            listSalgrades.clear();
        }
        String sql = "select * from salgrade";
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()){
                listSalgrades.add(parse(resultSet));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Salgrade getOne(float salary){
        String sql = "select * from salgrade where ? between minsal and hisal";
        Salgrade salgrade = new Salgrade();

        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setFloat(1, salary);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                resultSet.next();
                salgrade = parse(resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return salgrade;
    }

    @Override
    public Salgrade getOne(int id) {
        return null;
    }

    @Override
    public void create(Salgrade entity) {
    }

    @Override
    public void delete(int id) {

    }
}
