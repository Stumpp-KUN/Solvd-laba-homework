package solvd.repairservice.dao.mySQL;

import lombok.SneakyThrows;
import solvd.repairservice.dao.IClientDAO;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Person;
import solvd.repairservice.services.OrderService;
import solvd.repairservice.utils.MySQLConnection;
import solvd.repairservice.utils.PoolConnection;

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
    private final static String CREATECLIENT="INSERT INTO `clientss` (name,surname,phoneNumber) VALUES (?,?,?)";
    private final static String GETALLCLIENTS="SELECT * FROM `clientss`";
    private final static String GETCLIENTBYNUMBER="SELECT * FROM `clientss` WHERE phoneNumber=?";

    {
        PoolConnection poolConnection=new PoolConnection();
        try {
            connection=poolConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client createEntity(Client entity) {
        try (PreparedStatement ps=connection.prepareStatement(CREATECLIENT)){
            ps.setString(1,entity.getName());
            ps.setString(2, entity.getSurname());
            ps.setString(3,entity.getPhoneNumber());
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
        Client client=new Client();
        try (PreparedStatement ps = connection.prepareStatement(GETCLIENT)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                client=new Client(rs.getLong("id"),rs.getString("name"),rs.getString("surname"),rs.getString("phoneNumber"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            rs.close();
        }
        return client;
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
             List<Client> clients=new ArrayList<>();
             while (rs.next()) {
                 Client client=new Client();
                 client.setName(rs.getString("name"));
                 client.setSurname(rs.getString("surname"));
                 client.setPhoneNumber(rs.getString("phoneNumber"));
                 client.setId(rs.getInt("id"));
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
    public long getClientIsExist(long id) {
        try (PreparedStatement ps=connection.prepareStatement(GETCLIENT)){
            ps.setLong(1,id);
            rs=ps.executeQuery();
            Client client=new Client();
            while (rs.next()){
                client.setId(rs.getLong("id"));
            }
            return client.getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateOrders(long id, Order orders) {

    }

}
