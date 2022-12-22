package order;

public class SpeedContest {

    private int avgSpeed;
    private int realSpeed;

    public SpeedContest(int avgSpeed, int realSpeed) {
        this.avgSpeed = avgSpeed;
        this.realSpeed = realSpeed;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public int getRealSpeed() {
        return realSpeed;
    }

    public void setRealSpeed(int realSpeed) {
        this.realSpeed = realSpeed;
    }
}
