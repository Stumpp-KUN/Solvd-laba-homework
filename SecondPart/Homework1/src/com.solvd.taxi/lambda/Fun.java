package lambda;

public class Fun <X,Y>  {
    public String getString(X x,Y y){
        A<X,Y> a1=(n,n1)->{
            String f=x.toString();
            String t=y.toString();
            return f+" "+t;
        };

        String t= a1.concat(x,y);
        return t;
    }
}

interface A<T,Y>{
    String concat(T o,Y o2);
}



