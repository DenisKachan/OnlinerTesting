package onliner.tests;

import onliner.browserConfig.BrowserConfiguration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;
    protected BrowserConfiguration browserConfiguration;

    @BeforeMethod
    public void setUp() {
        browserConfiguration = BrowserConfiguration.getInstance();
        driver = browserConfiguration.chooseBrowser();
        browserConfiguration.browserSettings(driver);
        browserConfiguration.openStartPage(driver);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
