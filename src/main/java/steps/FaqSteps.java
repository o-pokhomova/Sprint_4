package steps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.MainPage;

import java.util.List;

public class FaqSteps {
    private final WebDriverWait wait;
    private final MainPage mainPage;

    public FaqSteps(WebDriverWait wait, MainPage mainPage) {
        this.wait = wait;
        this.mainPage = mainPage;
    }

    public String getAnswer(WebElement button) {
        // Кликаем по стрелочке
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();

        // Ждём появления всех блоков с ответом
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(mainPage.getAnswerPanel()));
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
