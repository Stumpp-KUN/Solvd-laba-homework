import Order.OrderCenter;
import Person.Customer;
import Person.Person;
import Vehicles.Car;
import Vehicles.Vehicle;

import java.util.Scanner;

public class Delivery {
    public static void main(String[] args)  {
        Customer customer=new Customer("A","B",22,"qwe","lt");
        System.out.println("enter bag weight");
        Scanner scanner=new Scanner(System.in);
        int k=scanner.nextInt();
        OrderCenter orderCenter=new OrderCenter(k,customer.getStreetOfPointA(),customer.getStreetOfPointB(),customer);
        orderCenter.makeAnOrder(orderCenter,customer);


    }
}
