package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.ICourierDAO;
import solvd.repairservice.models.Car;
import solvd.repairservice.models.person.Courier;
import solvd.repairservice.models.person.Person;
import solvd.repairservice.utils.MySQLConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CourierDAO extends MySQLDAO implements ICourierDAO {
    private ResultSet rs;
    private Connection connection;
    private final static String GETCOURIER="SELECT * FROM `Couriers` WHERE id=?";
    private final static String CREATECOURIER="INSERT INTO `couriers`(name,surname) VALUES (?,?)";
    private final static String GETCAR="SELECT * FROM 'cars' WHERE Couriers_id=?";
    private final static String DROPCOURIER="DELETE FROM `Couriers` WHERE id=?";
    private final static String GETALLCOURIERS="SELECT * FROM `couriers`";
    private final static String GETCOURIERBYNAME="SELECT * FROM `Couriers` WHERE name=?";
    private final static String UPDATE="UPDATE `couriers` SET cars=? WHERE id=?";

    {
        MySQLConnection mySQLConnection=new MySQLConnection();
        connection=mySQLConnection.getConnection();
    }

    @Override
    public Courier createEntity(Courier entity) {
        try(PreparedStatement ps=connection.prepareStatement(CREATECOURIER)) {
            ps.setString(1,entity.getName());
            ps.setString(2,entity.getSurname());
            ps.executeUpdate();
            return entity;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public Courier getEntityById(long id) {
        ResultSet rs=null;
        try(PreparedStatement ps=connection.prepareStatement(GETCOURIER)){
            Courier courier=new Courier();
            ps.setLong(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                courier.setId(rs.getLong("id"));
                courier.setName(rs.getString("name"));
                courier.setSurname(rs.getString("surname"));
            }
            return courier;
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
    public boolean updateEntity(Courier entity) {
        removeEntity(entity.getId());
        createEntity(entity);
        return true;
    }

    @Override
    public void removeEntity(long id) {
        try (PreparedStatement ps=connection.prepareStatement(DROPCOURIER)){
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Courier> getAllCouriers() {
        try (PreparedStatement ps=connection.prepareStatement(GETALLCOURIERS)){
            rs=ps.executeQuery();
            Courier courier=new Courier();
            List<Courier> couriers=new ArrayList<>();
            while(rs.next()){
                courier.setId(rs.getLong("id"));
                courier.setName(rs.getString("name"));
                courier.setSurname(rs.getString("surname"));
                System.out.println(courier.getName());
                couriers.add(courier);
            }
            System.out.println(couriers.get(0).getId());
            return couriers;
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
    public Courier getCourierByName(String name) {
        try (PreparedStatement ps=connection.prepareStatement(GETCOURIERBYNAME)){
            Courier courier=new Courier();
            ps.setString(1,name);
            rs=ps.executeQuery();
            while(rs.next()){
                courier.setId(rs.getLong("id"));
                courier.setName(rs.getString("name"));
                courier.setSurname(rs.getString("surname"));
            }
            rs.close();
            return courier;
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
    public Courier getCourierByCarID(long id) {
        return null;
    }

    @Override
    public boolean updateCars(long id,List<Car> cars) {
        return false;
    }
}
