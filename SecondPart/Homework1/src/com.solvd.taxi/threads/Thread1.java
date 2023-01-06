package threads;


import lombok.Setter;
import lombok.SneakyThrows;
import threadTempClasses.A;

@Setter
public class Thread1 extends Thread {

    A a;
    @SneakyThrows
    @Override
    public void run() {
        System.out.println(a.getName());
    }
}
