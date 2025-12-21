import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.LogoYandexSteps;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class LogoYandexTest extends BaseTest {
    private long URLYandex;

    public LogoYandexTest(String browser) {
        super(browser);
    }

    @Parameterized.Parameters
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
        assertEquals("Переход на главную страницу Яндекса не выполнен", URLYandex, driver.getCurrentUrl());
    }
}

