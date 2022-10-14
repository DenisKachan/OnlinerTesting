package Task2.BrowserConfig;

import Task2.Utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BrowserConfiguration {

    private static BrowserConfiguration instance;

    private BrowserConfiguration() {
    }

    public static BrowserConfiguration getInstance() {
        if (instance == null) {
            instance = new BrowserConfiguration();
        }
        return instance;
    }

    PropertyReader propertyReader = new PropertyReader();

    public WebDriver chooseBrowser() {
        switch (propertyReader.getProperty("browser")) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                return new ChromeDriver();
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                return new FirefoxDriver();
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public void browserSettings(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(propertyReader.getProperty("defaultWait")), TimeUnit.SECONDS);
        driver.get(propertyReader.getProperty("baseUrl"));
    }
}
