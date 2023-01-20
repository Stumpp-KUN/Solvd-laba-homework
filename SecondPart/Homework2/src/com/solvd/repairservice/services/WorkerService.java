package solvd.repairservice.services;

import solvd.repairservice.dao.IWorkerDAO;
import solvd.repairservice.dao.IWorkerLicenceDAO;
import solvd.repairservice.dao.mySQL.WorkerDAO;
import solvd.repairservice.dao.mySQL.WorkerLicenceDAO;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.WorkerLicence;
import solvd.repairservice.models.person.Worker;

import java.sql.SQLException;
import java.util.List;

public class WorkerService {
    private IWorkerLicenceDAO wlDAO=new WorkerLicenceDAO();
    private IWorkerDAO wDAO=new WorkerDAO();

    public Worker getWorkerById(long id) throws SQLException {
        Worker w= (Worker) wDAO.getEntityById(id);
        w.setWorkerLicence(wlDAO.getLicenceByWorkerID(id));
        return w;
    }

    public List<Worker> getAllWorkers(){
        return wDAO.getAllWorkers();
    }

    public Worker createWorker(String name, String surname, WorkerLicence wl, List<Order> orders){
        return (Worker)wDAO.createEntity(new Worker(name,surname,wl,orders));
    }

    public Worker getWorkerByName(String name){
        Worker w=wDAO.getWorkerByName(name);
        return w;
    }

    public void deleteWorkerByID(long id){
        wDAO.removeEntity(id);
    }
}
