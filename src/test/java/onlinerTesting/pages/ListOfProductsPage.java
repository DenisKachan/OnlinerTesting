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
        String commonLocatorForCheckboxList = "//span[text()='%s']/following::ul[@class='schema-filter__list']";
        String commonLocatorForCheckboxOption = "/descendant::span[contains(text(),'%s')]";
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(commonLocatorForCheckboxList, nameOfCheckboxList).concat(String.format(commonLocatorForCheckboxOption, optionForCheckbox)))));
        driver.findElement(By.xpath(String.format(commonLocatorForCheckboxList, nameOfCheckboxList).concat(String.format(commonLocatorForCheckboxOption, optionForCheckbox)))).click();
        return this;
    }

    public ListOfProductsPage setValuesInRangeInput(String nameOfInput, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String commonLocatorForMinimumValueRangeInput = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/input[contains(@data-bind,'value: facet.value.from')]";
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput))));
        driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput))).click();
        driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput))).sendKeys(minimumValue);
        String commonLocatorForMaximumValueRangeInput = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/input[contains(@data-bind,'value: facet.value.to')]";
        driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueRangeInput, nameOfInput))).click();
        driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueRangeInput, nameOfInput))).sendKeys(maximumValue);
        return this;
    }

    public ListOfProductsPage setValuesInDropdown(String nameOfDropdown, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String commonLocatorForMinimumValueDropdown = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/select[contains(@data-bind,'value: facet.value.from')]";
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueDropdown, nameOfDropdown))));
        Select dropdownMinimumValue = new Select(driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueDropdown, nameOfDropdown))));
        dropdownMinimumValue.selectByVisibleText(minimumValue);
        String commonLocatorForMaximumValueDropdown = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div/select[contains(@data-bind,'value: facet.value.to')]";
        Select dropdownMaximumValue = new Select(driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueDropdown, nameOfDropdown))));
        dropdownMaximumValue.selectByVisibleText(maximumValue);
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
                    String onlyNumbersResult = result.replaceAll("[^0-9]", "");
                    String onlyNumbersMinPrice = minPriceOfTV.replaceAll("[^0-9]", "");
                    String onlyNumbersMaxPrice = maxPriceOfTV.replaceAll("[^0-9]", "");
                    int parseResult = Integer.parseInt(onlyNumbersResult);
                    int parseMinPrice = Integer.parseInt(onlyNumbersMinPrice);
                    int parseMaxPrice = Integer.parseInt(onlyNumbersMaxPrice);
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
                    String onlyNumbersResult = result.replaceAll("[^0-9]", "");
                    String onlyNumbersMinSize = minSizeOfTV.replaceAll("[^0-9]", "");
                    String onlyNumbersMaxSize = maxSizeOfTV.replaceAll("[^0-9]", "");
                    String realSize = onlyNumbersResult.substring(0, 2);
                    int parseResult = Integer.parseInt(realSize);
                    int parseMinSize = Integer.parseInt(onlyNumbersMinSize);
                    int parseMaxSize = Integer.parseInt(onlyNumbersMaxSize);
                    softAssert.assertTrue(parseResult >= parseMinSize && parseResult <= parseMaxSize, "The sizes are different ");
                }
            } catch (StaleElementReferenceException ignored) {
            }
            attempts++;
        }
        return this;
    }

    public ListOfProductsPage assertAllData() {
        softAssert.assertAll();
        return this;
    }
}



