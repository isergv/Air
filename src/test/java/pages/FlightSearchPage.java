package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightSearchPage {

    SelenideElement
            fromCity = $("#departureCity"),
            toCity = $("#arrivalCity"),
            flightDate = $("#departureDate"),
            findButton = $x("//button[text()='Найти']"),
            message = $("#searchMessage");

    String
            errorMessageEmptyDate = "Пожалуйста, укажите дату вылета.";

    public void findFlights(String from, String to, String date) {
        fromCity.selectOption(from);
        toCity.selectOption(to);
        flightDate.setValue(date);
        findButton.click();
    }

    public void verifyEmptyDate() {
        message.shouldHave(text(errorMessageEmptyDate));
    }
}
