import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TruckTest{
    @Test
    public void constructorTest() {
        Car truck = new Truck((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000, 30_000);
        assertThrows(IllegalArgumentException.class, () -> new Truck((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000, -1));
        Assertions.assertInstanceOf(Truck.class, truck);
        Assertions.assertInstanceOf(Car.class, truck);
    }
    @Test
    public void toStringTest() {
        Car truck = new Truck((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000, 30_000);
        assertEquals("Truck: {brand: BMW, bodyType: COUPE, numberOfWheels: 4, HP: 100, price: 10000.0, VIN: 1234567890asdfghj, production: 2000-01-01, maximumLoadCapacity: 30000}", truck.toString());
    }

}
