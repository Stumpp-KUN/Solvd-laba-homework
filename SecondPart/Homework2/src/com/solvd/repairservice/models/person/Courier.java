package solvd.repairservice.models.person;

import lombok.Data;
import solvd.repairservice.models.Car;

import java.util.List;

@Data
public class Courier extends Person{
    private List<Car> cars;
    public Courier(long id,String name,String surname) {
        setId(id);
        setName(name);
        setSurname(surname);
    }

    public Courier(String name,String surname) {
        setName(name);
        setSurname(surname);
    }

    public Courier(String name, String surname,List<Car> cars) {
        setName(name);
        setSurname(surname);
        setCars(cars);
    }

    public Courier() {
    }
}
