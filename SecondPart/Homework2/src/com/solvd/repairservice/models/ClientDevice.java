package solvd.repairservice.models;

import lombok.Data;
import lombok.Getter;
import solvd.repairservice.models.person.Client;

@Data
public class ClientDevice {
    private long id;
    private String description;
    private Client client;
    private Order order;
}
