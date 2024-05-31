package cloud_google.page;

import cloud_google.page.pricing_calculator.CloudGooglePricingCalculatorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CloudGoogleSearchResultsPage {
    private final WebDriver driver;

    public CloudGoogleSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CloudGooglePricingCalculatorPage openNthSearchResult(int resultNumber) {
        WebElement targetLink = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(String.format("//*[@class = 'gsc-expansionArea']/div[%d]/div/div[1]/div/a", resultNumber))));
        targetLink.click();
        if (driver.getCurrentUrl().contains("https://cloud.google.com/products/calculator")) {
            return new CloudGooglePricingCalculatorPage(driver);
        }
        return null;
    }
}
