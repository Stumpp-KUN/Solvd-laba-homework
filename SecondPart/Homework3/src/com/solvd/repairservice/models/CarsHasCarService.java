package solvd.repairservice.models;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "CarsHasCarService")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsHasCarService {
    @XmlAttribute
    private long id;
    @XmlElement
    private int price;
    @XmlElement
    private CarService carService;
    @XmlElement
    private Car car;
}
