package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.ICarsHasCarServiceDAO;

public class CarsHasCarServiceDAO extends MySQLDAO implements ICarsHasCarServiceDAO {
    @Override
    public ICarsHasCarServiceDAO createEntity(ICarsHasCarServiceDAO entity) {
        return null;
    }

    @Override
    public ICarsHasCarServiceDAO getEntityById(long id) {
        return null;
    }

    @Override
    public boolean updateEntity(ICarsHasCarServiceDAO entity) {
        return false;
    }

    @Override
    public void removeEntity(long id) {

    }
}
