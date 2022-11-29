package vehicles;

import carDetails.Engine;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Truck extends Vehicle{
    private String model;
    private Engine engine;

    public Truck(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        setCost(400);
        setMaxSpeed(45);
        setMaxWeight(49);
    }

    @Override
    public boolean startDrive(){
        System.out.println("Truck's engine has been started");
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
        Truck truck = (Truck) o;
        return Objects.equals(model, truck.model) && Objects.equals(engine, truck.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, engine);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "model='" + model + '\'' +
                ", engine=" + engine +
                '}';
    }
}
