package order;

import carDetails.Engine;
import carDetails.Wheel;
import person.*;
import vehicles.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OrderCenter {
    private int weigth;
    private String streetOfPointA;
    private String streetOfPointB;
    private Package parcel;
    private Vehicle vehicle;
    private int cost;
    private Customer customer;
    Сourier person = new Сourier();



    public OrderCenter( Customer customer) {
        this.weigth = customer.getWeight();
        this.streetOfPointA = customer.getStreetOfPointA();
        this.streetOfPointB = customer.getStreetOfPointB();
        this.customer = customer;
    }


    public boolean takeOrder(OrderCenter orderCenter) {
        Order order=new Order();
        if(order.makeAnOrder(orderCenter)==true) {
            return true;
        }
        else
            System.out.println("error");
            return false;

    }

    public int checkPrice(OrderCenter orderCenter){
        PriceOrder priceOrder=new PriceOrder();
        return priceOrder.checkPrice(orderCenter);
    }


}
