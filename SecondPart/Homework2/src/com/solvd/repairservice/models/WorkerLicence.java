package solvd.repairservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import solvd.repairservice.models.person.Worker;

@Data
@NoArgsConstructor
public class WorkerLicence {
    private long id;
    private String country;
    private Worker worker;
}
