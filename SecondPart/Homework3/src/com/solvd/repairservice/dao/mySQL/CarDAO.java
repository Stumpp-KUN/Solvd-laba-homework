package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.ICarDAO;
import solvd.repairservice.models.Car;
import solvd.repairservice.models.person.Courier;
import solvd.repairservice.services.CourierService;
import solvd.repairservice.utils.MySQLConnection;
import solvd.repairservice.utils.PoolConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class CarDAO extends MySQLDAO implements ICarDAO {
    private Connection connection;
    private ResultSet rs;
    private final static String GETCAR="SELECT * FROM `Cars` WHERE id=?";
    private final static String GETALLCARS="SELECT * FROM `Cars`";
    private final static String DELETECAR="DELETE FROM `Cars` WHERE id=?";
    private final static String GETCARBYTYPE="SELECT * FROM `Cars` WHERE type=?";
    private final static String FINDACOURIER="SELECT * FROM `couriers` WHERE cars=?";
    private final static String CREATECAR="INSERT INTO `Cars`(modelName,type,Couriers_id) VALUES (?,?,?)";
    private final static String UPDATECOURIER="UPDATE `cars` SET Couriers_id=? WHERE id=?";

    {
        PoolConnection poolConnection=new PoolConnection();
        try {
            connection=poolConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car createEntity(Car entity) {
        try (PreparedStatement ps=connection.prepareStatement(CREATECAR)){
            ps.setString(1,entity.getModelName());
            ps.setString(2,entity.getType());
            ps.setLong(3,entity.getCourier().getId());
            ps.executeUpdate();
            ps.close();
            return entity;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Car getEntityById(long id) {
        CourierDAO courierDAO=new CourierDAO();
        try(PreparedStatement ps=connection.prepareStatement(GETCAR)){
            Car car=new Car();
            Courier courier=new Courier();
            ps.setLong(1,id);
            rs= ps.executeQuery();
            while (rs.next()){
                car.setModelName(rs.getString("modelName"));
                car.setType(rs.getString("type"));
                car.setId(rs.getInt("id"));
                courier.setId(rs.getLong("Couriers_id"));
                car.setCourier(courierDAO.getEntityById(courier.getId()));
            }
            rs.close();
            return car;
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
    public boolean updateEntity(Car entity) {
        removeEntity(entity.getId());
        createEntity(entity);
        return true;
    }

    @Override
    public void removeEntity(long id) {
        try (PreparedStatement ps=connection.prepareStatement(DELETECAR)){
            ps.setLong(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Car> getAllCars() {
        try(PreparedStatement ps=connection.prepareStatement(GETALLCARS)) {
            CourierDAO courierDAO=new CourierDAO();
            Courier courier=new Courier();
            rs=ps.executeQuery();
            List<Car> cars=new ArrayList<>();
            while (rs.next()){
                Car car=new Car();
                car.setId(rs.getLong("id"));
                car.setModelName(rs.getString("modelName"));
                car.setType(rs.getString("type"));
                car.setCourier(courierDAO.getEntityById(rs.getLong("Couriers_id")));
                cars.add(car);
            }
            rs.close();
            return cars;
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
    public List<Car> getCarsByType(String type) {
        try(PreparedStatement ps=connection.prepareStatement(GETCARBYTYPE)) {
            CourierDAO courierDAO=new CourierDAO();
            Courier courier=new Courier();
            ps.setString(1,type);
            Car car=new Car();
            List<Car> cars=new ArrayList<>();
            rs=ps.executeQuery();
            while (rs.next()){
                car.setId(rs.getLong("id"));
                car.setType(rs.getString("type"));
                car.setModelName(rs.getString("modelName"));
                courier.setId(rs.getLong("Couriers_id"));
                car.setCourier(courierDAO.getEntityById(courier.getId()));
                cars.add(car);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCouriers_id(long id,Courier courier) {
        try (PreparedStatement ps=connection.prepareStatement(UPDATECOURIER)){
            ps.setLong(1,courier.getId());
            ps.setLong(2,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
