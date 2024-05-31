package cloud_google.page.pricing_calculator.available_options;

public enum Region {
    FRANKFURT__EUROPE_WEST3,
    ALABAMA__US_EAST7,
    OREGON__US_WEST1;

    @Override
    public String toString() {
        return super.toString()
                .replace("__", " (")
                .replace("_", "-")
                .toLowerCase()
                .concat(")");
    }
}
