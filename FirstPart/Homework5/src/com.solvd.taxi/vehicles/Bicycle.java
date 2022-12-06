package vehicles;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
@NoArgsConstructor
public class Bicycle extends Vehicle{
    private boolean bucket;
    private final static Logger logger= LogManager.getRootLogger();

    public Bicycle( String model, boolean bucket) {
        setCost(100);
        setMaxSpeed(20);
        setMaxWeight(9);
        setModel(model);
        this.bucket = bucket;
    }

    @Override
    public boolean startDrive() {
        logger.info("Moving on bicycle");
        return true;
    }

    @Override
    public String getName() {
        return "Bicycle";
    }

    @Override
    public int deliveryPay(){
        return getCost();
    }
}
