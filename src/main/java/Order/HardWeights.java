package Order;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HardWeights extends Package{
    @Override
    public int getCost(){
        return 200;
    }
}
