package cloud_google.page.pricing_calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.StringJoiner;

public class CostEstimateSummary {
    private final WebDriver driver;

    public CostEstimateSummary(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        String mainWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public String generateReport() {
        StringJoiner stringJoiner = new StringJoiner(";\n", "<", ">");
        String xPath = "//span[@class='zv7tnb']";
        List<WebElement> elementsNamesList = driver.findElements(By.xpath(xPath));
        List<WebElement> elementsValuesList = driver.findElements(By.xpath(String.format("%s/../*[2]", xPath)));
        for (int i = 0; i < elementsNamesList.size(); i++) {
            stringJoiner.add(elementsNamesList.get(i).getText() + "= " + elementsValuesList.get(i).getText());
        }
        return stringJoiner.toString();
    }

}
