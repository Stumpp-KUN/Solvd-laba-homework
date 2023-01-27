package solvd.repairservice.models;

import lombok.Data;
import lombok.Setter;
import solvd.repairservice.models.person.Client;
import solvd.repairservice.models.person.Worker;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlAttribute
    private long id;
    @XmlElement
    private String description;
    @XmlElement
    private Client client;
    @XmlElement
    private Worker worker;

    public Order(String description, Client client, Worker worker) {
        this.description = description;
        this.client = client;
        this.worker = worker;
    }

    public Order(long id) {
        this.id = id;
    }

    public Order() {
    }
}
