package cloud_google.page.pricing_calculator.available_options;

public enum NumberOfGPUs {
    _1,
    _2,
    _4;

    @Override
    public String toString() {
        return super.toString().substring(1);
    }
}
