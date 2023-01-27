package solvd.repairservice.models;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.FIELD)
public class Service {
    @XmlAttribute
    private long id;
    @XmlElement
    private String description;
}
