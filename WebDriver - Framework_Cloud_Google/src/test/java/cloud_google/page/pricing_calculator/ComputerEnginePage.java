package cloud_google.page.pricing_calculator;

import cloud_google.page.pricing_calculator.available_options.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ComputerEnginePage extends CloudGooglePricingCalculatorPage implements CloudGooglePricingCalculator {
    private final WebDriver driver;

    @FindBy(id = "c11")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//*[text()='Operating System / Software']/../../../div[1]")
    private WebElement operatingSystem;

    @FindBy(xpath = "//*[text()='Machine Family']/../../../div")
    private WebElement machineFamily;

    @FindBy(xpath = "//*[text()='Series']/../../../div")
    private WebElement series;

    @FindBy(xpath = "//*[text()='Machine type' and @id='c31']/../../../div")
    private WebElement machineType;

    @FindBy(xpath = "//*[text()='Local SSD' and @id='c39']/../../../div")
    private WebElement localSSD;

    @FindBy(xpath = "//*[text()='Region' and @id='c43']/../../../div")
    private WebElement region;

    @FindBy(xpath = "//*[text()='Number of GPUs']/../../../div")
    private WebElement numberOfGPUs;

    @FindBy(xpath = "//*[text()='GPU Model']/../../../div")
    private WebElement gpuModel;

    public ComputerEnginePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void setProvisioningModel(ProvisioningModel provisioningModel) {
        scrollAndClickTheElement(By.xpath(String.format("//*[@for='%s']", provisioningModel.toString().toLowerCase())));
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances.clear();
        this.numberOfInstances.sendKeys(String.valueOf(numberOfInstances));
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        selectItem(this.operatingSystem, operatingSystem);
    }

    public void setMachineFamily(MachineFamily machineFamily) {
        selectItem(this.machineFamily, machineFamily);
    }

    public void setSeries(Series series) {
        selectItem(this.series, series);
    }

    public void setMachineType(MachineType machineType) {
        selectItem(this.machineType, machineType);
    }

    public void setGPUModel(GPUModel gpuModel) {
        showGPUOptions(this.gpuModel);
        selectItem(this.gpuModel, gpuModel);
    }

    public void setNumberOfGPUs(NumberOfGPUs numberOfGPUs) {
        showGPUOptions(this.numberOfGPUs);
        scrollAndClickTheElement(this.numberOfGPUs);
        scrollAndClickTheElement(By.xpath(String.format("//li[@data-value='%s']", numberOfGPUs.toString())));
    }

    private void showGPUOptions(WebElement expandedList) {
        WebElement addGPUsButton = driver.findElement(By.xpath("//*[@aria-label='Add GPUs']"));
        String ariaCheckedValue = addGPUsButton.getAttribute("aria-checked");
        if (ariaCheckedValue != null && ariaCheckedValue.equals("false"))
            scrollAndClickTheElement(addGPUsButton);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(expandedList));
    }

    public void setLocalSSD(LocalSSD localSSD) {
        selectItem(this.localSSD, localSSD);
    }

    public void setRegion(Region region) {
        try {
            selectItem(this.region, region);
        } catch (NoSuchElementException e) {
            System.out.println(e.getClass() + " - Caused by region setting. The region probably does not exist with the options applied. ");
        }
    }

    public void setCommittedUsage(CommittedUsage committedUsage) {
        scrollAndClickTheElement(By.xpath(String.format("//label[text()='%s']", committedUsage.toString())));
    }

    public CloudGooglePricingCalculator fillOutTheForm(Object... settings) {
        closeAlerts();
        for (Object object : settings) {
            if ((object instanceof List<?> list)) {
                for (Object listObject : list)
                    followTheAppropriateMethod(listObject);
            } else {
                followTheAppropriateMethod(object);
            }
        }
        return this;
    }

    private void closeAlerts() {
        try {
            clickTheElement(By.xpath("//button[text()='OK, got it']"));
            clickTheElement(By.xpath("//*[@class='close']/*"));
        } catch (NoSuchElementException | ElementClickInterceptedException ignored) {
        }
    }

    private void followTheAppropriateMethod(Object... settings) {
        for (Object object : settings) {
            switch (object.getClass().getSimpleName()) {
                case "CommittedUsage" -> setCommittedUsage((CommittedUsage) object);
                case "GPUModel" -> setGPUModel((GPUModel) object);
                case "LocalSSD" -> setLocalSSD((LocalSSD) object);
                case "MachineFamily" -> setMachineFamily((MachineFamily) object);
                case "MachineType" -> setMachineType((MachineType) object);
                case "NumberOfGPUs" -> setNumberOfGPUs((NumberOfGPUs) object);
                case "OperatingSystem" -> setOperatingSystem((OperatingSystem) object);
                case "ProvisioningModel" -> setProvisioningModel((ProvisioningModel) object);
                case "Region" -> setRegion((Region) object);
                case "Series" -> setSeries((Series) object);
                case "NumberOfInstances" -> {
                    NumberOfInstances numberOfInstances = (NumberOfInstances) object;
                    setNumberOfInstances(numberOfInstances.value());
                }
            }
        }
    }

    @Override
    public CostEstimateSummary share(Share shareOption) {
        updateServiceCost();
        try {
            clickShareButton();
        } catch (NoSuchElementException | ElementClickInterceptedException ignored) {
            closeAlerts();
            clickShareButton();
        }
        clickTheElement(By.xpath(String.format("//*[text()='%s']", shareOption.toString())));
        return new CostEstimateSummary(driver);
    }

    private void clickShareButton() {
        scrollAndClickTheElement(By.xpath("//*[text()='Share']"));
    }

    private void updateServiceCost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Service cost updated')]")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='Z7Qi9d HY0Uh']")));
        } catch (TimeoutException ignored) {
        }
    }

    private void selectItem(WebElement expandedList, Object itemToSelect) {
        scrollAndClickTheElement(expandedList);
        scrollAndClickTheElement(By.xpath(String.format(
                "//li//*[contains(translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'%s')]/../.."
                , itemToSelect.toString()
        )));
    }

    private void scrollAndClickTheElement(By by) {
        scrollAndClickTheElement(driver.findElement(by));
    }

    private void scrollAndClickTheElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ block: 'center', inline: 'center' });", webElement);
        WebElement webElement2 = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(webElement));
        webElement2.click();
    }

    private void clickTheElement(By by) {
        WebElement selectedOption = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(by));
        selectedOption.click();
    }

}