package solvd.repairservice.models;

import lombok.Data;
import solvd.repairservice.models.person.Courier;
import solvd.repairservice.models.person.Worker;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "finishedService")
@XmlAccessorType(XmlAccessType.FIELD)
public class FinishedService {
    @XmlAttribute
    private long id;
    @XmlElement
    private int amount;
    @XmlElement
    private String description;
    @XmlElement
    private Worker worker;
    @XmlElement
    private Order order;
    @XmlElement
    private Service service;
    @XmlElement
    private Courier courier;

}
