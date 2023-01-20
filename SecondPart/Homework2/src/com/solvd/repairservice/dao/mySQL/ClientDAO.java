package solvd.repairservice.dao.mySQL;

import lombok.SneakyThrows;
import solvd.repairservice.dao.IClientDAO;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Person;
import solvd.repairservice.services.OrderService;
import solvd.repairservice.utils.MySQLConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;

public class ClientDAO extends MySQLDAO implements IClientDAO {
    private ResultSet rs;
    private Connection connection;
    private final static String GETCLIENT="SELECT * FROM clientss WHERE id=?";
    private final static String DROPCLIENT="DELETE FROM `clientss` WHERE id=?";
    private final static String CREATECLIENT="INSERT INTO `clientss` (name,surname,phoneNumber,orders) VALUES (?,?,?,?)";
    private final static String GETALLCLIENTS="SELECT * FROM `clientss`";
    private final static String GETCLIENTBYNUMBER="SELECT * FROM `clientss` WHERE phoneNumber=?";

    {
        MySQLConnection mySQLConnection=new MySQLConnection();
        connection=mySQLConnection.getConnection();
    }

    @Override
    public Client createEntity(Client entity) {
        try (PreparedStatement ps=connection.prepareStatement(CREATECLIENT)){
            ps.setString(1,entity.getName());
            ps.setString(2, entity.getSurname());
            ps.setString(3,entity.getPhoneNumber());
            ps.setString(4,parseString(entity.getOrder()));
            ps.executeUpdate();
            ps.close();
            return entity;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getEntityById(long id) throws SQLException {
             try (PreparedStatement ps = connection.prepareStatement(GETCLIENT)) {
                 ps.setLong(1, id);
                 rs = ps.executeQuery();
                 while (rs.next()) {
//                     System.out.println(rs.getString("name"));
//                     client.setName(rs.getString("name"));
//                     System.out.println(client.getName());
//                     System.out.println(rs.getString("surname"));
//                     client.setSurname(rs.getString("surname"));
//                     client.setPhoneNumber(rs.getString("phoneNumber"));
//                     client.setId(rs.getInt("id"));
//                     client.setOrder(parsePoints(rs.getString("orders")));
                     Client client=new Client(rs.getLong("id"),rs.getString("name"),rs.getString("surname"),rs.getString("phoneNumber"));
                     System.out.println(rs.getLong("id"));
                     System.out.println(client.toString());
                     return client;
                 }
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             } finally {
                 rs.close();
                 return null;
             }
    }

    private String parseString(List<Order> cars){
        String k="";
        for(int i=0;i<cars.size();i++)
            k+=cars.get(i)+",";
        return k;
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
    public boolean updateEntity(Client entity) {
         removeEntity(entity.getId());
         createEntity(entity);
        return true;
    }


    @Override
    public void removeEntity(long id) {
        try (PreparedStatement ps=connection.prepareStatement(DROPCLIENT)){
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Client> getAllClients() {
         try(PreparedStatement ps=connection.prepareStatement(GETALLCLIENTS)) {
             rs=ps.executeQuery();
             Client client=new Client();
             List<Client> clients=new ArrayList<>();
             while (rs.next()) {
                 client.setName(rs.getString("name"));
                 client.setSurname(rs.getString("surname"));
                 client.setPhoneNumber(rs.getString("phoneNumber"));
                 client.setId(rs.getInt("id"));
                 if(rs.getString("orders")==null)
                 client.setOrder(parsePoints(rs.getString("orders")));
                 clients.add(client);
             }
             return clients;
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
    public Client getClientByPhoneNumber(String phoneNumber) {
        try (PreparedStatement ps = connection.prepareStatement(GETCLIENTBYNUMBER)) {
            Client client = new Client();
            ps.setString(1, phoneNumber);
            rs = ps.executeQuery();
            while (rs.next()) {
                client.setName(rs.getString("name"));
                client.setSurname(rs.getString("surname"));
                client.setPhoneNumber(rs.getString("phoneNumber"));
                client.setId(rs.getInt("id"));
                client.setOrder(parsePoints(rs.getString("orders")));
                client.getName();
            }
            rs.close();
            return client;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public Client getClientByOrderID(long id) {
        return null;
    }

    @Override
    public void updateOrders(long id, List<Order> orders) {
        try (PreparedStatement ps=connection.prepareStatement("UPDATE `clientss` SET orders=? WHERE id=?")){
            String k="";
            for(int i=0;i<orders.size();i++)
                k+=orders.get(i)+",";
            ps.setString(1,k);
            ps.setLong(2,id);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
