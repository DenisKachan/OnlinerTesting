package onlinerTesting.tests;

import onlinerTesting.browserConfig.BrowserConfiguration;
import onlinerTesting.pages.CataloguePage;
import onlinerTesting.pages.ListOfProductsPage;
import onlinerTesting.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;
    protected BrowserConfiguration browserConfiguration;
    protected MainPage mainPage;
    protected CataloguePage cataloguePage;
    protected ListOfProductsPage listOfProductsPage;


    @BeforeMethod
    public void setUp() {
        browserConfiguration = BrowserConfiguration.getInstance();
        driver = browserConfiguration.chooseBrowser();
        browserConfiguration.browserSettings(driver);
        mainPage = new MainPage(driver);
        cataloguePage = new CataloguePage(driver);
        listOfProductsPage = new ListOfProductsPage(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
