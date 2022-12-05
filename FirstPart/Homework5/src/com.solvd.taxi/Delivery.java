import exception.WrongChoiseException;
import exception.WrongOptionNumberException;
import exception.WrongSpeedChoiseException;
import exception.WrongWeightException;
import order.OrderCenter;
import order.SpeedOfDelivery;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.*;

import java.util.Scanner;

public class Delivery {
    static Customer customer=new Customer();
    static CustomerBuilder customerBuilder= new CustomerBuilder();
    static OrderCenter orderCenter;
    private static int i=0;
    private final static Logger logger= LogManager.getRootLogger();

    public static void main(String[] args)  {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter ur: name");
        String name=scanner.next();
        System.out.println(" Would u like to: 1-make an order, 2- check price for u package ");

        try {
            i = makeAChoise(scanner.nextInt());
        } catch (WrongChoiseException e) {
            e.printStackTrace();
        }

        try {
            if (getChoise(i, scanner, name) == false){}
        }
        catch (WrongOptionNumberException | WrongWeightException e){
            logger.warn(e.getMessage());
        }
    }

    private static int makeAChoise(int x) throws WrongChoiseException {
        if(x!=1&&x!=2)
            throw new WrongChoiseException("Error of number");
        else return x;
    }

    private static boolean getChoise(int i,Scanner scanner, String name) throws WrongOptionNumberException, WrongWeightException {
        if(i==1){
            System.out.println("Enter ur surname,street of point A, street of point B, parcel weight");
            customer= customerBuilder.generateCustomer(name, scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),null);
            System.out.println("Enter speed of delivery (fast,default,slow)");
            SpeedOfDelivery speedOfDelivery= null;
            try {
                speedOfDelivery = getSpeed(scanner.next());
                customer.setSpeedOfDelivery(speedOfDelivery);
                System.out.println(customer.toString());
                orderCenter=new OrderCenter(customer);
                orderCenter.takeOrder(orderCenter);
                return true;
            } catch (WrongSpeedChoiseException e) {
                logger.error(e.getMessage());
            }
            return true;

        }
        else if(i==2){
            System.out.println("Enter weight of ur bag");
            int j=scanner.nextInt();
            if(j<0) throw new WrongWeightException("Wrong weight, should be > 0");
            customer=customerBuilder.generateCustomer(name,j);
            orderCenter=new OrderCenter(customer);
            int cost=orderCenter.checkPrice(orderCenter);
            System.out.println("Cost of delivery: "+cost);
            return true;
        }
        else
            throw new WrongOptionNumberException("Wrong number of option");

    }

    private static SpeedOfDelivery getSpeed(String speed) throws WrongSpeedChoiseException {
        speed=speed.toUpperCase();
        switch (speed){
            case "FAST":
                return SpeedOfDelivery.FAST;
            case "SLOW":
                return SpeedOfDelivery.SLOW;
            case "DEFAULT":
                return SpeedOfDelivery.DEFAULT;
            default:
                throw new WrongSpeedChoiseException("Wrong speed");
        }
    }

}
