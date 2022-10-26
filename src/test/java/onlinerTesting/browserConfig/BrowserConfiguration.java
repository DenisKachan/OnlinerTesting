package onlinerTesting.browserConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import onlinerTesting.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        if ("chrome".equals(configReader.getProperty("browser"))) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        throw new IllegalArgumentException();
    }

    public void browserSettings(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configReader.getProperty("defaultWait")), TimeUnit.SECONDS);
        driver.get(configReader.getProperty("baseUrl"));
    }
}
