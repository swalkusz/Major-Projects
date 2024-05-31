package cloud_google.page.pricing_calculator.available_options;

public enum MachineFamily {
    GENERAL_PURPOSE,
    COMPUTE$_OPTIMIZED,
    MEMORY$_OPTIMIZED,
    ACCELERATOR$_OPTIMIZED;

    @Override
    public String toString() {
        return super.toString()
                .replace("$_", "-")
                .replace("_", " ")
                .toLowerCase();
    }
}
