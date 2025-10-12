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
    @DisplayName("POM-01. Неуспешный логин")
    void test01() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "wrong_stand_pass1");
        login_page.verify_wrong_username_or_password();
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
    @DisplayName("POM-03. Не найдены рейсы")
    void test03() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.findFlights("Казань", "Париж", "15-12-2025");
        FlightFoundList foundFlights = new FlightFoundList();
        foundFlights.verifyFoundFlights();
        foundFlights.verifySuccessFullSearch();


    }
}

