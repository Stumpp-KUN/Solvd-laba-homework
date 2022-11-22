package vehicles;

import carDetails.Engine;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Truck extends Vehicle{
    private String model;
    private Engine engine;

    public Truck(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        setCost(400);
        setMaxSpeed(45);
        setMaxWeight(49);
    }

    @Override
    public void startDrive(){
        System.out.println("Truck's engine has been started");
    }

    @Override
    public int deliveryPay(){
        return getCost();
    }
}
