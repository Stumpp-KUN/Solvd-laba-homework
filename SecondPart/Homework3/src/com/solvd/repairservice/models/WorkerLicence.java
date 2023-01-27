package solvd.repairservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import solvd.repairservice.models.person.Worker;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlRootElement(name = "workerLicence")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkerLicence {
    @XmlAttribute
    private long id;
    @XmlElement
    private String country;
    @XmlElement
    private Worker worker;
}
