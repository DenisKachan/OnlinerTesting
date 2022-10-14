package Task2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CataloguePage extends BasePage {

    @FindBy(className = "catalog-navigation__title")
    WebElement cataloguePageTitle;

    @FindBy(xpath = "//a[@href='https://catalog.onliner.by/tv']")
    WebElement imageOfATVFromSubSection;


    private final String commonLocatorForCatalogueSection = "//ul[@class='catalog-navigation-classifier ']/descendant::span[text()='%s']";
    private final String commonLocatorForCatalogueSubSection = "//div[@class='catalog-navigation-list__aside-title'][contains(text(),'%s')]";

    private WebElement catalogueSection(String nameOfTheSection) {
        return driver.findElement(By.xpath(String.format(commonLocatorForCatalogueSection, nameOfTheSection)));
    }

    private WebElement catalogueSubSection(String nameOfTheSubSection) {
        return driver.findElement(By.xpath(String.format(commonLocatorForCatalogueSubSection, nameOfTheSubSection)));
    }

    public CataloguePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public CataloguePage isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(cataloguePageTitle));
        return this;
    }

    public CataloguePage navigateCatalogueSection(String nameOfTheSection) {
        catalogueSection(nameOfTheSection).click();
        return this;
    }

    public CataloguePage navigateCatalogueSubSection(String nameOfTheSubsection) {
        catalogueSubSection(nameOfTheSubsection).click();
        return this;
    }

    public ListOfProductsPage clickTVImage() {
        imageOfATVFromSubSection.click();
        return new ListOfProductsPage(driver);
    }

}
