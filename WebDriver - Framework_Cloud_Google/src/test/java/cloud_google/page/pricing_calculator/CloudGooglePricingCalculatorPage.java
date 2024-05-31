package cloud_google.page.pricing_calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CloudGooglePricingCalculatorPage {
    private final WebDriver driver;

    public CloudGooglePricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CloudGooglePricingCalculator addToEstimate(ProductName productName) {
        try {
            clickAddToEstimateButton(productName);
        } catch (Exception exception) {
            acceptCookies();
            clickAddToEstimateButton(productName);
        }

        if (productName == ProductName.COMPUTE_ENGINE) {
            return new ComputerEnginePage(driver);
        }
        return null;
    }

    private void searchAndClickElement(By by) {
        WebElement webElement = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(by));
        webElement.click();
    }

    private void acceptCookies() {
        searchAndClickElement(By.xpath("//button[text()='OK, got it']"));
        searchAndClickElement(By.xpath("//*[@class='close']/*"));
    }

    private void clickAddToEstimateButton(ProductName productName) {
        searchAndClickElement(By.xpath("(//button[.//span[text()='Add to estimate']])[1]"));
        searchAndClickElement(By.cssSelector(String.format("[data-idx='%d']", productName.idxValue())));
    }

}
