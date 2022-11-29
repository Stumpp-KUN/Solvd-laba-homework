package order;

import carDetails.Engine;
import carDetails.Wheel;
import person.Customer;
import vehicles.*;

public class PriceOrder {
    private Package parcel;
    private Vehicle vehicle;
    private int cost;
    private Engine engine = new Engine("RT");
    private Wheel wheel = new Wheel(1, "Spring");

    public int checkPrice(OrderCenter orderCenter) {
        int weigth = orderCenter.getWeigth();
        if (weigth > 0&&weigth < 10) {
            parcel = new LightWeights();
            vehicle = new Bicycle("Bib", true);
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else if (weigth >= 10 && weigth < 15) {
            parcel = new MediumWeights();
            vehicle = new Car(engine, wheel, "Kol");
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else if (weigth >= 15 && weigth < 20) {
            parcel = new MediumWeights();
            vehicle = new Truck("Tok", engine);
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else if (weigth >= 20 &&weigth<1000 ) {
            parcel = new HardWeights();
            vehicle = new Plane("Boing");
            cost += parcel.getCost();
            cost += vehicle.deliveryPay();
            return cost;
        } else System.out.println("invalid weight");

        return 0;
    }

    public int checkPrice(int weigth){
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
