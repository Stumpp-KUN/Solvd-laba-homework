package solvd.repairservice.models;

import lombok.Data;
import lombok.Getter;
import solvd.repairservice.models.person.Client;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "clientDevice")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientDevice {
    @XmlAttribute
    private long id;
    @XmlElement
    private String description;
    @XmlElement
    private Client client;
    @XmlElement
    private Order order;
}
