package person;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
public abstract class Person {
    private String firstName;
    private String surname;
    private int age;

    public abstract String printPerson();
}
