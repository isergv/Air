package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightFoundList {

    SelenideElement
            firstRegButton = $x("//button[@class='register-btn']"),
            flightsCount = $x("//div[@id='flightsCount']"),
            message = $x("//td[text()='Рейсов по вашему запросу не найдено.']");


    public void chooseFirstList() {
        firstRegButton.click();
    }

    public void verifyFoundFlights() {
        message.shouldHave(text("Рейсов по вашему запросу не найдено."));
    }

    public void verifySuccessFullSearch() {
        flightsCount.shouldNotHave(text("Найдено рейсов: 0"));
    }

}
