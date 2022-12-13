import exception.*;
import lombok.Setter;
import order.OrderCenter;
import order.SpeedOfDelivery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.*;

import java.util.*;

public class Delivery{
    static Customer customer=new Customer();
    static CustomerBuilder customerBuilder= new CustomerBuilder();
    static OrderCenter orderCenter;
    private static int i=0;
    private final static Logger logger= LogManager.getRootLogger();

    public static void main(String[] args)  {
        Scanner scanner=new Scanner(System.in);
        logger.info("Enter ur: name");
        String name=scanner.next();
        logger.info(" Would u like to: 1-make an order, 2- check price for u package ");

        try {
            Set<Integer> choices=new HashSet<>();
            i = makeAChoise(scanner.nextInt());
            choices.add(i);
            List<Integer> list = new ArrayList<>(choices);
            if (getChoise(list.get(0), scanner, name) == false){}
        }
        catch (WrongChoiseException e) {
            logger.warn(e.getMessage());
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
            logger.info("Enter ur surname,street of point A, street of point B, parcel weight");
            customer= customerBuilder.generateCustomer(name, scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),null);
            logger.info("Enter speed of delivery (fast,default,slow)");
            SpeedOfDelivery speedOfDelivery= null;
            try {
                speedOfDelivery = getSpeed(scanner.next());
                customer.setSpeedOfDelivery(speedOfDelivery);
                Map<Integer,Customer> customers=new HashMap<>();
                customers.put(0,customer);
                logger.info(customers.get(0).toString());
                orderCenter=new OrderCenter(customers.get(0));
                orderCenter.takeOrder(orderCenter);
                return true;
            } catch (WrongSpeedChoiseException e) {
                logger.error(e.getMessage());
            }
            return true;

        }
        else if(i==2){
            logger.info("Enter weight of ur bag");
            int j=scanner.nextInt();
            if(j<0) throw new WrongWeightException("Wrong weight, should be > 0");
            List<Customer> customers=new LinkedList<>();
            customer=customerBuilder.generateCustomer(name,j);
            customers.add(customer);
            orderCenter=new OrderCenter(customers.get(0));
            int cost=orderCenter.checkPrice(orderCenter);
            logger.info("Cost of delivery: "+cost);
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
