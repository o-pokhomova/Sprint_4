import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import steps.FaqSteps;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {
    @Parameterized.Parameters(name = "{0}:  Вопрос: {1}")
    public static Object[][] getBrowserSelection() {
        List<String> browsers = List.of("chrome", "firefox");
        List<String[]> questionAndAnswerPairs = List.of(
                new String[]{"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                new String[]{"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                new String[]{"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                new String[]{"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                new String[]{"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                new String[]{"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                new String[]{"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                new String[]{"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        );
        List<String[]> result = new ArrayList<>();

        for (String browser : browsers) {
            for (String[] questionAndAnswer : questionAndAnswerPairs) {
                result.add(
                        new String[]{
                                browser,
                                questionAndAnswer[0],
                                questionAndAnswer[1]
                        }
                );
            }
        }
        return result.toArray(String[][]::new);
    }

    private FaqSteps faqSteps;
    private final String question;
    private final String expectedAnswer;

    public FaqTest(String browser, String question, String expectedAnswer) {
        super(browser);
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    @Before
    public void setUp() {
        faqSteps = new FaqSteps(wait, mainPage);
    }

    @Test
    public void questionsAccordion() {
        // Открыли сайт
        openPage();
        // получили список вопросов
        WebElement questionAnswerBlock = mainPage.findAnswerByQuestion(question);
        assertNotNull("Block for question '" + question + "' not found", questionAnswerBlock);

        String actualAnswer = faqSteps.getAnswer(questionAnswerBlock);
        assertEquals(
                "Ответ на вопрос '" + question + "' не совпадает.",
                expectedAnswer,
                actualAnswer
        );
    }
}
