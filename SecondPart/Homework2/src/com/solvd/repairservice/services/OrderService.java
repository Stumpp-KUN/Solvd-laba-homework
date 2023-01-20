package solvd.repairservice.services;

import solvd.repairservice.dao.IClientDAO;
import solvd.repairservice.dao.IOrderDAO;
import solvd.repairservice.dao.IWorkerDAO;
import solvd.repairservice.dao.mySQL.ClientDAO;
import solvd.repairservice.dao.mySQL.OrderDAO;
import solvd.repairservice.dao.mySQL.WorkerDAO;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Worker;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private IClientDAO clientDAO=new ClientDAO();
    private IWorkerDAO workerDAO=new WorkerDAO();
    private IOrderDAO orderDAO=new OrderDAO();

    public Order getOrderById(long id) throws SQLException {
        Order order=orderDAO.getEntityById(id);
        order.setWorker(workerDAO.getWorkerByOrderID(id));
        order.setClient(clientDAO.getClientByOrderID(id));
        return order;
    }

    public List<Order> getAllOrders(){
        return orderDAO.getAllOrders();
    }

    public Order createOrder(String description, Client client, Worker worker){
        return orderDAO.createEntity(new Order(description,client,worker));
    }

    public void deleteOrder(long id){
        orderDAO.removeEntity(id);
    }
}
