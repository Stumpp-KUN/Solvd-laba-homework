package Vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Vehicle {
    private int maxSpeed;
    private int maxWeight;
    private int cost;
    private String model;


    public abstract void startDrive();

    public int deliveryPay(){
        return cost;
    }

}
