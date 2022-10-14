package Task2.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ListOfProductsPage extends BasePage {

    @FindBy(xpath = "//div[@class='schema-product__group']")
    WebElement enabledProducts;

    @FindBys({
            @FindBy(xpath = "//div[@class='schema-product__title']/child::a")
    })
    public List<WebElement> titleOfProduct;

    @FindBys({
            @FindBy(xpath = "//div[@class='schema-product__price']/child::a")
    })
    public List<WebElement> priceOfProduct;

    @FindBys({
            @FindBy(xpath = "//div[@class='schema-product__description']/child::span[contains(text(),'частота матрицы')]")
    })
    public List<WebElement> descriptionOfProduct;

    private final String commonLocatorForCheckboxList = "//span[text()='%s']/following::ul[@class='schema-filter__list'][1]";
    private final String commonLocatorForCheckboxOption = "/descendant::span[contains(text(),'%s')]";
    private final String commonLocatorForMinimumValueRangeInput = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div[1]/input";
    private final String commonLocatorForMaximumValueRangeInput = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div[2]/input";
    private final String commonLocatorForMinimumValueDropdown = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div[1]/select";
    private final String commonLocatorForMaximumValueDropdown = "//span[text()='%s']/following::div[@class='schema-filter__group']/child::div[2]/select";


    private WebElement checkboxOption(String nameOfCheckboxList, String optionForCheckbox) {
        return driver.findElement(By.xpath(String.format(commonLocatorForCheckboxList, nameOfCheckboxList).concat(String.format(commonLocatorForCheckboxOption, optionForCheckbox))));
    }

    private WebElement minimumValueRageInput(String nameOfInput) {
        return driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput)));
    }

    private WebElement maximumValueRageInput(String nameOfInput) {
        return driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueRangeInput, nameOfInput)));
    }

    private WebElement minimumValueDropdown(String nameOfDropdown) {
        return driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueDropdown, nameOfDropdown)));
    }

    private WebElement maximumValueDropdown(String nameOfDropdown) {
        return driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueDropdown, nameOfDropdown)));
    }

    public ListOfProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ListOfProductsPage isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(enabledProducts));
        return this;
    }

    public ListOfProductsPage chooseOptionOfCheckBox(String nameOfCheckboxList, String optionOfCheckbox) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", checkboxOption(nameOfCheckboxList, optionOfCheckbox));
        checkboxOption(nameOfCheckboxList, optionOfCheckbox).click();
        return this;
    }

    public ListOfProductsPage setValuesInRangeInput(String nameOfInput, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", minimumValueRageInput(nameOfInput));
        minimumValueRageInput(nameOfInput).click();
        minimumValueRageInput(nameOfInput).sendKeys(minimumValue);
        maximumValueRageInput(nameOfInput).click();
        maximumValueRageInput(nameOfInput).sendKeys(maximumValue);
        return this;
    }

    public ListOfProductsPage setValuesInDropdown(String nameOfDropdown, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", minimumValueDropdown(nameOfDropdown));
        Select dropdownMinimumValue = new Select(minimumValueDropdown(nameOfDropdown));
        dropdownMinimumValue.selectByVisibleText(minimumValue);
        Select dropdownMaximumValue = new Select(maximumValueDropdown(nameOfDropdown));
        dropdownMaximumValue.selectByVisibleText(maximumValue);
        return this;
    }

    public void checkingTheModelOfTV(String modelOfTv) {
        for (WebElement webElement : titleOfProduct) {
            String result = webElement.getAttribute("innerText");
            Assert.assertTrue(result.contains(modelOfTv));
        }
    }

    public void checkingThePriceOfTV(String priceOfTV) {
        for (WebElement webElement : priceOfProduct) {
            String result = webElement.getAttribute("innerText");
            String onlyNumbersResult = result.replaceAll("[^0-9]", "");
            String onlyNumbersPrice = priceOfTV.replaceAll("[^0-9]", "");
            int parseResult = Integer.parseInt(onlyNumbersResult);
            int parsePrice = Integer.parseInt(onlyNumbersPrice);
            Assert.assertTrue(parseResult <= parsePrice);
        }
    }

    public void checkingTheResolutionOfTV(String resolutionOfTv) {
        for (WebElement webElement : descriptionOfProduct) {
            String result = webElement.getAttribute("innerText");
            Assert.assertTrue(result.contains(resolutionOfTv));
        }
    }

    public void checkingSizeOfTV(String minSizeOfTV, String maxSizeOfTV) {
        for (WebElement webElement : descriptionOfProduct) {
            String result = webElement.getAttribute("innerText");
            String onlyNumbersResult = result.replaceAll("[^0-9]", "");
            String onlyNumbersMinSize = minSizeOfTV.replaceAll("[^0-9]", "");
            String onlyNumbersMaxSize = maxSizeOfTV.replaceAll("[^0-9]", "");
            String realSize = onlyNumbersResult.substring(0,2);
            int parseResult = Integer.parseInt(realSize);
            int parseMinSize = Integer.parseInt(onlyNumbersMinSize);
            int parseMaxSize = Integer.parseInt(onlyNumbersMaxSize);
            Assert.assertTrue(parseResult >= parseMinSize && parseResult<=parseMaxSize);
        }
    }

    public ListOfProductsPage totalCheck(String modelTV, String priceOfTV, String resolutionTV, String minSizeTV, String maxSizeTV) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                checkingTheModelOfTV(modelTV);
                checkingTheResolutionOfTV(resolutionTV);
                checkingThePriceOfTV(priceOfTV);
                checkingSizeOfTV(minSizeTV,maxSizeTV);
                break;
            } catch (StaleElementReferenceException | AssertionError a) {
            }
            attempts++;
        }
        return this;
    }

}



