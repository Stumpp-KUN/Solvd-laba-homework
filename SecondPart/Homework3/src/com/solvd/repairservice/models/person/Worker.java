package solvd.repairservice.models.person;

import lombok.Data;
import solvd.repairservice.models.Order;
import solvd.repairservice.models.WorkerLicence;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker extends Person{
    @XmlElement(name = "workerLicence")
    private WorkerLicence workerLicence;
    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "Order")
    private List<Order> orders;

    public Worker(long id,String name,String surname,WorkerLicence workerLicence) {
        setId(id);
        setName(name);
        setSurname(surname);
        this.workerLicence=workerLicence;
    }

    public Worker(String name, String surname, WorkerLicence wl) {
        setName(name);
        setSurname(surname);
        setWorkerLicence(wl);
    }

    public Worker(String name,String surname,WorkerLicence workerLicence, List<Order> orders) {
        setName(name);
        setSurname(surname);
        this.workerLicence = workerLicence;
        this.orders = orders;
    }

    public Worker() {
    }
}
