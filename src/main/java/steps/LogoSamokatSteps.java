package steps;

import page.MainPage;

public class LogoSamokatSteps {

    private MainPage mainPage;

    public LogoSamokatSteps(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public String checkLogoSamokat() {
        mainPage.clickUpOrderButton();
        mainPage.clickLogoSamokat();
        return mainPage.getCurrentUrl();
    }
}


