package solvd.repairservice.services;

import solvd.repairservice.dao.ICourierDAO;
import solvd.repairservice.dao.mySQL.CourierDAO;
import solvd.repairservice.models.Car;
import solvd.repairservice.models.person.Courier;

import java.sql.SQLException;
import java.util.List;

public class CourierService {
    private ICourierDAO cDAO=new CourierDAO();

    public Courier getCourierById(long id) throws SQLException {
        Courier c= (Courier) cDAO.getEntityById(id);
        return c;
    }

    public Courier createCourier(String name,String surname,List<Car> cars){
        return (Courier) cDAO.createEntity(new Courier(name,surname,cars));
    }

    public Courier createCourier(String name,String surname){
        return (Courier) cDAO.createEntity(new Courier(name,surname));
    }

    public boolean updateCourier(long id,List<Car> cars){
        cDAO.updateCars(id,cars);
        return true;
    }

    public void deleteCourierById(long id){
        cDAO.removeEntity(id);
    }

    public Courier getCourierByName(String name){
        Courier c= cDAO.getCourierByName(name);
        return c;
    }

    public List<Courier> getAllCourier(){
        return cDAO.getAllCouriers();
    }
}
