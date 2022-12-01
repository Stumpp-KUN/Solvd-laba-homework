package vehicles;

import carDetails.Engine;
import carDetails.Wheel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Car extends Vehicle{
    private Engine engine;
    private Wheel wheel;

    public Car(Engine engine, Wheel wheel, String model) {
        this.engine = engine;
        this.wheel = wheel;
        setModel(model);
        setCost(200);
        setMaxSpeed(60);
        setMaxWeight(14);
    }

    @Override
    public boolean startDrive(){
        System.out.println("Car's engine has been started");
        return true;
    }

    @Override
    public int deliveryPay(){
        return getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return Objects.equals(engine, car.engine) && Objects.equals(wheel, car.wheel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), engine, wheel);
    }

    @Override
    public String toString() {
        return "Car{" +"model= "+getModel()+
                "engine=" + engine +
                ", wheel=" + wheel +
                '}';
    }
}
