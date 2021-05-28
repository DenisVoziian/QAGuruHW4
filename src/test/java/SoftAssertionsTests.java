import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTests {
    private String selenidePage = "/selenide/selenide";

    private String searchPage = "SoftAssertions",
            searchDataOnPage = "JUnit5";

    private SelenideElement wikiLink = $("[data-content=Wiki]"),
    searchPageInput = $("#wiki-pages-filter"),
    searchPagesResult = $("[data-filterable-for=wiki-pages-filter]"),
    foundPageLink = $("[data-filterable-for=wiki-pages-filter]").find(By.linkText(searchPage)),
    wikiBody = $("#wiki-body");

    @BeforeAll
    private static void setUp() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void checkSoftAssertions() {
//  - Откройте страниц Selenide в Github
        open(selenidePage);

//  - Перейдите в раздел Wiki проекта
        wikiLink.click();

//  - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        searchPageInput.setValue(searchPage);
        searchPagesResult.shouldHave(text(searchPage));

//  - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        foundPageLink.click();
        wikiBody.shouldHave(text(searchDataOnPage));
    }
}
