import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.FlightSearchPage;
import pages.FlightsLoginPage;
import pages.FlightFoundList;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsTests {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());

    }

    @BeforeEach
    void setUp() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        getWebDriver().manage().window().maximize();
    }
    //Тест-кейсы

    @Test
    @DisplayName("POM-01. Выход из системы")
    void test01() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.logout();
    }

    @Test
    @DisplayName("POM-02. Не задана дата вылета")
    void test02() {
        FlightsLoginPage login_page = new FlightsLoginPage();

        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "");
        searchPage.verifyEmptyDate();
    }

    @Test
    @DisplayName("POM-03. Прошедшая дата вылета")
    void test03() {
        FlightsLoginPage login_page = new FlightsLoginPage();

        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "10-10-2025");
        searchPage.verifyPastDate();
    }

    @Test
    @DisplayName("POM-04. Отображение сообщения Рейсов не найдено")
    void test04() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlightsMonday();
        searchPage.checkZeroFlight();
    }

    @Test
    @DisplayName("POM-05. Отображение сообщения Рейсов не найдено")
    void test05() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");

        FlightFoundList foundList = new FlightFoundList();
        foundList.checkRegButton();
    }

    @Test
    @DisplayName("POM-06. Отображение найденных рейсов")
    void test06() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");

        FlightFoundList foundList = new FlightFoundList();
        foundList.checkRegButton();
        foundList.checkTrueFlights();
    }

    @Test
    @DisplayName("POM-07. Кнопка 'Новый поиск', отображение на странице")
    void test07() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");

        FlightFoundList foundList = new FlightFoundList();
        foundList.checkNewSearchButton();
    }

    @Test
    @DisplayName("POM-08. Кнопка 'Новый поиск', проверка клика")
    void test08() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");

        FlightFoundList foundList = new FlightFoundList();
        foundList.checkNewSearchButton();

        foundList.clickNewSearchButton();
        searchPage.checkValueSearchTickets("Москва", "Париж", "2025-12-29");
    }
}

