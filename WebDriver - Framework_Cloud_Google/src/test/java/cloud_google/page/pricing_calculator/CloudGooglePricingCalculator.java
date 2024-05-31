package cloud_google.page.pricing_calculator;

import cloud_google.page.pricing_calculator.available_options.Share;

public interface CloudGooglePricingCalculator {
    CloudGooglePricingCalculator fillOutTheForm(Object... settings);

    CostEstimateSummary share(Share shareOption);
}
