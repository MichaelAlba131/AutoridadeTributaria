package interactions.login;

import configuration.classes.ExtractInformationYAML;
import functions.Driver;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.Headers;
import org.openqa.selenium.support.PageFactory;
import page.login.LoginPage;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        String url = URLAmbient(String.valueOf(ExtractInformationYAML.TypeLogin.QUOTA2));
        url = url.replace("AMBIENT_YAML", GetInfoAmbient().getAmbient().toLowerCase());
        driver.navigate().to(url);
        Allure.step("Navegar para Url:" + url);
    }

    /**
     * Preencher o usuário
     *
     * @param user Usuario
     * @param password Senha
     */
    public LoginInteractions FillUserAndPassword(String user,String password) throws InterruptedException, IOException {
        String url = URLAmbient(String.valueOf(ExtractInformationYAML.TypeLogin.QUOTA2));
        url = url.replace("AMBIENT_YAML", GetInfoAmbient().getAmbient().toLowerCase());
        DevTools devTools = ((HasDevTools)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.<Integer>empty(), Optional.<Integer>empty(), Optional.<Integer>empty()));
        String auth = user + ":" + password;
        String encodeToString = Base64.getEncoder().encodeToString(auth.getBytes());
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("Authorization", "Basic " + encodeToString);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
        driver.get(url);
        return new LoginInteractions();
    }
}