package solvd.repairservice.dao;

import solvd.repairservice.models.FinishedService;

import java.util.List;

public interface IFinishedServiceDAO extends IBaseDAO<FinishedService>{
    List<FinishedService> getAllFinishedServices();
    FinishedService getFinishedServiceByOrderID(long id);
}
