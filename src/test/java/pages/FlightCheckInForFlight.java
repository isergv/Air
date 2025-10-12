package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FlightCheckInForFlight {

    SelenideElement
            name = $("#passengerName"),
            passport = $("#passportNumber"),
            eMail = $("#email"),
            phone = $("#phone"),
            Message = $("#registrationMessage"),
            regButton = $x("//button[text()='Завершить регистрацию']"),
            titleH2 = $x("//h2[text()='Регистрация на рейс']");

    String
            errorUserNameLanguage = "ФИО должно содержать только русские буквы, пробелы и дефис.",
            errorClearName = "Пожалуйста, заполните все поля.",
            errorClearPassport = "Пожалуйста, заполните все поля.",
            errorClearFilter = "Пожалуйста, заполните все поля.",
            errorPassport = "Номер паспорта должен содержать только цифры и пробелы.";


    public void checkTitle() {
        Assertions.assertTrue(titleH2.isDisplayed());
    }

    public void titleH2() {
        titleH2.shouldHave(text("Регистрация на рейс"));
    }

    public void checkUser(String nameUser, String passportUser, String eMailUser, String phoneUser) {
        Assertions.assertEquals(name.getValue(), nameUser);
        Assertions.assertEquals(passport.getValue(), passportUser);
        Assertions.assertEquals(eMail.getValue(), eMailUser);
        Assertions.assertEquals(phone.getValue(), phoneUser);
    }

    public void checkNameMask() {
        name.setValue("Frodo");
        regButton.click();
        Message.shouldHave(text(errorUserNameLanguage));
    }

    public void checkPassport() {
        passport.setValue("Frodo");
        regButton.click();
        Message.shouldHave(text(errorPassport));
        sleep(1000);
    }

    public void clearName() {
        name.clear();
        regButton.click();
        Message.shouldHave(text(errorClearName));
    }

    public void clearPassport() {
        passport.clear();
        regButton.click();
        Message.shouldHave(text(errorClearPassport));
    }

    public void clearField() {
        name.clear();
        passport.clear();
        eMail.clear();
        phone.clear();
        regButton.click();
        sleep(1000);
        Message.shouldHave(text(errorClearFilter));
    }
}
