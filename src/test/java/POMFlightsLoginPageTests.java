import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.FlightSearchPage;
import pages.FlightsLoginPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsLoginPageTests {

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
    @DisplayName("POM-01. Успешный логин")
    void test01() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "stand_pass1");
        login_page.verify_successful_login();

        FlightSearchPage searchPage = new FlightSearchPage();
        searchPage.checkUserName();
    }

    @Test
    @DisplayName("POM-02. Неверный пароль")
    void test02() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "wrong_stand_pass1");
        login_page.verify_wrong_username_or_password();
    }

    @Test
    @DisplayName("POM-03. Авторизация без имени пользователя")
    void test03() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("", "stand_pass1");
        login_page.verify_no_username();
    }

    @Test
    @DisplayName("POM-04. Авторизация без пароля")
    void test04() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("standard_user", "");
        login_page.verify_no_password();
    }

    @Test
    @DisplayName("POM-05. Неверное имя, верный пароль")
    void test05() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("Error_standard_user", "stand_pass1");
        login_page.verify_wrong_username_or_password();
    }

    @Test
    @DisplayName("POM-06. Заблокированный пользователь")
    void test06() {
        FlightsLoginPage login_page = new FlightsLoginPage();
        login_page.login("locked_out_user", "lock_pass2");
        login_page.verify_block_user();
    }
}

