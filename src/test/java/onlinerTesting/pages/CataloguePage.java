package onlinerTesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CataloguePage extends BasePage {

    private final By cataloguePageTitle = By.className("catalog-navigation__title");

    private final By imageOfATVFromSubSection = By.xpath("//a[@href='https://catalog.onliner.by/tv']");

    public CataloguePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CataloguePage isPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(configReader.getProperty("webDriverWait")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(cataloguePageTitle)));
        return this;
    }

    public CataloguePage navigateCatalogueSection(String nameOfTheSection) {
        String commonLocatorForCatalogueSection = "//ul[@class='catalog-navigation-classifier ']/descendant::span[text()='%s']";
        driver.findElement(By.xpath(String.format(commonLocatorForCatalogueSection, nameOfTheSection))).click();
        return this;
    }

    public CataloguePage navigateCatalogueSubSection(String nameOfTheSubSection) {
        String commonLocatorForCatalogueSubSection = "//div[@class='catalog-navigation-list__aside-title'][contains(text(),'%s')]";
        driver.findElement(By.xpath(String.format(commonLocatorForCatalogueSubSection, nameOfTheSubSection))).click();
        return this;
    }

    public ListOfProductsPage clickTVImage() {
        driver.findElement(imageOfATVFromSubSection).click();
        return new ListOfProductsPage(driver);
    }
}
