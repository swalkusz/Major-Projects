package cloud_google.page.pricing_calculator.available_options;

public record NumberOfInstances(int numberOfInstances) {
    public int value() {
        return numberOfInstances;
    }

    @Override
    public String toString() {
        return String.valueOf(numberOfInstances);
    }
}
