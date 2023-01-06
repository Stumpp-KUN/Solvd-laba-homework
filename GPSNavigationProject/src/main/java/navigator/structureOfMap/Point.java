package navigator.structureOfMap;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Point extends Barrier {
    public Point(int x, int y, int n1, int n2, int weight) {
        setX(x);
        setY(y);
        setN1(n1);
        setN2(n2);
        setWeight(weight);
    }
}
