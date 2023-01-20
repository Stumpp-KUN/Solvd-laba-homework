package solvd.repairservice.dao;

import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Person;
import solvd.repairservice.models.person.Worker;

import java.util.List;

public interface IWorkerDAO extends IBaseDAO<Worker>{
    List<Worker> getAllWorkers();
    Worker getWorkerByName(String name);
    Worker getWorkerByOrderID(long id);
    void updateOrders(long id,List<Order> orders);
}
