package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private By upOrderBtn = By.xpath("//div[@class=\"Header_Nav__AGCXC\"]/button[@class=\"Button_Button__ra12g\" and text()=\"Заказать\"]");
    private By downOrderBtn = By.xpath("//div[@class=\"Home_FinishButton__1_cWm\"]/button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\" and text()=\"Заказать\"]");
    private By logoSamokat = By.xpath("//*[@alt='Scooter']");
    private By logoYandex = By.xpath("//*[@alt='Yandex']");
    protected String URLYandex = "https://ya.ru/";


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickUpOrderButton() {
        driver.findElement(upOrderBtn).click();
    }

    public void clickDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(downOrderBtn));
        driver.findElement(downOrderBtn).click();
    }

    /* ------------------------------*/

    // 1. Прокручиваем до блока «Вопросы о важном»
    public List<WebElement> findBlockQuestions() {
        WebElement element = driver.findElement(By.cssSelector(".Home_FourPart__1uthg"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        // 2. Находим все кнопки‑стрелочки (вопросы)
        return driver.findElements(
                By.className("accordion__button")
        );
    }

    /* ------------------------------*/

    public void clickLogoSamokat() {
        driver.findElement(logoSamokat).click();
    }

    /* ------------------------------*/

    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }
}
