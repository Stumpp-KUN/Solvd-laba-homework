package person;

public class CustomerBuilder {
    public Customer generateCustomer(String name,String surname,String streetA,String streetB,int weight,SpeedOfDelivery speedOfDelivery){
        return new Customer(name,surname,streetA,streetB,weight,null);
    }

    public Customer generateCustomer(String name,int weight){
        return new Customer(name,weight);
    }


}
