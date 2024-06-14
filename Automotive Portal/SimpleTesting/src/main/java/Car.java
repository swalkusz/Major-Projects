import java.time.LocalDate;
import java.util.*;

public class Car implements VehicleActivities {
    private final short numberOfWheels;
    private final BodyType bodyType;
    private short numberOfHP;
    private final String brand;
    private float price;
    private final LocalDate productionYear;
    private final String vin;
    private int odometer;
    private Set<SpecialEquipment> specialEquipmentList = new HashSet<>();

    public Car(short numberOfWheels, BodyType bodyType, short numberOfHP, String brand, float price, LocalDate productionYear, String vin, int odometer) {
        if (numberOfWheels < 3 || bodyType == null || numberOfHP < 0 || brand == null || price < 0 || productionYear == null || vin == null || vin.length() != 17 || odometer < 0 || odometer > 10_000_000)
            throw new IllegalArgumentException("Something went wrong with creating a car. Check the Constructor");
        this.numberOfWheels = numberOfWheels;
        this.bodyType = bodyType;
        this.numberOfHP = numberOfHP;
        this.brand = brand;
        this.price = price;
        this.productionYear = productionYear;
        this.vin = vin;
        this.odometer = odometer;
    }


    public short getNumberOfHP() {
        return numberOfHP;
    }

    public void setNumberOfHP(short numberOfHP) {
        if (numberOfHP < 0)
            throw new IllegalArgumentException("You cannot set car HP below 0! ");
        this.numberOfHP = numberOfHP;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0)
            throw new IllegalArgumentException("Price should be positive value! ");
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        if (odometer < this.odometer)
            throw new IllegalArgumentException("Odometer can only increases! ");
        this.odometer = odometer;
    }

    public short getNumberOfWheels() {
        return numberOfWheels;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public String getBrand() {
        return brand;
    }

    public LocalDate getProductionYear() {
        return productionYear;
    }

    public Set<SpecialEquipment> getSpecialEquipmentList() {
        return specialEquipmentList;
    }

    public void setSpecialEquipmentList(Set<SpecialEquipment> specialEquipmentList) {
        if (specialEquipmentList == null)
            throw new IllegalArgumentException("Cannot set Special Equipment List as null! ");
        this.specialEquipmentList = specialEquipmentList;
    }

    public void addItemToSpecialEquipmentList(SpecialEquipment specialEquipment) {
        specialEquipmentList.add(specialEquipment);
    }

    public void removeItemFromSpecialEquipmentList(SpecialEquipment specialEquipment) {
        specialEquipmentList.remove(specialEquipment);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");
        stringJoiner.add("brand: " + brand)
                .add("bodyType: " + bodyType)
                .add("numberOfWheels: " + numberOfWheels)
                .add("HP: " + numberOfHP)
                .add("price: " + price)
                .add("VIN: " + vin)
                .add("production: " + productionYear);
        return "Car: " + stringJoiner;
    }

    @Override
    public boolean isMovingFast(int speed) {
        return speed >= 100;
    }

    @Override
    public String makeNoise() {
        return "Brummm!";
    }
}
