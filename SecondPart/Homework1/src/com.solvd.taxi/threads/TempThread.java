package threads;

import lombok.SneakyThrows;

import java.time.LocalDateTime;

public class TempThread implements Runnable{
    int name;

    public TempThread(int name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
            Thread.sleep(1000);
            System.out.println(name+ " "+LocalDateTime.now());
    }
}
