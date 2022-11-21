package Order;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public abstract class Package {
    private int price;

    public int getCost(){
        return price;
    }
}
