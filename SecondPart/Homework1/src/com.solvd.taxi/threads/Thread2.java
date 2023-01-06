package threads;

import lombok.Setter;
import lombok.SneakyThrows;
import threadTempClasses.B;

@Setter
public class Thread2 extends Thread{
    B b;
    @SneakyThrows
    @Override
    public void run() {
        System.out.println(b.getName());
    }
}
