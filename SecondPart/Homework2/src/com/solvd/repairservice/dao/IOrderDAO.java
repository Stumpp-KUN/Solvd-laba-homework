package solvd.repairservice.dao;

import solvd.repairservice.models.Order;

import java.util.List;

public interface IOrderDAO extends IBaseDAO<Order>{
    List<Order> getAllOrders();
}
