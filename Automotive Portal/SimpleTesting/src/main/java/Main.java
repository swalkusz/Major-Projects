import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static Map<String, Car> searchCar(String text, Map<String, Car> sourceMap) {
        if (text == null || text.length() == 0)
            throw new IllegalArgumentException("Try search again!");
        Map<String, Car> searchedCars = new HashMap<>();
        Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        for (Map.Entry<String, Car> carMap : sourceMap.entrySet()) {
            matcher = pattern.matcher(carMap.toString());
            if (matcher.find())
                searchedCars.put(carMap.getKey(), carMap.getValue());
        }
        return searchedCars;
    }

    public static void addSpecialEquipment(Car car) {
        showEquipment();
        try {
            car.addItemToSpecialEquipmentList(SpecialEquipment
                    .valueOf(getTheEquipmentFromUser()));
        } catch (IllegalArgumentException e) {
            System.out.println("Unavailable equipment! ");
        }
    }

    private static void showEquipment() {
        System.out.println("chose equipment: ");
        for (SpecialEquipment item : SpecialEquipment.values())
            System.out.println("\t" + item);
    }

    private static String getTheEquipmentFromUser() {
        Scanner scanner1 = new Scanner(System.in);
        return scanner1
                .nextLine()
                .toUpperCase()
                .replace(" ", "_");
    }

    private static String vehicleSelection() {
        System.out.println("""
                Press:
                '1' - to add a car
                 other character - to add a truck
                """);
        return scanner.next();
    }

    private static short setNumberOfWheels() {
        System.out.println("Enter the number of wheels: ");
        return scanner.nextShort();
    }

    private static BodyType setBodyType() {
        showAvailableBodyTypes();
        BodyType bodyType = null;
        try {
            bodyType = BodyType.valueOf(scanner.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass());
        }
        return bodyType;
    }

    private static void showAvailableBodyTypes() {
        System.out.println("Choose the body type: ");
        for (BodyType body : BodyType.values())
            System.out.println("\t" + body);
    }

    private static short setNumberOfHorsePower() {
        System.out.println("Number of horsepower: ");
        return scanner.nextShort();
    }

    private static String setBrand() {
        System.out.println("\nBrand: ");
        return scanner.next();
    }

    private static float setPrice() {
        System.out.println("Price: ");
        return scanner.nextFloat();
    }

    private static LocalDate setProductionYear() {
        System.out.println("Production year: ");
        return LocalDate.of(scanner.nextInt(), 1, 1);
    }

    private static String setVIN() {
        System.out.println("VIN: ");
        return scanner.next();
    }

    private static int setMileage() {
        System.out.println("mileage (km): ");
        return scanner.nextInt();
    }

    private static int setMaximumLoadCapacity() {
        System.out.println("Maximum Load Capacity (kg): ");
        return scanner.nextInt();
    }

    private static void addMultipleEquipments(Car car) {
        while (true) {
            System.out.println("Here is actual Special Equipment List: " + car.getSpecialEquipmentList());
            System.out.println("Any extra Special equipment?: y/n");
            if (scanner.next().equals("y"))
                addSpecialEquipment(car);
            else break;
        }
    }

    public static Car createNewCar() {

        String vehicleSelection = vehicleSelection();
        short wheels = setNumberOfWheels();
        BodyType bodyType = setBodyType();
        short hP = setNumberOfHorsePower();
        String brand = setBrand();
        float price = setPrice();
        LocalDate production = setProductionYear();
        String vin = setVIN();
        int mileage = setMileage();

        Car car;
        if (vehicleSelection.equals("1"))
            car = new Car(wheels, bodyType, hP, brand, price, production, vin, mileage);
        else
            car = new Truck(wheels, bodyType, hP, brand, price, production, vin, mileage, setMaximumLoadCapacity());

        addMultipleEquipments(car);

        return car;
    }

    private static void menu(Map<String, Car> carCatalog) {
        while (true) {
            System.out.println("""
                    Press a character:\s
                     1 - to add a vehicle\s
                     2 - to search a vehicle\s
                     3 - to show all vehicles\s""");
            switch (scanner.next()) {
                case "1":
                    Car car = createNewCar();
                    carCatalog.put(car.getVin(), car);
                    break;
                case "2":
                    System.out.println(searchCar(scanner.next(), carCatalog));
                    break;
                case "3":
                    for (Car carMap : carCatalog.values())
                        System.out.println(carMap);
                    break;
                default:
                    return;
            }
        }
    }

    private static void generateNRandomCars(Map<String, Car> targetCarCatalogToSave) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            StringBuilder randomVin = new StringBuilder(String.valueOf(random.nextLong(100_000_000_000_000_000L)));
            while (randomVin.length() < 17)
                randomVin.append("x");
            Car car = new Car((short) 4, BodyType.COUPE, (short) 100, "Audi", 50_000, LocalDate.of(2000 + i, 1, 1), randomVin.toString(), 100_000 + i * 100);
            targetCarCatalogToSave.put(car.getVin(), car);
        }
    }

    public static void main(String[] args) {

        Map<String, Car> carCatalog = new HashMap<>();
        Car car = new Car((short) 4, BodyType.COUPE, (short) 100, "BMW", 10_000, LocalDate.of(2000, 1, 1), "1234567890asdfghj", 100_000);
        Truck truck = new Truck((short) 4, BodyType.COUPE, (short) 100, "Volvo", 10_000, LocalDate.of(2010, 1, 1), "1234567890asdfg23", 100_000, 40_000);

        car.addItemToSpecialEquipmentList(SpecialEquipment.BLACK_WINDOWS);
        car.addItemToSpecialEquipmentList(SpecialEquipment.LEATHER_SEATS);
        carCatalog.put(car.getVin(), car);
        carCatalog.put(truck.getVin(), truck);

        generateNRandomCars(carCatalog);
        menu(carCatalog);
    }
}
