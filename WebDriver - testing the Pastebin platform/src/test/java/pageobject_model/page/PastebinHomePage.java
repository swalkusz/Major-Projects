package pageobject_model.page;

import pageobject_model.page.items.PasteExpiration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinHomePage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private WebDriver driver;

    @FindBy(xpath = "//*[text()='AGREE']")
    private WebElement agreeButton;

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement codeField;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationDropdownList;

    @FindBy(id = "select2-postform-format-container")
    private WebElement syntaxDropdown;

    @FindBy(id = "postform-name")
    private WebElement pasteNameOrTitle;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public PastebinResultsPage createNewPaste(String code, PasteExpiration pasteExpiration, String pasteNameOrTitle, String syntaxHighlighting) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(agreeButton));
        agreeButton.click();
        expirationDropdownList.click();
        WebElement pasteExpirationOption = driver.findElement(By.xpath("//li[text()='"+pasteExpiration.pasteExpirationToString()+"']"));
        pasteExpirationOption.click();
        this.syntaxDropdown.click();
        WebElement syntaxHighlightingOption = driver.findElement(By.xpath("//li[text()='"+syntaxHighlighting+"']"));
        syntaxHighlightingOption.click();
        this.pasteNameOrTitle.sendKeys(pasteNameOrTitle);
        codeField.sendKeys(code);
        createNewPasteButton.click();
        return new PastebinResultsPage(driver, code, pasteExpiration.pasteExpirationToString(), pasteNameOrTitle, syntaxHighlighting);
    }

}
