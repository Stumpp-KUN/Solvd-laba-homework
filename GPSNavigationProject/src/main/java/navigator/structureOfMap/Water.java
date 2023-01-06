package navigator.structureOfMap;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Water extends Barrier{
    public Water(int x,int y,int n1,int n2,int weight) {
        setX(x*2);
        setY(y);
        setN1(n1);
        setN2(n2);
        setWeight(weight);
    }
}
