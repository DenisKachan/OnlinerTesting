package onliner.browserConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import onliner.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BrowserConfiguration {

    PropertyReader configReader = new PropertyReader("config.properties");

    private static BrowserConfiguration instance;

    private BrowserConfiguration() {
    }

    public static BrowserConfiguration getInstance() {
        if (instance == null) {
            instance = new BrowserConfiguration();
        }
        return instance;
    }

    public WebDriver chooseBrowser() {
        switch (configReader.getProperty("browser")) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public void browserSettings(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperty("defaultWait")), TimeUnit.SECONDS);
    }

    public void openStartPage(WebDriver driver){
        driver.get(configReader.getProperty("baseUrl"));
    }
}
