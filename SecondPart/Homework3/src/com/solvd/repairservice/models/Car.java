package solvd.repairservice.models;

import lombok.Data;
import solvd.repairservice.models.person.Courier;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "modelName")
    private String modelName;
    @XmlElement(name = "type")
    private String type;
    @XmlElement(name = "courier")
    private Courier courier;

    public Car(String modelName, String type, Courier courier) {
        this.modelName = modelName;
        this.type = type;
        this.courier = courier;
    }

    public Car(long id, String modelName, String type, Courier courier) {
        this.id = id;
        this.modelName = modelName;
        this.type = type;
        this.courier = courier;
    }

    public Car(long id, String modelName, String type) {
        this.id = id;
        this.modelName = modelName;
        this.type = type;
    }

    public Car(String modelName, String type) {
        this.modelName = modelName;
        this.type = type;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", type='" + type + '\'' +
                ", courierID=" + courier.getId() +
                '}';
    }
}
