package cloud_google.test;

import cloud_google.page.pricing_calculator.available_options.*;
import cloud_google.driver.DriverSingleton;
import cloud_google.page.CloudGoogleHomePage;
import cloud_google.page.pricing_calculator.ProductName;
import cloud_google.service.TestDataReader;
import cloud_google.util.TestListener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/*
* "browser" and "environment" system properties needed to be set to be able to run the test.
* */
@ExtendWith(TestListener.class)
public class CloudGoogleHomePageTest {
    private WebDriver driver;

    @BeforeEach
    public void browserSetup() {
        driver = DriverSingleton.getDriver();
    }

    @RepeatedTest(5)
    public void reportCorrespondsToInitialValues() {
        boolean reportCorrespondsToInitialValues = true;
        List<Object> settings = new ArrayList<>();
        settings.add(ProvisioningModel.REGULAR);
        settings.add(OperatingSystem.FREE__DEBIAN$_CENTOS$_COREOS$_UBUNTU_OR_BYOL);
        settings.add(MachineFamily.GENERAL_PURPOSE);
        settings.add(Series.N1);
        settings.add(MachineType.N1_STANDARD_16);
        settings.add(LocalSSD._4X375_GB);
        settings.add(CommittedUsage._3_YEARS);
        settings.add(GPUModel.NVIDIA_V100);
        settings.add(NumberOfGPUs._2);
        settings.add(Region.OREGON__US_WEST1);
        settings.add(new NumberOfInstances(4));

        String actualReport = new CloudGoogleHomePage(driver)
                .openPage()
                .search(TestDataReader.getTestData("test.search"))
                .openNthSearchResult(1)
                .addToEstimate(ProductName.COMPUTE_ENGINE)
                .fillOutTheForm(settings)
                .share(Share.OPEN_ESTIMATE_SUMMARY)
                .generateReport();

        for (Object item : settings) {
            if (!actualReport.toLowerCase().contains(item.toString().toLowerCase()) && !(item.getClass().getSimpleName().equals("Region") || item.getClass().getSimpleName().equals("MachineFamily"))) {
                reportCorrespondsToInitialValues = false;
                break;
            }
        }
        Assert.assertTrue(reportCorrespondsToInitialValues, "Report:\n" + actualReport);
    }

}
