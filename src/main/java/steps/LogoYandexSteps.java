package steps;

import page.MainPage;
import org.openqa.selenium.WebDriver;

public class LogoYandexSteps {

    private MainPage mainPage;

    public LogoYandexSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
    }

    public void checkLogoYandex() {
        mainPage.clickUpOrderButton();
        mainPage.clickLogoYandex();
    }


}


