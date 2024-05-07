package pageobject_model.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.StringJoiner;

public class PastebinResultsPage {

    private WebDriver driver;
    private String code;
    private String pasteExpiration;
    private String pasteNameOrTitle;
    private String syntaxHighlighting;

    @FindBy(xpath = "//*[@class='post-view js-post-view']")
    private List<WebElement> generalPasteResults;

    public PastebinResultsPage(WebDriver driver, String code, String pasteExpiration, String pasteNameOrTitle, String syntaxHighlighting) {
        this.driver = driver;
        this.code = code;
        this.pasteExpiration = pasteExpiration;
        this.pasteNameOrTitle = pasteNameOrTitle;
        this.syntaxHighlighting = syntaxHighlighting;
        PageFactory.initElements(driver, this);
    }

    public String appliedOptionsToString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        generalPasteResults = driver.findElements(By.xpath("//*[@class='expire' or text()='"+ syntaxHighlighting +"' or @class='source " + syntaxHighlighting.toLowerCase() + "' or text()='"+pasteNameOrTitle+"' or text()='"+ syntaxHighlighting + "']"));
        int counter = 1;
        for (WebElement item:generalPasteResults) {
            stringJoiner.add("item"+counter++ + ": "+item.getText());
        }
        return stringJoiner.toString();
    }
}
