package navigator.navigationSystem;

import lombok.Data;
import navigator.structureOfMap.Point;
import navigator.structureOfMap.Water;
import navigator.structureOfMap.Barrier;

import java.util.ArrayList;

@Data
public class Map {
    private ArrayList<Barrier> points=new ArrayList<Barrier>();

    public ArrayList<Barrier> initializeMap(){
        int k=0;
        for(int x=0;x<=100;x++){
            for(int y=0;y<=100;y++,k++){
                points.add(k,new Point());
                points.get(k).setX(x);
                points.get(k).setY(y);
                System.out.println(points.get(k));
            }
        }
        Water water=new Water();
        //System.out.println(points.add(7,water));
        return points;
    }

}
