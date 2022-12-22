package lambda;

public class Fun3<X extends Integer,Y extends Double>{

    public double getMult(X x,Y y){
        C<X,Y> a1=(n,n1)->{
            return x.intValue()*y.doubleValue();
        };
        double t= a1.mult(x,y);
        return t;
    }
}
interface C<X,Y>{
    double mult(X o,Y o2);
}
