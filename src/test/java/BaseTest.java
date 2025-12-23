import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.MainPage;
import page.OrderMakePage;

import java.time.Duration;

public class BaseTest {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected String URL = "https://qa-scooter.praktikum-services.ru/";
    private final String browser;

    MainPage mainPage;
    OrderMakePage orderMakePage;

    public BaseTest(String browser) {
        this.browser = browser;
    }

    @Before
    public void start() {
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();
        }
        mainPage = new MainPage(driver);
        orderMakePage = new OrderMakePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void startBrowserChrome() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }

    public void startBrowserFirefox() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
    }

    @After
    public void teardown() {
        //Закрыть браузер
        if (driver != null) {
            driver.quit();
        }
    }

    public void openPage() {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(mainPage.getAcceptBtn()).click();
    }
}
