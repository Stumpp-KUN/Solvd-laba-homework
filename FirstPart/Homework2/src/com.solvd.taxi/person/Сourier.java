package person;

import order.OrderCenter;
import order.Package;
import vehicles.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Сourier extends Person{
    private int experience;

    public Сourier(int experience, String name, String surname, int age, Package parcel) {
        setFirstName(name);
        setSurname(surname);
        setAge(age);
        this.experience = experience;
    }


    public boolean Deliver(Package parcel, Vehicle vehicle,int cost,Customer customer)  {
        vehicle.startDrive();
        System.out.println("Driving from "+customer.getStreetOfPointA()+" to "+customer.getStreetOfPointB());
        System.out.println("I have come to "+customer.getFirstName()+" on "+customer.getStreetOfPointB());
            getMoney(cost,customer,parcel);
            return true;
    }

    @Override
    public void printPerson(){
        System.out.println("Courier "+getFirstName()+ " experience:"+experience);
    }

    private void getMoney(int cost,Customer customer,Package parcel){
        customer.payMoney(cost,parcel);
    }


}
