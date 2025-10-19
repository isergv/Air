package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class FlightSearchPage {

    SelenideElement
            fromCity = $("#departureCity"),
            toCity = $("#arrivalCity"),
            flightDate = $("#departureDate"),
            logoutButton = $("#logoutButton"),
            findButton = $x("//button[text()='Найти']"),
            buttonReg = $x("//button[text()='Зарегистрироваться']"),
            titleH2 = $x("//h2[text()='Поиск авиабилетов']"),
            message = $("#searchMessage"),
            zeroFlight = $x("//td[text()='Рейсов по вашему запросу не найдено.']"),

            userName = $x("//span[text()='Добро пожаловать, Иванов Иван Иванович!']");

    ElementsCollection
            sizeFlight = $$x("//tr[contains(., 'Москва')]");

    String
            errorMessageEmptyDate = "Пожалуйста, укажите дату вылета.",
            errorMessagePastDate = "Невозможно осуществить поиск: выбранная дата уже прошла.";


    public void findFlights(String from, String to, String date) {
        fromCity.selectOption(from);
        toCity.selectOption(to);
        flightDate.setValue(date);
        screenshot("123");
        findButton.click();
    }

    public void findFlightsMonday() {
        //Проверить отображение сообщения "Рейсов не найдено" (Казань - Париж, понедельник)
        fromCity.selectOption("Казань");
        toCity.selectOption("Париж");
        flightDate.setValue("29-12-2025");
        findButton.click();
    }

    public void logout() {
        logoutButton.click();

        FlightsLoginPage loginPage = new FlightsLoginPage();
        loginPage.AuthText.shouldHave(text("Аутентификация"));
    }

    public void verifyEmptyDate() {
        message.shouldHave(text(errorMessageEmptyDate));
    }

    public void verifyPastDate() {
        message.shouldHave(text(errorMessagePastDate));
    }

    public void checkZeroFlight() {
        Assertions.assertTrue(zeroFlight.isDisplayed());
    }

    public void checkUserName() {
        userName.shouldHave(text("Добро пожаловать, Иванов Иван Иванович!"));
    }

    public void checkValueSearchTickets(String from, String to, String date) {
        fromCity.shouldHave(text(from));
        toCity.shouldHave(text(to));
        flightDate.shouldHave(value(date));
    }

    public void clickButtonReg() {
        buttonReg.click();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.titleH2();
    }
}
