package steps;

import page.MainPage;
import org.openqa.selenium.WebDriver;

public class LogoSamokatSteps {

    private MainPage mainPage;

    public LogoSamokatSteps(WebDriver driver) {
        this.mainPage = new MainPage(driver);
    }

    public void checkLogoSamokat() {
        mainPage.clickUpOrderButton();
        mainPage.clickLogoSamokat();
    }
}


