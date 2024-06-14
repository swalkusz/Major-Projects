import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    private static final Car car = new Car((short) 4, BodyType.COUPE, (short) 100, "BMW", 10_000, LocalDate.of(2000, 1, 1), "1234567890asdfghj", 100_000);


    @Test
    public void carConstructorTest() {
        String brand = "BWM";
        String vin = "1234567890asdfghj";
        assertThrows(IllegalArgumentException.class, () -> new Car((short) -1, BodyType.COUPE, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 2, BodyType.COUPE, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, null, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) -1, brand, 10_000, LocalDate.of(2000, 1, 1), vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 122, null, 10_000, LocalDate.of(2000, 1, 1), vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, -1, LocalDate.of(2000, 1, 1), vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, -0.01F, LocalDate.of(2000, 1, 1), vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, 10_000, null, vin, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), vin.substring(1), 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), vin + "x", 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), null, 100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), vin, -1));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4, BodyType.COUPE, (short) 100, brand, 10_000, LocalDate.of(2000, 1, 1), vin, 10_000_001));

        assertNotEquals(null, car.getSpecialEquipmentList());
    }

    @Test
    public void carSettersTest() {
        Car car = new Car((short) 4, BodyType.COUPE, (short) 100, "BMW", 10_000, LocalDate.of(2000, 1, 1), "1234567890asdfghj", 100_000);
        assertThrows(IllegalArgumentException.class, () -> car.setNumberOfHP((short) -1));
        assertThrows(IllegalArgumentException.class, () -> car.setPrice(-0.01F));
        int newOdometer = car.getOdometer() - 1;
        assertThrows(IllegalArgumentException.class, () -> car.setOdometer(newOdometer));
    }

    @Test
    public void carToStringTest() {
        assertEquals("Car: {brand: BMW, bodyType: COUPE, numberOfWheels: 4, HP: 100, price: 10000.0, VIN: 1234567890asdfghj, production: 2000-01-01}", car.toString());
    }

    @Test
    public void specialEquipmentListTest() {
        assertThrows(IllegalArgumentException.class, () -> car.setSpecialEquipmentList(null), "Check the method setSpecialEquipmentList");
    }

}
