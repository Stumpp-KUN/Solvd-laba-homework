package order;

import carDetails.Engine;
import carDetails.Wheel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Customer;
import vehicles.*;

import java.util.function.Function;

public class PriceOrder {
    private Package parcel;
    private Vehicle vehicle;
    private final Engine engine = new Engine("RT");
    private final Wheel wheel = new Wheel(1, "Spring");
    private final static Logger logger= LogManager.getRootLogger();

    static {
        logger.info("PriceOrder request successfuly came");
    }

    public int checkPrice(OrderCenter orderCenter) {
        int weigth = orderCenter.getWeigth();
        if (weigth > 0&&weigth < 10) {
            parcel = new LightWeights();
            vehicle = new Bicycle("Bib", true);
            return checkCost(parcel,vehicle);
        } else if (weigth >= 10 && weigth < 15) {
            parcel = new MediumWeights();
            vehicle = new Car(engine, wheel, "Kol");
            return checkCost(parcel,vehicle);
        } else if (weigth >= 15 && weigth < 20) {
            parcel = new MediumWeights();
            vehicle = new Truck("Tok", engine);
            return checkCost(parcel,vehicle);
        } else if (weigth >= 20 &&weigth<1000 ) {
            parcel = new HardWeights();
            vehicle = new Plane("Boing");
            return checkCost(parcel,vehicle);
        } else logger.warn("invalid weight");

        return 0;
    }

    private int checkCost(Package parcel, Vehicle vehicle){
        int cost=0;
        Function<Integer,Integer> fun=a->parcel.getCost();
        Function<Integer,Integer> fun2=a->vehicle.deliveryPay();
        int k=fun.apply(cost);
        k+=fun2.apply(k);
        return k;
    }


}
