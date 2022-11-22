package order;

import person.Customer;
import vehicles.*;

public class PriceOrder {
    private int weigth;
    private Package parcel;
    private Vehicle vehicle;
    private int cost;

    public int checkPrice(OrderCenter orderCenter) {
        int weigth = orderCenter.getWeigth();
        int cost = 0;
        if (weigth < 10) {
            parcel = new LightWeights();
            vehicle = new Bicycle();
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else if (weigth >= 10 && weigth < 15) {
            parcel = new MediumWeights();
            vehicle = new Car();
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else if (weigth >= 15 && weigth < 20) {
            parcel = new MediumWeights();
            vehicle = new Truck();
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else if (weigth < 1000 && weigth >= 20) {
            parcel = new HardWeights();
            vehicle = new Plane();
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else System.out.println("invalid weight");

        return 0;
    }
}
