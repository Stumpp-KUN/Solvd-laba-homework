package solvd.repairservice.services;

import solvd.repairservice.dao.ICarDAO;
import solvd.repairservice.dao.ICourierDAO;
import solvd.repairservice.dao.mySQL.CarDAO;
import solvd.repairservice.dao.mySQL.CourierDAO;
import solvd.repairservice.models.Car;
import solvd.repairservice.models.person.Courier;

import java.sql.SQLException;
import java.util.List;

public class CarService {
    private ICarDAO cDAO=new CarDAO();
    private ICourierDAO courierDAO=new CourierDAO();

    public Car getCarByID(long id) throws SQLException {
        Car car=cDAO.getEntityById(id);
        return car;
    }

    public List<Car> getAllCars(){
        return cDAO.getAllCars();
    }

    public Car createCar(String modelName, String type){
        return cDAO.createEntity(new Car(modelName, type));
    }

    public Car createCar(String modelName, String type,Courier courier){
        return cDAO.createEntity(new Car(modelName, type,courier));
    }

    public void deleteCar(long id){
        cDAO.removeEntity(id);
    }

    public boolean updateCar(long id, Courier courier){
        CarDAO carDAO=new CarDAO();
        carDAO.updateCouriers_id(id,courier);
        return true;
    }
}
