package Person;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class Person {
    private String firstName;
    private String surname;
    private int age;

    Person(String firstName,String surname,int age){
        this.firstName=firstName;
        this.surname=surname;
        this.age=age;
    }

    public abstract void printPerson();
}
