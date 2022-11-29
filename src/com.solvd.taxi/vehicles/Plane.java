package vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Plane extends Vehicle implements IFly{

    public Plane(String model) {
        setCost(1000);
        setModel(model);
        setMaxSpeed(300);
        setMaxWeight(1000);
    }

    @Override
    public boolean startDrive() {
        System.out.println("Driving");
        return true;
    }

    @Override
    public int deliveryPay(){
        return getCost();
    }

    @Override
    public boolean fly() {
        System.out.println("Flying");
        return true;
    }
}
