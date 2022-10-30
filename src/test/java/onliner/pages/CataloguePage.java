package onliner.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CataloguePage extends BasePage {

    private final By cataloguePageTitle = By.className("catalog-navigation__title");
    private final String commonLocatorForCatalogueSection = "//ul[@class='catalog-navigation-classifier ']/descendant::span[text()='%s']";
    private final String commonLocatorForCatalogueSubSection = "//div[@class='catalog-navigation-list__aside-title'][contains(text(),'%s')]";
    private final String commonLocatorForSubSectionEntity = "//div[@class='catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active']//span[contains(text(),'%s')]";

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
        WebElement catalogueSection = driver.findElement(By.xpath(String.format(commonLocatorForCatalogueSection, nameOfTheSection)));
        catalogueSection.click();
        return this;
    }

    public CataloguePage navigateCatalogueSubSection(String nameOfTheSubSection) {
        WebElement catalogueSubSection = driver.findElement(By.xpath(String.format(commonLocatorForCatalogueSubSection, nameOfTheSubSection)));
        catalogueSubSection.click();
        return this;
    }

    public ListOfProductsPage navigateSubSectionEntity(String nameOfSubSectionEntity) {
       WebElement subSectionEntity =  driver.findElement(By.xpath(String.format(commonLocatorForSubSectionEntity, nameOfSubSectionEntity)));
       subSectionEntity.click();
       return new ListOfProductsPage(driver);
    }
}
