import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.FlightCheckInForFlight;
import pages.FlightFoundList;
import pages.FlightSearchPage;
import pages.FlightsLoginPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMCheckInForFlight {

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
    @DisplayName("POM-01. Переход к форме регистрации")
    void test01() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();
    }

    @Test
    @DisplayName("POM-02. Форма регистрации. Автозаполнение данных пользователя")
    void test02() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();

        checkInForFlight.checkUser("Иванов Иван Иванович", "1234 567890", "ivanov@example.com", "+7 (123) 456-7890");
    }

    @Test
    @DisplayName("POM-03. Форма регистрации. Проверка ФИО")
    void test03() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();

        checkInForFlight.checkNameMask();
    }

    @Test
    @DisplayName("POM-04. Форма регистрации. Проверка паспорта")
    void test04() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();

        checkInForFlight.checkPassport();
    }

    @Test
    @DisplayName("POM-05. Форма регистрации. Пустые поля")
    void test05() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();

        checkInForFlight.checkPassport();
    }

    @Test
    @DisplayName("POM-06. Пустое поле Имени")
    void test06() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();

        checkInForFlight.clearName();
    }

    @Test
    @DisplayName("POM-06_02. Пустое поле Паспорт")
    void test06_02() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();

        checkInForFlight.clearPassport();
    }

    @Test
    @DisplayName("POM-06_3. Форма с пустыми полями")
    void test07() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();

        checkInForFlight.clearField();
    }

    @Test
    @DisplayName("POM-08. Успешная регистрация")
    void test08() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();
        checkInForFlight.regButtonClick();
        switchTo().alert().accept();
    }

    @Test
    @DisplayName("POM-09. Вернуться к найденным билетам")
    void test09() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Москва", "Париж", "29-12-2025");
        searchPage.clickButtonReg();

        FlightCheckInForFlight checkInForFlight = new FlightCheckInForFlight();
        checkInForFlight.checkTitle();
        checkInForFlight.returnButtonClick();

        FlightFoundList foundList = new FlightFoundList();
        foundList.clickNewSearchButton();
    }
}

