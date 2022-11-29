import order.MediumWeights;
import order.OrderCenter;
import person.Customer;
import person.Person;
import person.Сourier;

import java.util.Scanner;

public class Delivery {

    public static void main(String[] args)  {

        Customer customer;
        OrderCenter orderCenter;
        Delivery delivery=new Delivery();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter ur: name");
        String name=scanner.next();
        System.out.println(" Would u like to: 1-make an order, 2- check price for u package ");
        int i=scanner.nextInt();
        if(i==1){
            System.out.println("Enter ur surname,street of point A, street of point B, parcel weight");
            customer= delivery.setCustomer(name, scanner.next(), scanner.next(), scanner.next(), scanner.nextInt());
            System.out.println(customer.toString());
            orderCenter=new OrderCenter(customer);
            orderCenter.takeOrder(orderCenter);
        }
        else if(i==2){
            System.out.println("Enter weight of ur bag");
            int j=scanner.nextInt();
            customer=delivery.setCustomer(name,j);
            orderCenter=new OrderCenter(customer);
            int cost=orderCenter.checkPrice(orderCenter);
            System.out.println("Cost of delivery: "+cost);
        }


    }
    public Customer setCustomer(String n,String s,String streetA,String street,int weight){
        Customer customer=new Customer(n,s,streetA,street,weight);
        return customer;
    }

    public Customer setCustomer(String n,int weight){
        Customer customer=new Customer(n,weight);
        return customer;
    }

}
