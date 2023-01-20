package solvd.repairservice.dao;

import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Person;

import java.util.List;

public interface IClientDAO extends IBaseDAO<Client> {
    List<Client> getAllClients();
    Client getClientByPhoneNumber(String phoneNumber);
    Client getClientByOrderID(long id);
    void updateOrders(long id,List<Order> orders);

}
