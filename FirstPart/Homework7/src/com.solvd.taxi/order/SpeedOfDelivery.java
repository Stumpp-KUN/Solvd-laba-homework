package order;

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
        return Math.floor(Math.random()*(speed-speed/2+1)+speed/2)*Math.floor(Math.random()*(luck-1+1)+1);
    }
}
