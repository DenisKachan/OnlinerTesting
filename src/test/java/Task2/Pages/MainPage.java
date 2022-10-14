package Task2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    @FindBy(xpath = "//input[@class='fast-search__input']")
    WebElement loginButton;

    private final String commonLocatorForMainPageSection = "//a[@class='b-main-navigation__link']/descendant::span[text()='%s']";

    private WebElement mainPageSection(String nameOfTheSection) {
        return driver.findElement(By.xpath(String.format(commonLocatorForMainPageSection, nameOfTheSection)));
    }

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public MainPage isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    public MainPage navigateSection(String nameOfTheSection) {
        mainPageSection(nameOfTheSection).click();
        return this;
    }

}
