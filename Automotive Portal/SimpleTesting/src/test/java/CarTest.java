import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void carConstructorTest () {
        assertThrows(IllegalArgumentException.class, () -> new Car((short) -1,BodyType.COUPE, (short) 100, "BMW", 10_000, LocalDate.of(2000,1,1), "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 2,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,null, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) -1, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 122, null, 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", -1,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", -0.01F,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000, null, "1234567890asdfghj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfgh",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghaj",100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), null,100_000));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",-1));
        assertThrows(IllegalArgumentException.class, () -> new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",10_000_001));
        Car car = new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000);

        assertNotEquals(null, car.getSpecialEquipmentList());
    }

    @Test
    public void carSettersTest() {
        Car car = new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000);
        assertThrows(IllegalArgumentException.class, () -> car.setNumberOfHP((short) -1));
        assertThrows(IllegalArgumentException.class, () -> car.setPrice(-0.01F));
        int newOdimeter = car.getOdometer()-1;
        assertThrows(IllegalArgumentException.class, () -> car.setOdometer(newOdimeter));
    }

    @Test
    public void carToStringTest() {
        Car car = new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000);
        assertEquals("Car: {brand: BMW, bodyType: COUPE, numberOfWheels: 4, HP: 100, price: 10000.0, VIN: 1234567890asdfghj, production: 2000-01-01}", car.toString());
    }

    @Test
    public void specialEquipmentListTest() {
        Car car = new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000);
        assertThrows(IllegalArgumentException.class, () -> car.setSpecialEquipmentList(null),"Check the method setSpecialEquipmentList");

    }

}
