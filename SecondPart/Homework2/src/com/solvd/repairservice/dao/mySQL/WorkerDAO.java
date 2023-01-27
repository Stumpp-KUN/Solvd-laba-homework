package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.IWorkerDAO;
import solvd.repairservice.models.Car;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.WorkerLicence;
import solvd.repairservice.models.person.Person;
import solvd.repairservice.models.person.Worker;
import solvd.repairservice.services.OrderService;
import solvd.repairservice.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAO extends MySQLDAO implements IWorkerDAO {
    private ResultSet rs;
    private Connection connection;
    private final static String GETWORKERID="SELECT * FROM `worker` WHERE id=?";
    private final static String GETWORKERNAME="SELECT * FROM `worker` WHERE name=?";
    private final static String CREATEWORKER="INSERT INTO `worker`(name,surname,WorkerLicence_id,orders) VALUES (?,?,?,?)";
    private final static String DROPWORKER="DELETE FROM `worker` WHERE id=?";
    private final static String GETALLWORKERS="SELECT * FROM `worker`";

    {
        MySQLConnection mySQLConnection=new MySQLConnection();
        connection=mySQLConnection.getConnection();
    }

    @Override
    public Worker createEntity(Worker entity) {
        try (PreparedStatement ps=connection.prepareStatement(CREATEWORKER)){
            String j1=null;
            if(entity.getOrders()!=null) {
                j1 = parseString(entity.getOrders());
            }
            ps.setString(1,entity.getName());
            ps.setString(2, entity.getSurname());
            ps.setLong(3,entity.getWorkerLicence().getId());
            ps.setString(4,j1);
            ps.executeUpdate();
            return entity;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private String parseString(List<Order> cars){
        String k="";
        for(int i=0;i<cars.size();i++)
            k+=cars.get(i)+",";
        return k;
    }

    @Override
    public Worker getEntityById(long id) {
        ResultSet rs=null;
        try (PreparedStatement ps=connection.prepareStatement(GETWORKERID)){
            Worker worker=new Worker();
            ps.setLong(1,id);
            rs=ps.executeQuery();
            String j="";
            while (rs.next()){
                worker.setId(rs.getLong("id"));
                worker.setName(rs.getString("name"));
                worker.setSurname(rs.getString("surname"));
                WorkerLicence workerLicence=new WorkerLicence();
                WorkerLicenceDAO workerLicenceDAO=new WorkerLicenceDAO();
                workerLicence=workerLicenceDAO.getEntityById(rs.getLong("WorkerLicence_id"));
                worker.setWorkerLicence(workerLicence);
                j=rs.getString("orders");
                if(parsePoints(j)!=null)
                    worker.setOrders(parsePoints(j));
            }
            return worker;
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

    private List<Order> parsePoints(String x) throws SQLException {
        if(x==null) return null;
        OrderService orderService=new OrderService();
        List<Order> orders=new ArrayList<>();
        String[] words = x.split(",");
        for (String word : words) {
            orders.add(orderService.getOrderById(Long.getLong(word)));
        }
        return orders;
    }

    @Override
    public boolean updateEntity(Worker entity) {
        return false;
    }

    @Override
    public void removeEntity(long id) {
        try (PreparedStatement ps=connection.prepareStatement(DROPWORKER)){
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Worker> getAllWorkers() {
        try (PreparedStatement ps=connection.prepareStatement(GETALLWORKERS)){
            rs=ps.executeQuery();
            Worker worker=new Worker();
            List<Worker> workers=new ArrayList<>();
            String j="";
            while (rs.next()){
                worker.setId(rs.getLong("id"));
                worker.setName(rs.getString("name"));
                worker.setSurname(rs.getString("surname"));
                WorkerLicence workerLicence=new WorkerLicence();
                WorkerLicenceDAO workerLicenceDAO=new WorkerLicenceDAO();
                workerLicence=workerLicenceDAO.getEntityById(rs.getLong("WorkerLicence_id"));
                worker.setWorkerLicence(workerLicence);
                j=rs.getString("orders");
                if(parsePoints(j)!=null)
                    worker.setOrders(parsePoints(j));
                workers.add(worker);
            }
            rs.close();
            return workers;
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
    public Worker getWorkerByName(String name) {
        try (PreparedStatement ps=connection.prepareStatement(GETWORKERNAME)){
            Worker worker=new Worker();
            ps.setString(1,name);
            rs=ps.executeQuery();
            while (rs.next()) {
                worker.setId(rs.getLong("id"));
                worker.setName(rs.getString("name"));
                worker.setSurname(rs.getString("surname"));
                WorkerLicence workerLicence = new WorkerLicence();
                WorkerLicenceDAO workerLicenceDAO = new WorkerLicenceDAO();
                workerLicence = workerLicenceDAO.getEntityById(rs.getLong("WorkerLicence_id"));
                worker.setWorkerLicence(workerLicence);
            }
            rs.close();
            return worker;
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
    public Worker getWorkerByOrderID(long id) {
        return null;
    }

    @Override
    public void updateOrders(long id, List<Order> orders) {
        try(PreparedStatement ps=connection.prepareStatement("UPDATE `worker` SET orders=? where id=?")){

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}