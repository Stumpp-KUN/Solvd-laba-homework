package solvd.repairservice.dao;

import solvd.repairservice.dao.IBaseDAO;
import solvd.repairservice.models.Service;

import java.util.List;

public interface IServiceDAO extends IBaseDAO<Service> {
    List<Service> getAllService();
}
