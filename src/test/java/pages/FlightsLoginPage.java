package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightsLoginPage {
    SelenideElement Auth, Username, Password, LoginButton, Message, AuthText;

    public FlightsLoginPage() {
        Auth = $("div[id=loginContainer]");
        Username = $("#username");
        Password = $("#password");
        LoginButton = $("#loginButton");
        Message = $("#message");
        AuthText = $x("//h2[text()='Аутентификация']");
    }

    public void login(String username, String password) {
        Username.setValue(username);
        Password.setValue(password);
        LoginButton.click();
    }

    public void verify_successful_login() {
        Message.shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
    }

    public void verify_wrong_username_or_password() {
        Message.shouldHave(text("Неверное имя пользователя или пароль."));
    }

    public void verify_no_username_and_password() {
        Message.shouldHave(text("Username is required."));
    }

    public void verify_no_username() {
        Message.shouldHave(text("Username is required."));
    }

    public void verify_no_password() {
        Message.shouldHave(text("Password is required."));
    }

    public void verify_block_user() {
        Message.shouldHave(text("Пользователь заблокирован."));
    }

}
