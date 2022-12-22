package lambda;

public class Fun2 <X extends Integer,Y extends Integer>{
    public int getSum(X x,Y y){
        B<X,Y> a1=(n,n1)->{
            return x.intValue()+y.intValue();
        };

        int t= a1.sum(x,y);
        return t;
    }
}

interface B<T extends Integer,Y extends Integer>{
    int sum(T o,Y o2);
}
