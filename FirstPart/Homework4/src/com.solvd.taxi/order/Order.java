package order;

import carDetails.Engine;
import carDetails.Wheel;
import person.Customer;
import person.Сourier;
import vehicles.*;

public class Order{
    private Package parcel;
    private Vehicle vehicle;
    private int cost;
    Сourier person = new Сourier();
    private final Engine engine = new Engine("RT");
    private final Wheel wheel = new Wheel(1, "Spring");

    static {
        System.out.println("Order request successfuly came");
    }

    public final boolean makeAnOrder(OrderCenter orderCenter){

        if (orderCenter.getWeigth() <= 0) return false;

        if (orderCenter.getWeigth() < 10) {
            parcel = new LightWeights();
            vehicle = new Bicycle("Bib", true);
            System.out.println("Information about Bicycle: maxSpeed: " + vehicle.getMaxSpeed() + ", maxWeigth= " + vehicle.getMaxWeight() + ", model= " + vehicle.getModel());
            cost=checkCost(parcel,vehicle,orderCenter);
            System.out.println("Final cost: " + cost);
            person.Deliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else if (orderCenter.getWeigth() >= 10 && orderCenter.getWeigth() < 15) {
            parcel = new MediumWeights();
            vehicle = new Car(engine, wheel, "Kol");
            System.out.println("Information about Car: maxSpeed: " + vehicle.getMaxSpeed() + ", maxWeigth= " + vehicle.getMaxWeight() + ", model= " + vehicle.getModel());
            cost=checkCost(parcel,vehicle,orderCenter);
            System.out.println("Final cost: " + cost);
            person.Deliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else if (orderCenter.getWeigth() >= 15 && orderCenter.getWeigth() < 50) {
            parcel = new MediumWeights();
            vehicle = new Truck("Tok", engine);
            System.out.println("Information about Truck: maxSpeed: " + vehicle.getMaxSpeed() + ", maxWeigth= " + vehicle.getMaxWeight() + ", model= " + vehicle.getModel());
            cost=checkCost(parcel,vehicle,orderCenter);
            System.out.println("Final cost: " + cost);
            person.Deliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else if (orderCenter.getWeigth() >= 20 && orderCenter.getWeigth() < 1000) {
            parcel = new HardWeights();
            vehicle = new Plane("Boing");
            System.out.println("Information about Plane: maxSpeed: " + vehicle.getMaxSpeed() + ", maxWeigth= " + vehicle.getMaxWeight() + ", model= " + vehicle.getModel());
            cost=checkCost(parcel,vehicle,orderCenter);
            System.out.println("Final cost: " + cost);
            person.Deliver(parcel, vehicle, cost, orderCenter.getCustomer());
        } else System.out.println("error, invalid weight");
        return true;
    }

    private int checkSpeed(OrderCenter orderCenter,int cost){
        switch (orderCenter.getCustomer().getSpeedOfDelivery()){
            case FAST -> {
                cost*=3;
                return cost;
            }
            case DEFAULT -> {
                cost*=1.2;
                return cost;
            }
            case SLOW -> {
                cost*=0.7;
                return cost;
            }
            default -> System.out.println("Error");
        }
        return 0;
    }

    private int checkCost(Package parcel, Vehicle vehicle,OrderCenter orderCenter){
        int cost=0;
        cost += parcel.getCost();
        cost += vehicle.deliveryPay();
        checkSpeed(orderCenter,cost);
        return cost;
    }
}
