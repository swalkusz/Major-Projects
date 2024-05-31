package cloud_google.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CloudGoogleHomePage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private final WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchInput;

    public CloudGoogleHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CloudGoogleHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public CloudGoogleSearchResultsPage search(String searchedPhrase) {
        searchInput.click();
        searchInput.sendKeys(searchedPhrase);
        searchInput.submit();
        return new CloudGoogleSearchResultsPage(driver);
    }
}
