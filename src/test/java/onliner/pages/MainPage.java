package onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    private final By loginButton = By.xpath("//input[@class='fast-search__input']");
    private final String commonLocatorForMainPageSection = "//a[@class='b-main-navigation__link']/descendant::span[text()='%s']";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(configReader.getProperty("webDriverWait")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginButton)));
        return this;
    }

    public MainPage navigateSection(String nameOfTheSection) {
        WebElement pageSection = driver.findElement(By.xpath(String.format(commonLocatorForMainPageSection, nameOfTheSection)));
        pageSection.click();
        return this;
    }
}
