package threadTempClasses;

import lombok.Setter;

@Setter
public class A {
    B b;
    public synchronized int getName() throws InterruptedException {
        wait(50);
        return b.returnName();
    }

    public synchronized int returnName(){
        return 1;
    }
}
