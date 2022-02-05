package ua.edu.sumdu.j2se.chepiha.dbeditor.models.dao.interfaces;

import java.util.List;

public interface DAOForCRUD<T> {

    void getAll(List<T> listEntities);
    T getOne(int id);
    T getOne(float salary);

    void create(T entity);
    void delete(int id);
}
