package cloud_google.page.pricing_calculator;

public enum ProductName {
    COMPUTE_ENGINE,
    CLOUD_STORAGE,
    CLOUD_SQL,
    BIGQUERY;

    public int idxValue() {
        int idx = 0;
        for (ProductName element : ProductName.values()) {
            if (element.equals(this))
                break;
            idx++;
        }
        return idx;
    }

    @Override
    public String toString() {
        return super.toString().replace("_", " ");
    }
}
