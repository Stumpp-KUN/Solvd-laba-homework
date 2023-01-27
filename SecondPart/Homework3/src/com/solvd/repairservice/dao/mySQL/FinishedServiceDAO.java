package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.IFinishedServiceDAO;
import solvd.repairservice.models.FinishedService;

import java.util.List;

public class FinishedServiceDAO extends MySQLDAO implements IFinishedServiceDAO {
    @Override
    public FinishedService createEntity(FinishedService entity) {
        return null;
    }

    @Override
    public FinishedService getEntityById(long id) {
        return null;
    }

    @Override
    public boolean updateEntity(FinishedService entity) {
        return false;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<FinishedService> getAllFinishedServices() {
        return null;
    }

    @Override
    public FinishedService getFinishedServiceByOrderID(long id) {
        return null;
    }
}
