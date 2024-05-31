package cloud_google.driver;

import cloud_google.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static final String RESOURCES_PATH = "src/test/resources/drivers/";
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (null == driver) {
            switch (System.getProperty("browser")) {
                case "msedge" -> {
                    System.setProperty("webdriver.edge.driver", RESOURCES_PATH + "msedgedriver.exe");
                    driver = new EdgeDriver();
                }
                case "firefox" -> {
                    System.setProperty("webdriver.gecko.driver", RESOURCES_PATH + "geckodriver.exe");
                    driver = new FirefoxDriver();
                }
                default -> {
                    System.setProperty("webdriver.chrome.driver", RESOURCES_PATH + "chromedriver.exe");
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        TestListener.setWebdriver(driver);
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
