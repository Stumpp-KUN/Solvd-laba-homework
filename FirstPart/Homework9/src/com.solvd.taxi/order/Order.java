package order;

import carDetails.Engine;
import carDetails.Wheel;
import exception.NotEnoughMoneyException;

import lambda.Fun3;
import person.Customer;
import person.Сourier;
import vehicles.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;


public class Order{
    private Package parcel;
    private Vehicle vehicle;
    private int cost;
    Сourier person = new Сourier();
    private final Engine engine = new Engine("RT");
    private final Wheel wheel = new Wheel(1, "Spring");
    private final static Logger logger= LogManager.getRootLogger();

    static {
        logger.info("Order request successfuly came");
    }

    public final boolean makeAnOrder(OrderCenter orderCenter){

        if (orderCenter.getWeigth() <= 0) return false;

        if (orderCenter.getWeigth() < 10) {
            parcel = new LightWeights();
            vehicle = new Bicycle("Bib", true);
            getInfo(orderCenter,vehicle);
            cost=checkCost(parcel,vehicle,orderCenter);
            makeDeliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else if (orderCenter.getWeigth() >= 10 && orderCenter.getWeigth() < 15) {
            parcel = new MediumWeights();
            vehicle = new Car(engine, wheel, "Kol");
            getInfo(orderCenter,vehicle);
            cost=checkCost(parcel,vehicle,orderCenter);
            makeDeliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else if (orderCenter.getWeigth() >= 15 && orderCenter.getWeigth() < 50) {
            parcel = new MediumWeights();
            vehicle = new Truck("Tok", engine);
            getInfo(orderCenter,vehicle);
            cost=checkCost(parcel,vehicle,orderCenter);
            makeDeliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else if (orderCenter.getWeigth() >= 20 && orderCenter.getWeigth() < 1000) {
            parcel = new HardWeights();
            vehicle = new Plane("Boing");
            getInfo(orderCenter,vehicle);
            cost=checkCost(parcel,vehicle,orderCenter);
            makeDeliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else logger.warn("error, invalid weight");
        return true;
    }

    private boolean makeDeliver(Package parcel,Vehicle vehicle,int cost, Customer customer){
        try {
            person.Deliver(parcel, vehicle, cost, customer);
            return true;
        }
        catch (NotEnoughMoneyException e){
            logger.warn(e.getMessage());
            return false;
        }

    }

    private boolean getInfo(OrderCenter orderCenter,Vehicle vehicle){
        logger.info("Information about "+vehicle.getName()+": maxSpeed: " + vehicle.getMaxSpeed() + ", maxWeigth= " + vehicle.getMaxWeight() + ", model= " + vehicle.getModel());
        logger.info("Ur vehicle's avg speed: "+orderCenter.getCustomer().getSpeedOfDelivery().getSpeed().getAvgSpeed()+", but real speed of ur delivery's cars is: "+orderCenter.getCustomer().getSpeedOfDelivery().getSpeed().getRealSpeed());
        return true;
    }

    public int checkSpeed(OrderCenter orderCenter,int cost){
        Fun3 fun=new Fun3();
        switch (orderCenter.getCustomer().getSpeedOfDelivery()){
            case FAST -> {
                double t=fun.getMult(cost,SpeedOfDelivery.FAST.getMULT());
                return (int)t;
            }
            case DEFAULT -> {
                double t=fun.getMult(cost,SpeedOfDelivery.DEFAULT.getMULT());
                return (int)t;
            }
            case SLOW -> {
                double t=fun.getMult(cost,SpeedOfDelivery.SLOW.getMULT());
                return (int)t;
            }
            default -> logger.warn("Error");
        }
        return 0;
    }

    private int checkCost(Package parcel, Vehicle vehicle,OrderCenter orderCenter){
        int cost=0;
        Function<Integer,Integer> fun= a->parcel.getCost();
        Function<Integer,Integer> fun2=a->vehicle.deliveryPay();
        cost=fun.apply(cost);
        cost+=fun2.apply(cost);
        logger.info("Final cost: " + checkSpeed(orderCenter,cost));
        return checkSpeed(orderCenter,cost);
    }
}
