package navigator.structureOfMap;

import navigator.navigationSystem.Map;
import navigator.structureOfMap.Barrier;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        Map map = new Map();
        map.initializeMap();
        ArrayList<Barrier> f = map.getPoints();
        System.out.println(f.get(7).getX());
    }
}
