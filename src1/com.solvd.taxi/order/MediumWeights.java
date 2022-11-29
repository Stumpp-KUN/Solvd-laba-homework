package order;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MediumWeights extends Package{

    @Override
    public int getCost(){
        return 140;
    }
}
