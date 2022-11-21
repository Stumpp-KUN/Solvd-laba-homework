package Vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bicycle extends Vehicle{
    private boolean bucket;

    public Bicycle( String model, boolean bucket) {
        setCost(100);
        setMaxSpeed(20);
        setMaxWeight(9);
        setModel(model);
        this.bucket = bucket;
    }

    @Override
    public void startDrive() {
        System.out.println("Moving on bicycle");
    }

    @Override
    public int deliveryPay(){
        return getCost();
    }
}
