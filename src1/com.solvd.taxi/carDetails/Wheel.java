package carDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Wheel {
    private int ageOfIssue;
    private String seasonOfWheel;

    public Wheel(int ageOfIssue, String seasonOfWheel) {
        this.ageOfIssue = ageOfIssue;
        this.seasonOfWheel = seasonOfWheel;
    }
}
