package solvd.repairservice.dao;

import solvd.repairservice.models.Car;
import solvd.repairservice.models.person.Courier;

import java.util.List;

public interface ICarDAO extends IBaseDAO<Car>{
    List<Car> getAllCars();
    List<Car> getCarsByType(String type);
    boolean updateCouriers_id(long id,Courier courier);
}
