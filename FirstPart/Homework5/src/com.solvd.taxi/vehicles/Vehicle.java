package vehicles;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public abstract class Vehicle {
    private int maxSpeed;
    private int maxWeight;
    private int cost;
    private String model;

    public abstract boolean startDrive();
    
    public abstract String getName();

    public int deliveryPay(){
        return cost;
    }

}
