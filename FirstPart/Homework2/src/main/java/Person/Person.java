package Person;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class Person {
    private String firstName;
    private String surname;
    private int age;

    public abstract void printPerson();
}
