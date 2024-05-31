package cloud_google.util;

import cloud_google.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class TestListener implements TestWatcher {

    private final Logger logger = LogManager.getRootLogger();

    private static WebDriver driver = null;

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        TestWatcher.super.testDisabled(context, reason);
        DriverSingleton.closeDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        TestWatcher.super.testSuccessful(context);
        logger.info("test succeed");
        DriverSingleton.closeDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testAborted(context, cause);
        DriverSingleton.closeDriver();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        TestWatcher.super.testFailed(context, cause);
        logger.info("test failed");
        saveScreenShot();
    }

    private void saveScreenShot() {
        String directoryPath = "./target/screenshots/";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String screenshotPath = directoryPath + getCurrentTimeAsString() + ".png";
        File screenCapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenCapture.toPath(), Paths.get(screenshotPath));
            logger.info("Successfully saved screenshot: {}", screenshotPath);
        } catch (IOException e) {
            logger.error("Failed to take a screenshot. ", e);
        } finally {
            DriverSingleton.closeDriver();
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    public static void setWebdriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
