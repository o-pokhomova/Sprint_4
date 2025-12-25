package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class MainPage {
    public static String URL = "https://qa-scooter.praktikum-services.ru/";

    private WebDriver driver;
    private WebDriverWait wait;

    protected String urlYandex = "https://ya.ru/";
    private By acceptBtn = By.id("rcc-confirm-button");
    private By answerPanel = By.xpath(".//div[@class='accordion__panel']/p");
    private By upOrderBtn = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private By downOrderBtn = By.xpath(".//button[contains(@class, 'Button_Middle') and text() = 'Заказать']");
    private By logoSamokat = By.xpath(".//*[@alt='Scooter']");
    private By logoYandex = By.xpath(".//*[@alt='Yandex']");
    private final String scrollIntoViewScript = "arguments[0].scrollIntoView();";
    private final By questionsAndAnswersBlock = By.cssSelector(".Home_FourPart__1uthg");
    private String answerPanelByQuestionTextTemplate = ".//*[contains(@class, 'accordion__button') and normalize-space(text()) = '%s']";

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickUpOrderButton() {
        driver.findElement(upOrderBtn).click();
    }

    public void clickDownOrderButton() {
        WebElement button = driver.findElement(downOrderBtn);
        scrollDown(button);
        button.click();
    }

    /* ------------------------------*/

    // 1. Прокручиваем до блока «Вопросы о важном»
    public WebElement findAnswerByQuestion(String question) {
        WebElement element = driver.findElement(questionsAndAnswersBlock);
        scrollDown(element);

        // 2. Находим кнопку-стрелочку
        return element.findElement(
                By.xpath(
                        String.format(answerPanelByQuestionTextTemplate, question)
                )
        );
    }

    private void scrollDown(WebElement element) {
        wait.until(d -> {
            ((JavascriptExecutor) d).executeScript(scrollIntoViewScript, element);
            return element.isDisplayed();
        });
    }

    /* ------------------------------*/

    public void clickLogoSamokat() {
        driver.findElement(logoSamokat).click();
    }

    /* ------------------------------*/

    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }

    public String getUrlOpenedInSecondTab() {
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
        return driver.getCurrentUrl();
    }

    public void acceptCookies() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(getAcceptBtn()).click();
    }

    public void open() {
        driver.get(URL);
    }

    public void clickButton(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public List<WebElement> waitForAnswersPanel() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getAnswerPanel()));
    }

    public By getAnswerPanel() {
        return answerPanel;
    }

    public String getUrlYandex() {
        return urlYandex;
    }

    public By getAcceptBtn() {
        return acceptBtn;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
