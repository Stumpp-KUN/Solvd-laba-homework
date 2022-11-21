package Person;

import Order.OrderCenter;
import Order.Package;
import Vehicles.Vehicle;
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


    public void Deliver(Package parcel, Vehicle vehicle, OrderCenter orderCenter,int cost,Customer customer)  {
        vehicle.startDrive();
        System.out.println("Driving from "+orderCenter.getStreetOfPointA()+" to "+orderCenter.getStreetOfPointB());
        System.out.println("I have come to "+customer.getFirstName()+" on "+orderCenter.getStreetOfPointB());
            getMoney(cost,customer,parcel);
    }

    @Override
    public void printPerson(){
        System.out.println("Courier "+getFirstName()+ " experience:"+experience);
    }

    private void getMoney(int cost,Customer customer,Package parcel){
        customer.payMoney(cost,parcel);
    }


}
