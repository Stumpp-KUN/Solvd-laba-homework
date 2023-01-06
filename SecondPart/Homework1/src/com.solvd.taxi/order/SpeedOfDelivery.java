package order;

import java.util.function.Function;

public enum SpeedOfDelivery {
    FAST(95,3,2.5),
    DEFAULT(60,1.2,1.5),
    SLOW(30,0.7,1.2);

    private int speed;
    private double mult;
    private double luck;

    SpeedOfDelivery(int speed, double mult,double luck) {
        this.speed=speed;
        this.mult=mult;
        this.luck=luck;
    }

    public SpeedContest getSpeed(){
        int realSpeed=(int)randSpeed(speed);
        return new SpeedContest(speed,realSpeed);
    }

    public double getMULT() {
        return this.mult;
    }

    private double randSpeed(int speed){
        double k=0;
        Function<Double,Double> fun= a-> Math.floor(Math.random()*(speed-speed/2+1)+speed/2);
        Function<Double,Double> fun2= a-> Math.floor(Math.random()*(luck-1+1)+1);
        return Math.floor(fun.apply(k)*fun2.apply(k));
    }
}
