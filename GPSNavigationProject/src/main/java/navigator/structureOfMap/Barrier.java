package navigator.structureOfMap;

import lombok.Data;

@Data
public abstract class Barrier {
    private int x;
    private int y;
    private int n1;
    private int n2;
    private int weight;

}
