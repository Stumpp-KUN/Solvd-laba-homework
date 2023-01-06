package threadTempClasses;

import lombok.Setter;

@Setter
public class B {
    A a;
    public synchronized int getName() throws InterruptedException {
        wait(50);
        return a.returnName();
    }
    public synchronized int returnName(){
        return 2;
    }
}
