package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.IClientDeviceDAO;
import solvd.repairservice.models.ClientDevice;

import java.util.List;

public class ClientDeviceDAO extends MySQLDAO implements IClientDeviceDAO {
    @Override
    public ClientDevice createEntity(ClientDevice entity) {
        return null;
    }

    @Override
    public ClientDevice getEntityById(long id) {
        return null;
    }

    @Override
    public boolean updateEntity(ClientDevice entity) {
        return false;
    }

    @Override
    public void removeEntity(long id) {

    }

    @Override
    public List<ClientDevice> getAllClientDevices() {
        return null;
    }
}
