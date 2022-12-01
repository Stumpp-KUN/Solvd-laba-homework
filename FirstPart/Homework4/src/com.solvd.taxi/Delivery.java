import order.MediumWeights;
import order.OrderCenter;
import person.*;

import java.util.Scanner;

public class Delivery {
    static Customer customer=new Customer();
    static CustomerBuilder customerBuilder= new CustomerBuilder();
    static OrderCenter orderCenter;

    public static void main(String[] args)  {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter ur: name");
        String name=scanner.next();
        System.out.println(" Would u like to: 1-make an order, 2- check price for u package ");
        int i=scanner.nextInt();
        if(getChoise(i,scanner,name)==false)
            System.out.println("Error of values");


    }

    private static boolean getChoise(int i,Scanner scanner, String name){
        if(i==1){
            System.out.println("Enter ur surname,street of point A, street of point B, parcel weight");
            customer= customerBuilder.generateCustomer(name, scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),null);
            System.out.println("Enter speed of delivery (fast,default,slow)");
            SpeedOfDelivery speedOfDelivery=getSpeed(scanner.next());
            if(speedOfDelivery!=null){
                customer.setSpeedOfDelivery(speedOfDelivery);
                System.out.println(customer.toString());
                orderCenter=new OrderCenter(customer);
                orderCenter.takeOrder(orderCenter);
                return true;
            }
            else return false;

        }
        else if(i==2){
            System.out.println("Enter weight of ur bag");
            int j=scanner.nextInt();
            customer=customerBuilder.generateCustomer(name,j);
            orderCenter=new OrderCenter(customer);
            int cost=orderCenter.checkPrice(orderCenter);
            System.out.println("Cost of delivery: "+cost);
            return true;
        }
        else
            return false;
    }

    private static SpeedOfDelivery getSpeed(String speed){
        speed=speed.toUpperCase();
        switch (speed){
            case "FAST":
                return SpeedOfDelivery.FAST;
            case "SLOW":
                return SpeedOfDelivery.SLOW;
            case "DEFAULT":
                return SpeedOfDelivery.DEFAULT;
            default:
                System.out.println("Error speed");
        }
        return null;
    }

}
