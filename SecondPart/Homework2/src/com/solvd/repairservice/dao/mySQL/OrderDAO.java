package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.IOrderDAO;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Worker;
import solvd.repairservice.services.ClientService;
import solvd.repairservice.services.WorkerService;
import solvd.repairservice.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends MySQLDAO implements IOrderDAO {
    private ResultSet rs;
    private Connection connection;
    private final static String GETORDERBYID="SELECT * FROM `orders` WHERE id=?";
    private final static String CREATEORDER="INSERT INTO `orders`(description,client_id,Worker_id) VALUES (?,?,?)";
    private final static String DROPORDER="DELETE FROM `orders` WHERE id=?";
    private final static String GETALLORDERS="SELECT * FROM `orders`";

    {
        MySQLConnection mySQLConnection=new MySQLConnection();
        connection=mySQLConnection.getConnection();
    }

    @Override
    public Order createEntity(Order entity)  {
        try (PreparedStatement ps=connection.prepareStatement(CREATEORDER)){
            ps.setString(1,entity.getDescription());
            ps.setLong(2, entity.getClient().getId());
            ps.setLong(3,entity.getWorker().getId());
            ps.executeUpdate();
            return entity;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getEntityById(long id) {
        try (PreparedStatement ps=connection.prepareStatement(GETORDERBYID)){
            Order order=new Order();
            ps.setLong(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                order.setId(rs.getLong("id"));
                order.setDescription(rs.getString("description"));
                ClientService clientService=new ClientService();
                Client client=new Client();
                client=clientService.getClientById(rs.getLong("client_id"));
                order.setClient(client);
                WorkerService workerService=new WorkerService();
                Worker worker=new Worker();
                worker=workerService.getWorkerById(rs.getLong("Worker_id"));
                order.setWorker(worker);
            }
            rs.close();
            return order;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean updateEntity(Order entity) {
        return false;
    }

    @Override
    public void removeEntity(long id) {
        try (PreparedStatement ps=connection.prepareStatement(DROPORDER)){
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        try (PreparedStatement ps=connection.prepareStatement(GETALLORDERS)){
            rs=ps.executeQuery();
            Order order=new Order();
            List<Order> orders=new ArrayList<>();
            while(rs.next()){
                order.setId(rs.getLong("id"));
                order.setDescription(rs.getString("description"));
                ClientService clientService=new ClientService();
                Client client=new Client();
                client=clientService.getClientById(rs.getLong("client_id"));
                order.setClient(client);
                WorkerService workerService=new WorkerService();
                Worker worker=new Worker();
                worker=workerService.getWorkerById(rs.getLong("Worker_id"));
                order.setWorker(worker);
                orders.add(order);
            }
            rs.close();
            return orders;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
