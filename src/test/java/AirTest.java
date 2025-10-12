import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AirTest {

    @Test
    void test00() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        $("#username").setValue("standard_user");
        $("#password").setValue("stand_pass1");
        $("#loginButton").click();
        $("#greeting").shouldBe(visible);
        String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
        System.out.println("------- " + date);
        $("#departureDate").clear();
        $("#departureDate").setValue(date);
        $x("//button[text()='Найти']").click();
        $x("//button[text()='Новый поиск']").click();
    }

    @Test
    void test01() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        $("#username").setValue("standard_user");
        $("#password").setValue("lock_pass2");
        $("#loginButton").click();
        $x("//div[text()='Новый поиск']").shouldBe();
    }

    @Test
    void test02() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        $("#username").setValue("standard_user");
        $("#password").setValue("lock_pass2");
        $("#loginButton").click();
        $("body").shouldHave(text("Неверное имя пользователя или пароль."));
    }

    @Test
    void test03() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        $("#username").setValue("standard_user");
        $("#password").setValue("stand_pass1");
        $("#loginButton").click();
        $("#greeting").shouldBe(visible);
        String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
        System.out.println("------- " + date);
        $("#departureDate").clear();

        $x("//button[text()='Найти']").click();
        $x("//div[text()='Пожалуйста, укажите дату вылета.']").shouldBe();
    }

    @Test
    void test04() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        $("#username").setValue("standard_user");
        $("#password").setValue("stand_pass1");
        $("#loginButton").click();
        $("#greeting").shouldBe(visible);
        String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
        System.out.println("------- " + date);

        $("#departureCity").selectOption("Казань");
        $("#arrivalCity").selectOption("Париж");
        $("#departureDate").setValue("13.10.2025");

        $x("//button[text()='Найти']").click();
        $x("//td[text()='Рейсов по вашему запросу не найдено.']").shouldBe();
    }

    @Test
    void test05() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        $("#username").setValue("standard_user");
        $("#password").setValue("stand_pass1");
        $("#loginButton").click();
        $("#greeting").shouldBe(visible);
        String date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
        System.out.println("------- " + date);

        $("#departureCity").selectOption("Москва");
        $("#arrivalCity").selectOption("Нью-Йорк");
        $("#departureDate").setValue("13.10.2025");

        $x("//button[text()='Найти']").click();
        $x("//button[text()='Зарегистрироваться']").click();
        $x("//input[@id='passengerName']").clear();
        $x("//button[text()='Завершить регистрацию']").click();

        $x("//div[text()='Пожалуйста, заполните все поля.']").shouldBe();

       // sleep(5000);
    }

}
