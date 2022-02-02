package ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao;

import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.factory.OracleDAOFactory;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.interfaces.DAOForCRUD;
import ua.edu.sumdu.j2se.chepiha.dbeditor.models.entities.Emp;

import java.sql.*;
import java.util.List;

public class OracleEmpDAO implements DAOForCRUD<Emp> {

    public OracleEmpDAO(){
        super();
    }

    private Emp parse(ResultSet resultSet){
        Emp emp = new Emp();
        try {
            int empno = resultSet.getInt("EMPNO");
            String ename = resultSet.getString("ENAME");
            String job = resultSet.getString("JOB");
            int mgr = resultSet.getInt("MGR");
            Date hiredate = resultSet.getDate("HIREDATE");
            float sal = resultSet.getFloat("SAL");
            float comm = resultSet.getFloat("COMM");
            int depno = resultSet.getInt("DEPTNO");

            emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, depno);
        } catch (SQLException throwables) {
            System.out.println("The employee does not exist");
        }
        return emp;
    }

    @Override
    public void getAll(List<Emp> listEmps) {
        if(listEmps.size()>0){
            listEmps.clear();
        }
        String sql = "select * from emp";
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()){
                listEmps.add(parse(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllMgr(List<Integer> listIdMgr) {
        if(listIdMgr.size()>0){
            listIdMgr.clear();
        }
        String sql = "select distinct mgr from emp";
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()){
                listIdMgr.add(resultSet.getInt("MGR"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Emp getOne(int id) {
        String sql = "select * from emp where empno = ?";
        Emp emp = new Emp();
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                resultSet.next();
                emp = parse(resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return emp;
    }

    @Override
    public void create(Emp employee) {
        String sql = "insert into emp (ename, job, mgr, hiredate, sal, comm, deptno) values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, employee.getEname());
            preparedStatement.setString(2, employee.getJob());
            preparedStatement.setInt(3, employee.getMgr());
            preparedStatement.setDate(4, employee.getHiredate());
            preparedStatement.setFloat(5, employee.getSal());
            preparedStatement.setFloat(6, employee.getComm());
            preparedStatement.setInt(7, employee.getDeptno());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from EMP where EMPNO = ?";
        try (Connection connection = OracleDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
