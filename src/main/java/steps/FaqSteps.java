package steps;

import org.openqa.selenium.WebElement;
import page.MainPage;

import java.util.List;

public class FaqSteps {
    private final MainPage mainPage;

    public FaqSteps(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public String getAnswer(WebElement button) {
        // Кликаем по стрелочке
        mainPage.clickButton(button);

        // Ждём появления всех блоков с ответом
        List<WebElement> elements = mainPage.waitForAnswersPanel();
        // answer пока еще ничего не нашли
        WebElement answer = null;
        // перебираем, пока не найдем видимый.
        for (WebElement webElement : elements) {
            if (webElement.isDisplayed()) {
                answer = webElement;
                break;
            }
        }
        if (answer == null) {
            return null;
        }
        return answer.getText().trim();
    }
}
