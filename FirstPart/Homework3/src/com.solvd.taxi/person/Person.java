package person;

import lombok.Data;

@Data
public abstract class Person {
    private String firstName;
    private String surname;
    private int age;

    public abstract String printPerson();
}
