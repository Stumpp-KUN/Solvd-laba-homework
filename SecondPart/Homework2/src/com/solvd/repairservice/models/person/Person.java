package solvd.repairservice.models.person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class Person {
    private long id;
    private String name;
    private String surname;

}
