package solvd.repairservice.models;

import lombok.Data;
import lombok.Setter;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Worker;

@Data
public class Order {
    private long id;
    private String description;
    private Client client;
    private Worker worker;

    public Order(String description, Client client, Worker worker) {
        this.description = description;
        this.client = client;
        this.worker = worker;
    }

    public Order() {
    }
}
