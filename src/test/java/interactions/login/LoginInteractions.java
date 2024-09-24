package interactions.login;

import configuration.classes.ExtractInformationYAML;
import functions.Driver;
import io.qameta.allure.Allure;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import page.login.LoginPage;

import java.io.IOException;

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
        driver.navigate().to("https://www.google.com.br");
        Allure.step("Navegar para Url:" + url);
    }

    /**
     * Preencher o usuário
     *
     * @param user Usuario
     * @param password Senha
     */
    public LoginInteractions FillUserAndPassword(String user,String password) throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(user);
        Allure.step("Preencher usuário: " + user);
        alert.sendKeys(password);
        Allure.step("Preencher senha: " + user);
        alert.accept();
        Allure.step("Clicar em logar");
        return new LoginInteractions();
    }

}
