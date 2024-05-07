package pageobject_model.test;

import pageobject_model.page.items.PasteExpiration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageobject_model.page.PastebinHomePage;

public class PastebinWebdriverTest {
    private WebDriver driver;

    @BeforeEach
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    /*
    * This method checks if the entered arguments are equal to retrieved.
    * Browser page title matches 'Paste Name / Title'
    * Syntax is suspended for bash
    * Check that the code matches.
    * */
    @RepeatedTest(3)
    public void argumentsEnteredCorrespondsToTheRetrieved() {
        String code = "public static void main(String[] args) {\n" +
                "   StringJoiner stringJoiner = new StringJoiner(\";\",\"[\",\"]\");\n" +
                "   stringJoiner\n" +
                "       .add(\"first\")\n" +
                "       .add(\"second\")\n" +
                "       .add(\"third\")\n" +
                "       .add(String.valueOf(4));\n" +
                "   // [first;second;third;4]\n" +
                "}";
        PasteExpiration pasteExpiration = PasteExpiration.$1_HOUR;
        String pasteNameOrTitle = "simple stringJoiner in Java";
        String syntaxHighlighting = "Java";

        String actualAppliedOptions= new PastebinHomePage(driver)
                .openPage()
                .createNewPaste(code, pasteExpiration, pasteNameOrTitle, syntaxHighlighting)
                .appliedOptionsToString();

        String expectedAppliedOptions = "item1: " + pasteNameOrTitle
                + "\nitem2: " + "60 MIN"
                + "\nitem3: " + syntaxHighlighting
                + "\nitem4: " + code;

        Assert.assertEquals(actualAppliedOptions, expectedAppliedOptions, "Check the received values and their order! " +
                "\nFound:\n"+actualAppliedOptions+"\nExpected:\n"+expectedAppliedOptions);
    }

    @AfterEach
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

}
