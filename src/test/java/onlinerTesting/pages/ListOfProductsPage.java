package onlinerTesting.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class ListOfProductsPage extends BasePage {

    private final By enabledProducts = By.xpath("//div[@class='schema-product__group']");

    private final By titleOfProduct = By.xpath("//div[@class='schema-product__title']/child::a");

    private final By priceOfProduct = By.xpath("//div[@class='schema-product__price']/child::a");

    private final By descriptionOfProduct = By.xpath("//div[@class='schema-product__description']/child::span[contains(text(),'частота матрицы')]");

    private final String commonLocatorForCheckboxList = "//span[text()='%s']/following::ul[@class='schema-filter__list']";

    private final String commonLocatorForCheckboxOption = "/descendant::span[contains(text(),'%s')]";

    private final String commonLocatorForMinimumValueRangeInput = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/input[contains(@data-bind,'value: facet.value.from')]";

    private final String commonLocatorForMaximumValueRangeInput = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/input[contains(@data-bind,'value: facet.value.to')]";

    private final String commonLocatorForMinimumValueDropdown = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/select[contains(@data-bind,'value: facet.value.from')]";

    private final String commonLocatorForMaximumValueDropdown = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/select[contains(@data-bind,'value: facet.value.to')]";

    SoftAssert softAssert = new SoftAssert();

    public ListOfProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ListOfProductsPage isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(configReader.getProperty("webDriverWait")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(enabledProducts)));
        return this;
    }

    public ListOfProductsPage chooseOptionOfCheckBox(String nameOfCheckboxList, String optionForCheckbox) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(commonLocatorForCheckboxList, nameOfCheckboxList).concat(String.format(commonLocatorForCheckboxOption, optionForCheckbox)))));
        driver.findElement(By.xpath(String.format(commonLocatorForCheckboxList, nameOfCheckboxList).concat(String.format(commonLocatorForCheckboxOption, optionForCheckbox)))).click();
        return this;
    }

    public ListOfProductsPage setValuesInRangeInput(String nameOfInput, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput))));
        driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput))).click();
        driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput))).sendKeys(minimumValue);
        driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueRangeInput, nameOfInput))).click();
        driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueRangeInput, nameOfInput))).sendKeys(maximumValue);
        return this;
    }

    public ListOfProductsPage setValuesInDropdown(String nameOfDropdown, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueDropdown, nameOfDropdown))));
        Select dropdownMinimumValue = new Select(driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueDropdown, nameOfDropdown))));
        Select dropdownMaximumValue = new Select(driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueDropdown, nameOfDropdown))));
        if (nameOfDropdown.equals("Диагональ")) {
            dropdownMinimumValue.selectByVisibleText(minimumValue + "\"");
            dropdownMaximumValue.selectByVisibleText(maximumValue + "\"");
        }else {dropdownMinimumValue.selectByVisibleText(minimumValue);
             dropdownMaximumValue.selectByVisibleText(maximumValue);
        }
        return this;
    }

    public ListOfProductsPage checkingTheModelOfTV(String modelOfTv) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                for (WebElement webElement : driver.findElements(titleOfProduct)) {
                    String result = webElement.getAttribute("innerText");
                    softAssert.assertTrue(result.contains(modelOfTv), "The models are different");
                }
            } catch (StaleElementReferenceException ignored) {
            }
            attempts++;
        }
        return this;
    }

    public ListOfProductsPage checkingThePriceOfTV(String minPriceOfTV, String maxPriceOfTV) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                for (WebElement webElement : driver.findElements(priceOfProduct)) {
                    String result = webElement.getAttribute("innerText");
                    String[] arrayResult = result.split(" ");
                    String priceResult = arrayResult[1].substring(0,7).replaceAll("[,]",".");
                    double parseResult = Double.parseDouble(priceResult);
                    double parseMinPrice = Double.parseDouble(minPriceOfTV);
                    double parseMaxPrice = Double.parseDouble(maxPriceOfTV);
                    softAssert.assertTrue(parseResult >= parseMinPrice && parseResult <= parseMaxPrice, "The prices are different");
                }
            } catch (StaleElementReferenceException ignored) {
            }
            attempts++;
        }
        return this;
    }

    public ListOfProductsPage checkingTheResolutionOfTV(String resolutionOfTv) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                for (WebElement webElement : driver.findElements(descriptionOfProduct)) {
                    String result = webElement.getAttribute("innerText");
                    softAssert.assertTrue(result.contains(resolutionOfTv), "The resolutions are different ");
                }
            } catch (StaleElementReferenceException ignored) {
            }
            attempts++;
        }
        return this;
    }

    public ListOfProductsPage checkingSizeOfTV(String minSizeOfTV, String maxSizeOfTV) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                for (WebElement webElement : driver.findElements(descriptionOfProduct)) {
                    String result = webElement.getAttribute("innerText");
                    String [] arrayResult = result.split(" ");
                    String sizeResult = arrayResult[0];
                    String onlyNumbersResult = sizeResult.replaceAll("[^0-9]", "");
                    int parseResult = Integer.parseInt(onlyNumbersResult);
                    int parseMinSize = Integer.parseInt(minSizeOfTV);
                    int parseMaxSize = Integer.parseInt(maxSizeOfTV);
                    softAssert.assertTrue(parseResult >= parseMinSize && parseResult <= parseMaxSize, "The sizes are different ");
                }
            } catch (StaleElementReferenceException ignored) {
            }
            attempts++;
        }
        return this;
    }
}



