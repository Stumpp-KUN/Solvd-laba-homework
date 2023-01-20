package solvd.repairservice.dao;

import solvd.repairservice.models.WorkerLicence;

import java.util.List;

public interface IWorkerLicenceDAO extends IBaseDAO<WorkerLicence>{
    List<WorkerLicence> getAllWorkerLicence();
    List<WorkerLicence> getAllWorkerLicenceByCountry(String country);
    WorkerLicence getLicenceByWorkerID(long id);
}
