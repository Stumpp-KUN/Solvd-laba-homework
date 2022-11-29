package carDetails;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Engine {
    private String modelname;
    private boolean engineStatus;

    public Engine(String modelname) {
        this.modelname = modelname;
    }

    public void startEngine(){
        engineStatus=true;
    }
}
