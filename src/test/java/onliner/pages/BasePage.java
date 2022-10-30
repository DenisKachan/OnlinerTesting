package onliner.pages;

import onliner.utils.PropertyReader;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected PropertyReader configReader = new PropertyReader("config.properties");

    public abstract BasePage isPageOpened();

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
