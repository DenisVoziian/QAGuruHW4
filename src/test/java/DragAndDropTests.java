import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTests {
    private String dragAndDropUrl = "/drag_and_drop";

    private String firstText = "A",
            secondText = "B",
            headerText = "Drag and Drop";

    private SelenideElement boxA = $("#column-a"),
            boxB = $("#column-b"),
            headerPage = $("h3");

    @BeforeAll
    private static void setUp() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
    }

    @Test
    void dragAndDropTest() {
//        Откройте https://the-internet.herokuapp.com/drag_and_drop
        open(dragAndDropUrl);
        headerPage.shouldHave(text(headerText));
        boxA.shouldHave(text(firstText));
        boxB.shouldHave(text(secondText));

//        - Перенесите прямоугольник А на место В
        boxA.dragAndDropTo(boxB);

//        - Проверьте, что прямоугольники действительно поменялись
        boxA.shouldHave(text(secondText));
        boxB.shouldHave(text(firstText));
    }
}
