package solvd.repairservice.dao;

import java.sql.SQLException;

public interface IBaseDAO <T>{
    T createEntity(T entity);
    T getEntityById(long id) throws SQLException;
    boolean updateEntity(T entity);
    void removeEntity(long id);
}
