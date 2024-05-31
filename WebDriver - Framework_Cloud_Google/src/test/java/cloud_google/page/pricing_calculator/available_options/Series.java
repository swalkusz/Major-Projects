package cloud_google.page.pricing_calculator.available_options;

public enum Series {
    N1,
    N2,
    E2,
    N2D,
    T2A,
    T2D,
    C3,
    C3D;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
