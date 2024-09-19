package interactions.login;

import configuration.classes.ExtractInformationYAML;
import functions.Driver;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import page.login.LoginPage;

import java.io.IOException;

import static configuration.classes.ExtractInformationYAML.GetInfoAmbient;
import static configuration.classes.ExtractInformationYAML.URLAmbient;
import static functions.Functions.SmartField;

public class LoginInteractions extends LoginPage {
    static WebDriver driver;

    public LoginInteractions() {
        driver = Driver.driver;
        PageFactory.initElements(driver, this);
    }

    public LoginInteractions action() {
        return new LoginInteractions();
    }


    /**
     * Navegar para a URL da Veragi
     */
    public static void OpenUrl() throws IOException {
        String url = URLAmbient(String.valueOf(ExtractInformationYAML.TypeLogin.SIGIP));
        url = url.replace("AMBIENT_YAML", GetInfoAmbient().getAmbient().toLowerCase());
        driver.navigate().to(url);
        Allure.step("Navegar para Url:" + url);
    }

    /**
     * Preencher o usuário
     *
     * @param user Usuario
     */
    public LoginInteractions FillUser(String user) throws InterruptedException {
        SmartField(null, user_login, user, 40);
        Allure.step("Preencher usuário: " + user);
        return new LoginInteractions();
    }

    /**
     * Preencher senha
     *
     * @param password Senha
     */
    public LoginInteractions FillPassword(String password) throws InterruptedException {
        SmartField(null, password_login, password, 20);
        Allure.step("Preencher senha: " + password);
        return new LoginInteractions();
    }

    /**
     * Clicar em acessar
     */
    public LoginInteractions ClickAcessar() throws InterruptedException {
        Thread.sleep(2000);

        if (acessar_btn.getAttribute("ng-reflect-disabled").equals("false")) {
            SmartField(null, acessar_btn, null, 20);
            Allure.step("Clicar no botão Acessar!");
            Thread.sleep(300);
        }
        return new LoginInteractions();
    }


}
