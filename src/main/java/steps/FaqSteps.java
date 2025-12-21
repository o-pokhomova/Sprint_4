package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class FaqSteps {
    private final WebDriverWait wait;

    public FaqSteps(WebDriverWait wait) {
        this.wait = wait;
    }

    public QuestionAndAnswer getQuestionAndAnswer(WebElement button) {
        String actualQuestion = button.getText().trim();

        // Кликаем по стрелочке
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();

        // Ждём появления всех блоков с ответом
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='accordion__panel']/p")
        ));
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
        return new QuestionAndAnswer(actualQuestion, answer.getText().trim());
    }
}
