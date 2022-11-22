package order;

public abstract class Package {
    private int price;
    private int weight;

    public int getWeight(){return weight;}

    public int getCost(){
        return price;
    }
}
