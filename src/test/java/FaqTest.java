import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import steps.FaqSteps;
import steps.QuestionAndAnswer;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {
    private static final String[][] EXPECTED_QA = {
            {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {"Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {"Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {"Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {"Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {"Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."}
    };


    @Parameterized.Parameters
    public static Object[][] getBrowserSelection() {
        return new Object[][]{
                {"chrome"},
                {"firefox"}
        };
    }

    private FaqSteps faqSteps;

    public FaqTest(String browser) {
        super(browser);
    }

    @Before
    public void setUp() {
        faqSteps = new FaqSteps(wait);
    }

    @Test
    public void QuestionsAccordion() {
        // Открыли сайт
        openPage();
        // получили список вопросов
        List<WebElement> questionButtons = mainPage.findBlockQuestions();
        // 3. Для каждого блока вопроса:
        for (int i = 0; i < questionButtons.size(); i++) {
            // 3.1 Преобразуем блок в пару вопрос-ответ.
            QuestionAndAnswer actual = faqSteps.getQuestionAndAnswer(questionButtons.get(i));
            String expectedQuestion = EXPECTED_QA[i][0];
            String expectedAnswer = EXPECTED_QA[i][1];
            // сравниваем эталонный и полученный результаты
            assertNotNull("Block not found", actual);
            assertEquals(
                    "",
                    expectedQuestion,
                    actual.question
            );
            assertEquals(
                    "Ответ на вопрос '" + actual.question + "' не совпадает.",
                    expectedAnswer,
                    actual.answer
            );
        }
    }
}