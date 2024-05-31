package cloud_google.page.pricing_calculator.available_options;


public enum CommittedUsage {
    _NONE,
    _1_YEAR,
    _3_YEARS;

    @Override
    public String toString() {
        return super.toString()
                .substring(1)
                .replace("_", " ")
                .toLowerCase();
    }
}
