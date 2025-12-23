import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.LogoYandexSteps;

import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class LogoYandexTest extends BaseTest {
    public LogoYandexTest(String browser) {
        super(browser);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getBrowserSelection() {
        return new Object[][]{
                {"chrome"},
                {"firefox"}
        };
    }
    @Test
    public void successfulClickLogo() {
        LogoYandexSteps logoYandexSteps = new LogoYandexSteps(driver);

        openPage();
        logoYandexSteps.checkLogoYandex();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();
        String currentWindow = driver.getWindowHandle();
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                // Распространённая проблема FF:
                // при открытии вкладки url всегда about:blank.
                // потом браузер переключется на url ссылки, но без этой строочки мы не дожидаемся переключения
                wait.until(d -> !driver.getCurrentUrl().equals("about:blank"));
                // Дальше цепочка редиректов, нас интересует конечный url
                wait.until(d -> !driver.getCurrentUrl().contains("yandex.ru"));
                wait.until(d -> !driver.getCurrentUrl().contains("sso"));
                break;
            }
        }
        assertEquals("Переход на главную страницу Яндекса не выполнен", mainPage.getUrlYandex(), driver.getCurrentUrl());
    }
}

