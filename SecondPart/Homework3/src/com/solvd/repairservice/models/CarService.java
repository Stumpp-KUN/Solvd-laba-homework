package solvd.repairservice.models;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "carService")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarService {
    @XmlAttribute
    private long id;
    @XmlElement
    private String name;
    @XmlElement
    private int age;
}
