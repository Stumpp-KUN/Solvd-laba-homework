package person;

import exception.NotEnoughMoneyException;
import order.Package;
import vehicles.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor

public final class Сourier extends Person{
    private int experience;

    public Сourier(int experience, String name, String surname, int age, Package parcel) {
        setFirstName(name);
        setSurname(surname);
        setAge(age);
        this.experience = experience;
    }


    public boolean Deliver(Package parcel, Vehicle vehicle,int cost,Customer customer) throws NotEnoughMoneyException {
        vehicle.startDrive();
        System.out.println("Driving from "+customer.getStreetOfPointA()+" to "+customer.getStreetOfPointB());
        System.out.println("I have come to "+customer.getFirstName()+" on "+customer.getStreetOfPointB());
            getMoney(cost,customer,parcel);
            return true;
    }

    @Override
    public String printPerson(){
        return "Courier "+getFirstName()+ " experience:"+experience;
    }

    private boolean getMoney(int cost,Customer customer,Package parcel) throws NotEnoughMoneyException {
        customer.payMoney(cost,parcel);
        return true;
    }

    @Override
    public String toString() {
        return "Сourier{" +
                "experience=" + experience +
                 ", name=" +getFirstName()+", surname= "+getSurname()+'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Сourier сourier = (Сourier) o;
        return experience == сourier.experience && getSurname().equals(сourier.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience);
    }
}
