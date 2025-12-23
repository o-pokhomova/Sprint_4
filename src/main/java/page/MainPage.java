package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private WebDriver driver;

    protected String urlYandex = "https://ya.ru/";
    private By acceptBtn = By.id("rcc-confirm-button");
    private By answerPanel = By.xpath("//div[@class='accordion__panel']/p");
    private By upOrderBtn = By.xpath("//div[@class=\"Header_Nav__AGCXC\"]/button[@class=\"Button_Button__ra12g\" and text()=\"Заказать\"]");
    private By downOrderBtn = By.xpath("//div[@class=\"Home_FinishButton__1_cWm\"]/button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\" and text()=\"Заказать\"]");
    private By logoSamokat = By.xpath("//*[@alt='Scooter']");
    private By logoYandex = By.xpath("//*[@alt='Yandex']");
    private final String scrollIntoViewScript = "arguments[0].scrollIntoView();";
    private final By questionsAndAnswersBlock = By.cssSelector(".Home_FourPart__1uthg");
    private String answerPanelByQuestionTextTemplate = ".//*[contains(@class, 'accordion__button') and normalize-space(text()) = '%s']";


    public MainPage(WebDriver driver) {
        this.driver = driver;
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
        ((JavascriptExecutor) driver).executeScript(scrollIntoViewScript, element);
    }

    /* ------------------------------*/

    public void clickLogoSamokat() {
        driver.findElement(logoSamokat).click();
    }

    /* ------------------------------*/

    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
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
}
