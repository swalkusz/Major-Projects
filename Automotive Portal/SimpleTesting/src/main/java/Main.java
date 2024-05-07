import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static Map<String,Car> searchCar (String text, Map<String,Car> sourceMap) {
        if (text == null || text.length() == 0)
            throw new IllegalArgumentException("Try search again!");
        Map<String,Car> searchedCars = new HashMap<>();
        Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        for (Map.Entry<String,Car> carMap: sourceMap.entrySet()) {
            matcher = pattern.matcher(carMap.toString());
            if (matcher.find())
                searchedCars.put(carMap.getKey(),carMap.getValue());
        }
        return searchedCars;
    }

    public static void addSpecialEquipment (Car car) {
        System.out.println("chose equipment: ");
        for (SpecialEquipment item:SpecialEquipment.values())
            System.out.println("\t"+item);
        try {
            Scanner scanner1 = new Scanner(System.in);
            String s = scanner1.nextLine().toUpperCase().replace(" ", "_");
            car.addItemToSpecialEquipmentList(SpecialEquipment.valueOf(s));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Unavailable equipment! ");
        }
    }

    public static Car createNewCar () {
        System.out.println("Press:\n '1' - to add a car\n other character to add a truck\n");
        String choiceVehicle = scanner.next();
        //1
        System.out.println("Enter the number of wheels: ");
        short wheels = scanner.nextShort();
        //2
        System.out.println("Choose the body type: ");
        for (BodyType body:BodyType.values())
            System.out.println("\t"+body);
        BodyType bodyType = null;
        try {
            bodyType = BodyType.valueOf(scanner.next().toUpperCase());
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getClass());
        }
        //3
        System.out.println("Number of horsepower: ");
        short hP = scanner.nextShort();
        //4
        System.out.println("\nBrand: ");
        String brand = scanner.next();
        //5
        System.out.println("Price: ");
        float price = scanner.nextFloat();
        //6
        System.out.println("Production year: ");
        LocalDate production = LocalDate.of(scanner.nextInt(), 1,1);
        //7
        System.out.println("VIN: ");
        String vin = scanner.next();
        //8
        System.out.println("mileage (km): ");
        int mileage = scanner.nextInt();

        Car car;
        if (choiceVehicle.equals("1"))
            car = new Car(wheels,bodyType,hP,brand,price,production,vin,mileage);
        else {
            //9
            System.out.println("Maximum Load Capacity (kg): ");
            car = new Truck(wheels, bodyType, hP, brand, price, production, vin, mileage, scanner.nextInt());
        }

        while (true) {
            System.out.println("Here is actual Special Equipment List: " + car.getSpecialEquipmentList());
            System.out.println("Any extra Special equipment?: y/n");
            if (scanner.next().equals("y"))
                addSpecialEquipment(car);
            else break;
        }

        return car;
    }

    public static void main(String[] args) {
        Random random = new Random();
        Map<String,Car> cars = new HashMap<>();
        Car car = new Car((short) 4,BodyType.COUPE, (short) 100, "BMW", 10_000,  LocalDate.of(2000,1,1), "1234567890asdfghj",100_000);
        Truck truck = new Truck((short) 4,BodyType.COUPE, (short) 100, "Volvo", 10_000,  LocalDate.of(2010,1,1), "1234567890asdfg23",100_000, 40_000);

        car.addItemToSpecialEquipmentList(SpecialEquipment.BLACK_WINDOWS);
        car.addItemToSpecialEquipmentList(SpecialEquipment.LEATHER_SEATS);
        cars.put(car.getVin(), car);
        cars.put(truck.getVin(), truck);

        for (int i = 0; i < 10; i++) {
            StringBuilder randomVin = new StringBuilder(String.valueOf(random.nextLong(100_000_000_000_000_000L)));
            while (randomVin.length()<17)
                randomVin.append("x");
            car = new Car((short) 4,BodyType.COUPE, (short) 100, "Audi", 50_000,  LocalDate.of(2000 + i,1,1),randomVin.toString() ,100_000+ i*100);
            cars.put(car.getVin(), car);
        }


        menu: while (true) {
            System.out.println("Press a character: \n 1 - to add a vechicle " +
                    "\n 2 - to search a vechicle \n 3 - to show all vechicles ");
            switch (scanner.next()) {
                case "1":
                    car = createNewCar();
                    cars.put(car.getVin(), car);
                    break;
                case "2":
                    System.out.println(searchCar(scanner.next(), cars));
                    break;
                case "3":
                    for (Car carMap : cars.values())
                        System.out.println(carMap);
                    break;
                default:
                    break menu;
            }
        }

    }
}
