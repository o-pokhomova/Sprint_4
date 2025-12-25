import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.MainPage;
import steps.LogoSamokatSteps;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LogoSamokatTest extends BaseTest {
    public LogoSamokatTest(String browser) {
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
        LogoSamokatSteps logoSamokatSteps = new LogoSamokatSteps(mainPage);

        openPage();
        assertEquals(
                "Переход на главную страницу сайта не выполнен",
                MainPage.URL,
                logoSamokatSteps.checkLogoSamokat()
        );
    }
}
