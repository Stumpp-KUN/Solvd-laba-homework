package vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
@NoArgsConstructor
public class Plane extends Vehicle implements IFly{

    public Plane(String model) {
        setCost(1000);
        setModel(model);
        setMaxSpeed(300);
        setMaxWeight(1000);
    }
    private final static Logger logger= LogManager.getRootLogger();
    @Override
    public boolean startDrive() {

        logger.info("Driving");
        return true;
    }

    @Override
    public String getName() {
        return "Plane";
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
