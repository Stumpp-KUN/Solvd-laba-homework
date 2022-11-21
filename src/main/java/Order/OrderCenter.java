package Order;

import CarDetails.Engine;
import CarDetails.Wheel;
import Person.*;
import Vehicles.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCenter {
    private int weigth;
    private String streetOfPointA;
    private String streetOfPointB;
    private Package parcel;
    private Vehicle vehicle;
    private int cost;
    private Customer customer;
    Сourier person=new Сourier();
    private Engine engine=new Engine("RT");
    private Wheel wheel=new Wheel(1,"Spring");

    public OrderCenter(int weigth, String streetOfPointA, String streetOfPointB,Customer customer) {
        this.weigth = weigth;
        this.streetOfPointA = streetOfPointA;
        this.streetOfPointB = streetOfPointB;
        this.customer=customer;
    }


    public void makeAnOrder(OrderCenter orderCenter, Customer customer){
        if(orderCenter.getWeigth()<=0) return;

        if(orderCenter.getWeigth()<10){
            parcel= new LightWeights();
            vehicle=new Bicycle("Bib",true);
            System.out.println("Information about Bicycle: maxSpeed: "+vehicle.getMaxSpeed()+", maxWeigth= "+vehicle.getMaxWeight()+", model= "+ vehicle.getModel());
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            System.out.println("Final cost: "+cost);
            person.Deliver(parcel,vehicle,orderCenter,cost,customer);
        }
        else if (orderCenter.getWeigth()>=10&&orderCenter.getWeigth()<15){
            parcel=new MediumWeights();
            vehicle=new Car(engine,wheel,"Kol");
            System.out.println("Information about Car: maxSpeed: "+vehicle.getMaxSpeed()+", maxWeigth= "+vehicle.getMaxWeight()+", model= "+ vehicle.getModel());
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            System.out.println("Final cost: "+cost);
            person.Deliver(parcel,vehicle,orderCenter,cost,customer);
        }
        else if(orderCenter.getWeigth()>=15&&orderCenter.getWeigth()<50){
            parcel=new MediumWeights();
            vehicle=new Truck("Tok",engine);
            System.out.println("Information about Truck: maxSpeed: "+vehicle.getMaxSpeed()+", maxWeigth= "+vehicle.getMaxWeight()+", model= "+ vehicle.getModel());
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            System.out.println("Final cost: "+cost);
            person.Deliver(parcel,vehicle,orderCenter,cost,customer);
        }
        else if(orderCenter.getWeigth()>=20&&orderCenter.getWeigth()<1000){
            parcel=new HardWeights();
            vehicle=new Plane("Boing");
            System.out.println("Information about Plane: maxSpeed: "+vehicle.getMaxSpeed()+", maxWeigth= "+vehicle.getMaxWeight()+", model= "+ vehicle.getModel());
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            System.out.println("Final cost: "+cost);
            person.Deliver(parcel,vehicle,orderCenter,cost,customer);
        }
        else System.out.println("error, invalid weight");
    }

    public int checkPrice(OrderCenter orderCenter){
        int weigth=orderCenter.getWeigth();
        int cost=0;
        if(weigth<10){
            parcel=new LightWeights();
            vehicle=new Bicycle();
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            return cost;
        }
        else if(weigth>=10&&weigth<15){
            parcel=new MediumWeights();
            vehicle=new Car();
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            return cost;
        }
        else if(weigth>=15&&weigth<20){
            parcel=new MediumWeights();
            vehicle=new Truck();
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            return cost;
        }
        else if(weigth<1000&&weigth>=20){
            parcel=new HardWeights();
            vehicle=new Plane();
            cost+=parcel.getCost();
            cost+=vehicle.deliveryPay();
            return cost;
        }
        else System.out.println("invalid weight");

        return 0;
    }
}
