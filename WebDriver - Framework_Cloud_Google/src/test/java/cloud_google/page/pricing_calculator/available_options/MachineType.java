package cloud_google.page.pricing_calculator.available_options;

public enum MachineType {
    F1_MICRO,
    N1_STANDARD_1,
    N1_STANDARD_2,
    N1_STANDARD_4,
    N1_STANDARD_8,
    N1_STANDARD_16,
    N1_STANDARD_32;

    @Override
    public String toString() {
        return super.toString()
                .replace("_", "-")
                .toLowerCase();
    }
}
