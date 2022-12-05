package person;

import exception.NotEnoughMoneyException;
import order.Package;
import lombok.Data;
import lombok.NoArgsConstructor;
import order.SpeedOfDelivery;

import java.util.Objects;

@Data
@NoArgsConstructor
public final class Customer extends Person {
    private Package parcel;
    private int money=(int)Math.floor(Math.random()*(4000-400+1)+400);
    private String streetOfPointA;
    private String streetOfPointB;
    private int weight;
    SpeedOfDelivery speedOfDelivery;


    public Customer(String firstName,String surname, String streetA,String streetB,int weight,SpeedOfDelivery speedOfDelivery){
        setFirstName(firstName);
        setSurname(surname);
        streetOfPointA=streetA;
        streetOfPointB=streetB;
        this.weight=weight;
        this.speedOfDelivery=speedOfDelivery;
    }

    public Customer(String name,int weight){
        setFirstName(name);
        this.weight=weight;
    }

    public boolean payMoney(int cost, Package parcel) throws NotEnoughMoneyException {
        if(money-cost>0) {
            money-=cost;
            getPackage(parcel);
            System.out.println(" Enough money ");
            return true;
        }
        else throw new NotEnoughMoneyException("Not enough money");
    }

    private boolean getPackage(Package parcel){
        this.parcel=parcel;
        System.out.println("Customer has taken a package");
        return true;
    }

    @Override
    public String printPerson() {
        return "Name: "+getFirstName()+", surname: "+getSurname()+", customer street: "+getStreetOfPointA()+", customer's final street: "+getStreetOfPointB()+", speedOfDelivery: "+getSpeedOfDelivery();
    }

    @Override
    public String toString(){
        return "Customer name: "+getFirstName()+", Customer's street: "+getStreetOfPointA()+", speed: "+getSpeedOfDelivery();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return money == customer.money && weight == customer.weight && streetOfPointA.equals(customer.streetOfPointA) && streetOfPointB.equals(customer.streetOfPointB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), money, streetOfPointA, streetOfPointB, weight);
    }
}
