import order.OrderCenter;
import person.Customer;

import java.util.Scanner;

public class Delivery {
    public static void main(String[] args)  {
        Customer customer=new Customer("A","B",22,"qwe","lt");
        System.out.println("enter bag weight");
        Scanner scanner=new Scanner(System.in);
        int k=scanner.nextInt();
        OrderCenter orderCenter=new OrderCenter(k,customer);
        orderCenter.takeOrder(orderCenter);



    }
}
