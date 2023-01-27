package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.IServiceDAO;
import solvd.repairservice.models.Service;
import solvd.repairservice.utils.MySQLConnection;
import solvd.repairservice.utils.PoolConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ServiceDAO extends MySQLDAO implements IServiceDAO {
    private ResultSet rs;
    private Connection connection;
    private final static String CREATESERVICE="INSERT INTO `Services`(description) VALUES (?)";
    private final static String GETSERVICEBYID="SELECT * FROM `Services` WHERE id=?";
    private final static String DROPSERVICE="DELETE FROM `Services` WHERE id=?";
    private final static String GETSERVICES="SELECT * FROM `Services`";

    {
        PoolConnection poolConnection=new PoolConnection();
        try {
            connection=poolConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Service createEntity(Service entity) {
        try (PreparedStatement ps=connection.prepareStatement(CREATESERVICE)){
            ps.setString(1,entity.getDescription());
            rs=ps.executeQuery();
            rs.close();
            return entity;
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
    public Service getEntityById(long id) {
        try(PreparedStatement ps=connection.prepareStatement(GETSERVICEBYID)){
            Service service=new Service();
            ps.setLong(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                service.setDescription(rs.getString("description"));
            }
            rs.close();
            return service;
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
    public boolean updateEntity(Service entity) {
        removeEntity(entity.getId());
        createEntity(entity);
        return true;
    }

    @Override
    public void removeEntity(long id) {
        try(PreparedStatement ps=connection.prepareStatement(DROPSERVICE)) {
            ps.setLong(1,id);
            rs=ps.executeQuery();
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
    }

    @Override
    public List<Service> getAllService() {
        try (PreparedStatement ps=connection.prepareStatement(GETSERVICES)){
            rs=ps.executeQuery();
            Service service=new Service();
            List<Service> services=new ArrayList<>();
            while(rs.next()){
                service.setId(rs.getLong("id"));
                service.setDescription(rs.getString("description"));
                services.add(service);
            }
            rs.close();
            return services;
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
