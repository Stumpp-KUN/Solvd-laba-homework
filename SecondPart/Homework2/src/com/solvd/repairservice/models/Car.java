package solvd.repairservice.models;

import lombok.Data;
import solvd.repairservice.models.person.Courier;

@Data
public class Car {
    private long id;
    private String modelName;
    private String type;
    private Courier courier;

    public Car(String modelName, String type, Courier courier) {
        this.modelName = modelName;
        this.type = type;
        this.courier = courier;
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
