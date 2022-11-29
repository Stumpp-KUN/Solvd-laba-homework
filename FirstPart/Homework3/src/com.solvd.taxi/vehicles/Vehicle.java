package vehicles;

import lombok.Data;

@Data
public abstract class Vehicle {
    private int maxSpeed;
    private int maxWeight;
    private int cost;
    private String model;


    public abstract boolean startDrive();

    public int deliveryPay(){
        return cost;
    }

}
