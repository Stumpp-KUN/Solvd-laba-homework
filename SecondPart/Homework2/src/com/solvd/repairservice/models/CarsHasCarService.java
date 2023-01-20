package solvd.repairservice.models;

import lombok.Data;

@Data
public class CarsHasCarService {
    private long id;
    private int price;
    private CarService carService;
    private Car car;
}
