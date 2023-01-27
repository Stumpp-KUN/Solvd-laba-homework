package solvd.repairservice.dao;

import solvd.repairservice.models.ClientDevice;

import java.util.List;

public interface IClientDeviceDAO extends IBaseDAO<ClientDevice>{
    List<ClientDevice> getAllClientDevices();
}
