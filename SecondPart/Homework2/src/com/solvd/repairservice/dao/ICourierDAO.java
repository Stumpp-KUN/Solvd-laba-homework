package solvd.repairservice.dao;

import solvd.repairservice.models.Car;
import solvd.repairservice.models.person.Courier;
import solvd.repairservice.models.person.Person;

import java.util.List;

public interface ICourierDAO extends IBaseDAO<Courier>{
    List<Courier> getAllCouriers();
    Courier getCourierByName(String name);
    Courier getCourierByCarID(long id);
    boolean updateCars(long id,List<Car> cars);
}
