package solvd.repairservice.models.person;

import lombok.Data;
import lombok.NoArgsConstructor;
import solvd.repairservice.models.Order;

import java.util.ArrayList;
import java.util.List;

@Data
public class Client extends Person {
    private String phoneNumber;
    private List<Order> order=new ArrayList<>();

    public Client() {
    }
    public Client(long id,String name,String surname) {
        setId(id);
        setName(name);
        setSurname(surname);
    }
    public Client(long id, String name, String surname, String phoneNumber) {
        setId(id);
        setName(name);
        setSurname(surname);
        this.phoneNumber=phoneNumber;
    }



    public Client(String name, String surname, String number) {
        setName(name);
        setSurname(surname);
        this.phoneNumber=number;
    }

    public Client(String name, String surname,String phoneNumber, List<Order> order) {
        setName(name);
        setSurname(surname);
        this.phoneNumber = phoneNumber;
        this.order = order;
    }

    @Override
    public String toString() {
        return "Name "+getName()+", Surname "+getSurname()+", Orders "+order.toString();
    }
}
