package vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Plane extends Vehicle{

    public Plane(String model) {
        setCost(1000);
        setModel(model);
        setMaxSpeed(300);
        setMaxWeight(1000);
    }

    @Override
    public void startDrive() {
        System.out.println("Flying");
    }

    @Override
    public int deliveryPay(){
        return getCost();
    }

}
