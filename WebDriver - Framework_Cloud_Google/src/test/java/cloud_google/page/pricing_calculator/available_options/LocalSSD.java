package cloud_google.page.pricing_calculator.available_options;

public enum LocalSSD {
    _0,
    _1X375_GB,
    _2X375_GB,
    _3X375_GB,
    _4X375_GB,
    _5X375_GB,
    _6X375_GB,
    _7X375_GB,
    _8X375_GB,
    _24X375_GB;

    @Override
    public String toString() {
        return super.toString()
                .substring(1)
                .replace("_", " ")
                .toLowerCase();
    }
}
