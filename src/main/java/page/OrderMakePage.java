package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderMakePage {

    private WebDriver driver;
    private By confirmActionBtn = By.xpath("//button[text()='Да']");
    private By rentalPeriod = By.className("Dropdown-option");
    private By clkRentalField = By.className("Dropdown-placeholder");
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By nextBtn = By.xpath("//button[text()=\"Далее\"]");
    private By number = By.cssSelector("input[placeholder=\"* Телефон: на него позвонит курьер\"]");
    private By metroField = By.xpath(".//input[@placeholder='* Станция метро']");

    private By addressField = By.cssSelector("input[placeholder=\"* Адрес: куда привезти заказ\"]");
    private By blackCheck = By.xpath("//label[text()='чёрный жемчуг']/input[@type='checkbox']");
    private By greyCheck = By.xpath("//label[text()='серая безысходность']/input[@type='checkbox']");
    private By lastNameField = By.cssSelector("input[placeholder=\"* Фамилия\"]");
    private By firstNameField = By.cssSelector("input[placeholder=\"* Имя\"]");
    private By orderBtn = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private By controlElement = By.xpath("//div[text()='Заказ оформлен']");
    private By allStation = By.xpath("//div[@class=\"select-search__select\"]//li");

    public OrderMakePage(WebDriver driver) {
        this.driver = driver;
    }

    // Ввести в поле имя
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    //ВВести в поле фамилию
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    // Ввести адрес
    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    // Кликнуть по полю станции
    public void clickMetroField() {
        driver.findElement(metroField).click();
        ;
    }

    // Выбрать станцию
    public void selectStation(int metro) {
        List<WebElement> elements = driver.findElements(allStation);
        elements.get(metro - 1).click();  // Выбор станции метро по индексу
    }

    // ввести номер
    public void enterNumber(String phoneNumber) {
        driver.findElement(number).sendKeys(phoneNumber);
    }

    // нажать далее для перехода на следующее окно
    public void clickNextButton() {
        driver.findElement(nextBtn).click();
    }

    //выбрать дату
    public void selectDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(dateField).sendKeys(Keys.RETURN);
    }

    //кликнуть на поле аренды
    public void clickRentalField() {
        driver.findElement(clkRentalField).click();
    }

    // выбрать срок аренды
    public void selectRentalPeriod(int rentalDays) {
        List<WebElement> elements = driver.findElements(rentalPeriod);
        elements.get(rentalDays - 1).click();  // Выбор срока аренды
    }

    // выбрать параметр самоката
    public void selectScooterOption(String color) {
        if ("чёрный жемчуг".equals(color)) {
            driver.findElement(blackCheck).click();
        } else if ("серая безысходность".equals(color)) {
            driver.findElement(greyCheck).click();
        }
    }

    // нажать кнопку "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderBtn).click();
    }

    // подтвердить
    public void confirmAction() {
        driver.findElement(confirmActionBtn).click();
    }

    //проверка перехода на модальное окно путем поиска специфического элемента
    public List<WebElement> getList() {
        List<WebElement> orderError = driver.findElements(controlElement);
        return orderError;
    }
}
