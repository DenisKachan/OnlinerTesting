package onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ListOfProductsPage extends BasePage {

    private final By titleOfProduct = By.xpath("//div[@class='schema-product__title']/child::a[contains(text(),'')]");
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
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(descriptionOfProduct)));
        return this;
    }

    public ListOfProductsPage chooseOptionOfCheckBox(String nameOfCheckboxList, String optionForCheckbox) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement optionOfCheckbox = driver.findElement(By.xpath(String.format(commonLocatorForCheckboxList, nameOfCheckboxList).concat(String.format(commonLocatorForCheckboxOption, optionForCheckbox))));
        js.executeScript("arguments[0].scrollIntoView(true);", optionOfCheckbox);
        optionOfCheckbox.click();
        return this;
    }

    public ListOfProductsPage setValuesInRangeInput(String nameOfInput, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement minimumValueRangeInput = driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueRangeInput, nameOfInput)));
        WebElement maximumValueRangeInput = driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueRangeInput, nameOfInput)));
        js.executeScript("arguments[0].scrollIntoView(true);", minimumValueRangeInput);
        minimumValueRangeInput.click();
        minimumValueRangeInput.sendKeys(minimumValue);
        maximumValueRangeInput.click();
        maximumValueRangeInput.sendKeys(maximumValue);
        return this;
    }

    public ListOfProductsPage setValuesInDropdown(String nameOfDropdown, String minimumValue, String maximumValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dropdownMinimumValue = driver.findElement(By.xpath(String.format(commonLocatorForMinimumValueDropdown, nameOfDropdown)));
        WebElement dropdownMaximumValue = driver.findElement(By.xpath(String.format(commonLocatorForMaximumValueDropdown, nameOfDropdown)));
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownMinimumValue);
        Select selectMinDropdown = new Select(dropdownMinimumValue);
        Select selectMaxDropdown = new Select(dropdownMaximumValue);
        if (nameOfDropdown.equals("Диагональ")) {
            selectMinDropdown.selectByVisibleText(minimumValue + "\"");
            selectMaxDropdown.selectByVisibleText(maximumValue + "\"");
        }else {selectMinDropdown.selectByVisibleText(minimumValue);
             selectMaxDropdown.selectByVisibleText(maximumValue);
        }
        return this;
    }

    public ListOfProductsPage checkingTheModelOfTV(String modelOfTv) {
        List<WebElement> titles = driver.findElements(titleOfProduct);
                for (int i = 0; i<titles.size();i++) {
                    String result = titles.get(i).getText();
                    System.out.println(result);
                    softAssert.assertTrue(result.contains(modelOfTv), "The models are different");
                }
        return this;
    }

    public ListOfProductsPage checkingThePriceOfTV(String minPriceOfTV, String maxPriceOfTV) {
        List<WebElement> prices = driver.findElements(priceOfProduct);
                for (int i = 0; i<prices.size();i++) {
                    String result = prices.get(i).getText();
                    String[] arrayResult = result.split(" ");
                    String priceResult = arrayResult[1].replaceAll("[,]",".");
                    double parseResult = Double.parseDouble(priceResult);
                    double parseMinPrice = Double.parseDouble(minPriceOfTV);
                    double parseMaxPrice = Double.parseDouble(maxPriceOfTV);
                    System.out.println(result);
                    softAssert.assertTrue(parseResult >= parseMinPrice && parseResult <= parseMaxPrice, "The prices are different");
                }
        return this;
    }

    public ListOfProductsPage checkingTheResolutionOfTV(String resolutionOfTv) {
        List<WebElement> resolutions = driver.findElements(descriptionOfProduct);
                for (int i = 0; i<resolutions.size();i++) {
                    String result = resolutions.get(i).getText();
                    System.out.println(result);
                    softAssert.assertTrue(result.contains(resolutionOfTv), "The resolutions are different ");
                }
        return this;
    }

    public ListOfProductsPage checkingSizeOfTV(String minSizeOfTV, String maxSizeOfTV) {
        List<WebElement> sizes = driver.findElements(descriptionOfProduct);
                for (int i = 0; i<sizes.size();i++) {
                    String result = sizes.get(i).getText();
                    String [] arrayResult = result.split("\"");
                    String sizeResult = arrayResult[0];
                    int parseResult = Integer.parseInt(sizeResult);
                    int parseMinSize = Integer.parseInt(minSizeOfTV);
                    int parseMaxSize = Integer.parseInt(maxSizeOfTV);
                    System.out.println(result);
                    softAssert.assertTrue(parseResult >= parseMinSize && parseResult <= parseMaxSize, "The sizes are different ");
                }
        return this;
    }
}



