package solvd.repairservice.dao;

import solvd.repairservice.models.CarService;

import java.util.List;

public interface ICarServiceDAO extends IBaseDAO<CarService> {
    List<CarService> getAllCarService();
}
