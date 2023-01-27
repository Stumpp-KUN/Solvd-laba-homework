package solvd.repairservice.dao.mySQL;

import solvd.repairservice.dao.ICarServiceDAO;
import solvd.repairservice.models.CarService;
import solvd.repairservice.utils.MySQLConnection;
import solvd.repairservice.utils.PoolConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarServiceDAO extends MySQLDAO implements ICarServiceDAO {
    private ResultSet rs;
    private Connection connection;
    private final static String GETCARSERVICEBYID="SELECT * FROM `Clients` WHERE id=?";
    private final static String DROPCARSERVICE="DELETE FROM `Clients` WHERE id=?";
    private final static String CREATECARSERVICE="INSERT INTO `Clients` (name,surname,phoneNumber) VALUES (?,?,?)";
    private final static String GETALLCARSERVICES="SELECT * FROM `Clients`";

    {
        PoolConnection poolConnection=new PoolConnection();
        try {
            connection=poolConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CarService createEntity(CarService entity) {
        try (PreparedStatement ps=connection.prepareStatement(CREATECARSERVICE)){
            ps.setString(1,entity.getName());
            ps.setInt(2,entity.getAge());
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
    public CarService getEntityById(long id) {
        try(PreparedStatement ps=connection.prepareStatement(GETCARSERVICEBYID)) {
            CarService carService=new CarService();
            ps.setLong(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                carService.setId(rs.getLong("id"));
                carService.setName(rs.getString("name"));
                carService.setAge(rs.getInt("age"));
            }
            rs.close();
            return carService;
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
    public boolean updateEntity(CarService entity) {
        removeEntity(entity.getId());
        createEntity(entity);
        return true;
    }

    @Override
    public void removeEntity(long id) {
        try(PreparedStatement ps=connection.prepareStatement(DROPCARSERVICE)){
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
    public List<CarService> getAllCarService() {
        try (PreparedStatement ps=connection.prepareStatement(GETALLCARSERVICES)){
            rs=ps.executeQuery();
            CarService carService=new CarService();
            List<CarService> carServices=new ArrayList<>();
            while(rs.next()){
                carService.setId(rs.getLong("id"));
                carService.setName(rs.getString("name"));
                carService.setAge(rs.getInt("age"));
                carServices.add(carService);
            }
            rs.close();
            return carServices;
        }
        catch (SQLException throwables) {
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
