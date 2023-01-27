package solvd.repairservice.models.person;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Person {
    @XmlAttribute
    private long id;
    @XmlElement(name = "pName")
    private String name;
    @XmlElement(name = "pSurname")
    private String surname;

}
