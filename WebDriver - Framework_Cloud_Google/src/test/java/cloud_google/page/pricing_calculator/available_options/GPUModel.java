package cloud_google.page.pricing_calculator.available_options;

public enum GPUModel {
    NVIDIA_TESLA_V100,
    NVIDIA_V100;

    @Override
    public String toString() {
        return super.toString()
                .replace("_", " ")
                .toLowerCase();
    }
}
