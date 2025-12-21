import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderMakeSteps;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderMakeTest extends BaseTest {
    public String name;
    public String lastName;
    public String address;
    public int station;
    public String phone;
    public String date;
    public int period;
    public String option;

    public OrderMakeTest(String browser,
                         String name,
                         String lastName,
                         String address,
                         int station,
                         String phone,
                         String date,
                         int period,
                         String option) {
        super(browser);
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.option = option;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowserSelection() {
        return new Object[][]{
                {"chrome", "Ольга", "Похомова", "Летчика Бабушкина 47", 3, "+70005553399", "16.12.2021", 3, "серая безысходность"},
                {"chrome", "Иван", "Александров", "Крылатское 4", 5, "+988883333444", "11.12.1987", 2, "чёрный жемчуг"},
                {"firefox", "Ольга", "Похомова", "Летчика Бабушкина 47", 3, "+70005553399", "16.12.2021", 3, "серая безысходность"},
                {"firefox", "Иван", "Александров", "Крылатское 4", 5, "+988883333444", "11.12.1987", 2, "чёрный жемчуг"}
        };
    }

    @Test
    public void successfulOrderUp() {
        OrderMakeSteps orderSteps = new OrderMakeSteps(orderMakePage);
        openPage();
        // нажать кнопку "Заказ"
        mainPage.clickUpOrderButton();
        orderSteps.firstStepForWhom(name, lastName, address, station, phone);
        orderSteps.secondaryStepAboutRental(date, period, option);
        //проверка перехода на модальное окно путем поиска специфического элемента
        assertTrue(
                "Переход на страницу подтверждения не выполнен",
                !orderMakePage.getList().isEmpty()
        );
    }

    @Test
    public void successfulOrderDown() {
        //Создаём экземпляр OrderMakeSteps с передачей orderMakePage
        OrderMakeSteps orderSteps = new OrderMakeSteps(orderMakePage);
        openPage();
        // нажать кнопку "Заказ"
        mainPage.clickDownOrderButton();
        orderSteps.firstStepForWhom(name, lastName, address, station, phone);
        orderSteps.secondaryStepAboutRental(date, period, option);
        //проверка перехода на модальное окно путем поиска специфического элемента
        assertTrue(
                "Переход на страницу подтверждения не выполнен",
                !orderMakePage.getList().isEmpty()
        );
    }
}