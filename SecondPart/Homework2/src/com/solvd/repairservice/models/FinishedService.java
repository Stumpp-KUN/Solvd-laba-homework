package solvd.repairservice.models;

import lombok.Data;
import solvd.repairservice.models.person.Courier;
import solvd.repairservice.models.person.Worker;

@Data
public class FinishedService {
    private long id;
    private int amount;
    private String description;
    private Worker worker;
    private Order order;
    private Service service;
    private Courier courier;

}
