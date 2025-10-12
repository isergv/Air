package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FlightFoundList {

    SelenideElement
            firstRegButton = $x("//button[@class='register-btn']"),
            flightsCount = $x("//div[@id='flightsCount']"),
            regButton = $x("//button[text()='Зарегистрироваться']"),

            newSearchButton = $x("//button[@class='new-search-btn']"),
            message = $x("//td[text()='Рейсов по вашему запросу не найдено.']");

    ElementsCollection
            sizeFlight = $$x("//tr[contains(., 'Москва')]");

    String
            noFlight = ("Рейсов по вашему запросу не найдено."),
            zeroFlight = ("Найдено рейсов: 0");


    public void chooseFirstList() {
        firstRegButton.click();
    }

    public void verifyFoundFlights() {
        message.shouldHave(text(noFlight));
    }

    public void verifySuccessFullSearch() {
        flightsCount.shouldHave(text(zeroFlight));
    }

    public void checkRegButton() {
        Assertions.assertTrue(regButton.isDisplayed());
    }

    public void checkTrueFlights() {
        Assertions.assertEquals(2, sizeFlight.size());
    }

    public void checkNewSearchButton() {
        Assertions.assertTrue(newSearchButton.isDisplayed());
    }

    public void clickNewSearchButton() {
        newSearchButton.click();
        FlightSearchPage searchPage = new FlightSearchPage();
        Assertions.assertTrue(searchPage.titleH2.isDisplayed());
    }

}
