package cloud_google.page.pricing_calculator.available_options;

public enum Share {
    OPEN_ESTIMATE_SUMMARY,
    COPY_LINK;

    @Override
    public String toString() {
        String text = super.toString().replace("_", " ").toLowerCase();
        return text.substring(0, 1).toUpperCase().concat(text.substring(1));
    }
}
