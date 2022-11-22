package person;

import order.Package;
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

    public boolean payMoney(int cost, Package parcel){
        if(money-cost>0) {
            money-=cost;
            getPackage(parcel);
            System.out.println(" Success ");
            return true;
        }
        else System.out.println("Error, not enough money");
        return false;
    }

    private boolean getPackage(Package parcel){
        this.parcel=parcel;
        System.out.println("The package successfully delivered");
        return true;
    }

    @Override
    public void printPerson() {
        System.out.println("Name: "+getFirstName());
    }
}
