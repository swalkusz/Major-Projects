import java.time.LocalDate;

public class Truck extends Car {
    private final int maximumLoadCapacity;

    public Truck(short numberOfWheels, BodyType bodyType, short numberOfHP, String brand, float price, LocalDate productionYear, String vin, int odometer, int maximumLoadCapacity) {
        super(numberOfWheels, bodyType, numberOfHP, brand, price, productionYear, vin, odometer);
        if (maximumLoadCapacity < 0)
            throw new IllegalArgumentException("The maximum load capacity should be positive value!");
        this.maximumLoadCapacity = maximumLoadCapacity;
    }

    public int getMaximumLoadCapacity() {
        return maximumLoadCapacity;
    }

    @Override
    public String toString() {
        return super.toString()
                .replace("Car", "Truck")
                .replace("}",", maximumLoadCapacity: "+maximumLoadCapacity+"}");
    }

    @Override
    public String makeNoise() {
        return super.makeNoise() + " Beeep!!!";
    }
}
