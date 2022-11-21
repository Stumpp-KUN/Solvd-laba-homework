package Vehicles;

import CarDetails.Engine;
import CarDetails.Wheel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car extends Vehicle{
    private Engine engine;
    private Wheel wheel;

    public Car(Engine engine, Wheel wheel, String model) {
        this.engine = engine;
        this.wheel = wheel;
        setModel(model);
        setCost(200);
        setMaxSpeed(60);
        setMaxWeight(14);
    }

    @Override
    public void startDrive(){
        System.out.println("Car's engine has been started");
    }

    @Override
    public int deliveryPay(){
        return getCost();
    }
}
