import exception.*;
import io.Word;
import io.Write;
import lambda.Fun;
import lombok.Setter;
import order.MediumWeights;
import order.OrderCenter;
import order.SpeedOfDelivery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Delivery{
    static Customer customer=new Customer();
    private static int j=23;
    private static int sum;
    private static String  refTemp=" f ";
    static CustomerBuilder customerBuilder= new CustomerBuilder();
    static OrderCenter orderCenter;
    private static int i=0;
    private final static Logger logger= LogManager.getRootLogger();

//    public static void main(String[] args) throws IOException {
//        Write write=new Write();
//        String k=write.getText("D:\\t.txt");
//        write.checkBlank(k);
//        String[] f=write.doArray(k);
//         write.checkSimilar(f);
//         write.checkSimilarNumber(f);
//    }

    public static void main(String[] args) throws ReflectionException {
        reflectionVar(new Delivery());
        System.out.println("----");
        reflectionMethods(new Delivery());
        System.out.println("----");
        reflectionObject();
        System.out.println("----");
        reflectionConstructor();
    }

//    public static void main(String[] args)  {
//        Scanner scanner=new Scanner(System.in);
//        logger.info("Enter ur: name");
//        String name=scanner.next();
//        logger.info(" Would u like to: 1-make an order, 2- check price for u package ");
//
//        try {
//            Set<Integer> choices=new HashSet<>();
//            choices.stream().filter(f->f>0).forEach(f-> System.out.println(f)); // 1 2
//            i = makeAChoise(scanner.nextInt());
//            choices.add(i);
//            List<Integer> list = new ArrayList<>(choices);
//            list.stream().forEach(s-> System.out.println(s));  // 3
//            if (getChoise(list.get(0), scanner, name) == false){}
//        }
//        catch (WrongChoiseException e) {
//            logger.warn(e.getMessage());
//        }
//        catch (WrongOptionNumberException | WrongWeightException e){
//            logger.warn(e.getMessage());
//        }
//    }

    public Delivery(int k, int j,int b){
        sum=k+j+b;
        logger.info(sum);
    }

    public Delivery() {
    }

    private static boolean reflectionVar(Object o) throws ReflectionException {
        try {
            Field[] f=o.getClass().getDeclaredFields();
            for(int i=0;i<f.length;i++)
                logger.info(f[i].toString());
            Field field = o.getClass().getDeclaredField("j");
            logger.info((int)field.get(o));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new ReflectionException("Error of reflection");
        }
        return true;
    }

    private static boolean reflectionMethods(Object o) throws ReflectionException{
        try {
            Method method = o.getClass().getDeclaredMethod("getTemp");
            method.setAccessible(true);
            method.invoke(o);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new ReflectionException("Error of reflection");
        }
        return true;
    }

    private boolean getTemp(){
        logger.info(refTemp);
        return true;
    }

    private static Object reflectionObject() throws ReflectionException {
        Delivery delivery=null;
        try {
            Class clazz = Class.forName(Delivery.class.getName());
            delivery = (Delivery) clazz.getDeclaredConstructor().newInstance();
            delivery.getTemp();
            return delivery;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ReflectionException("Error of reflection Object");
        }
    }

    private static boolean reflectionConstructor() throws ReflectionException{
        Delivery delivery=null;
        try {
            Class clazz = Class.forName(Delivery.class.getName());
            Constructor[] constructors = clazz.getConstructors();
            Class[] param = constructors[1].getParameterTypes();
            for (Class param1 : param) {
                logger.info(param1.getName() + " ");
            }
            delivery = (Delivery) clazz.getConstructor(param).newInstance(1,5,2 );
            return true;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new ReflectionException("Error of constructor");
        }
    }

    private static int makeAChoise(int x) throws WrongChoiseException {
        if(x!=1&&x!=2)
            throw new WrongChoiseException("Error of number");
        else return x;
    }

    private static List<String> getTypes(){
        List<String> types=new ArrayList<>();
        types.add(SpeedOfDelivery.FAST.toString());
        types.add(SpeedOfDelivery.DEFAULT.toString());
        types.add(SpeedOfDelivery.SLOW.toString());
        return types;
    }

    private static List<String> getParameters(){
        List<String> parametrs=new ArrayList<>();
        parametrs.add("surname");
        parametrs.add("street of point A");
        parametrs.add("street of point B ");
        parametrs.add("parcel weight       ");
        return parametrs;
    }

    private static boolean getChoise(int i,Scanner scanner, String name) throws WrongOptionNumberException, WrongWeightException {
        if(i==1){
            List<String> types=getTypes();
            List<String> par=getParameters();
            par.stream().filter(s->s.length()==5).forEach(System.out::println);
            logger.info("Enter ur "+par.get(0)+", "+par.get(1)+", "+par.get(2)+", "+par.get(3)); // 4 5
            customer= customerBuilder.generateCustomer(name, scanner.next(), scanner.next(), scanner.next(), scanner.nextInt(),null);
            Comparator<String> comp = (a, b) -> a.compareTo(b);
            types.stream().sorted(comp.reversed()).forEach(System.out::println);  // 6 7
            logger.info("Enter speed of delivery "+types.get(0)+", "+types.get(1)+", "+types.get(2));
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
