package Person;

import Order.OrderCenter;
import Order.Package;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer extends Person {
    private Package parcel;
    private int money=10000;
    private String streetOfPointA;
    private String streetOfPointB;


    public Customer(String firstName,String surname,int age, String streetA,String streetB){
        setFirstName(firstName);
        setSurname(surname);
        setAge(age);
        streetOfPointA=streetA;
        streetOfPointB=streetB;
    }

//    public void getOrder(Customer customer,int weight){
//        OrderCenter orderCenter=new OrderCenter(weight,customer.getStreetOfPointA(),customer.getStreetOfPointB(),customer);
//        orderCenter.makeAnOrder(orderCenter,customer);
//    }

    public void payMoney(int cost, Package parcel){
        if(money-cost>0) {
            money-=cost;
            getPackage(parcel);
            System.out.println(" Success ");
        }
        else System.out.println("Error, not enough money");
    }

    private void getPackage(Package parcel){
        this.parcel=parcel;
        System.out.println("The package successfully delivered");
    }

    @Override
    public void printPerson() {
        System.out.println("Name: "+getFirstName());
    }
}
