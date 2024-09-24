package functions;

import configuration.classes.ExtractInformationYAML;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.util.HashMap;
import static configuration.classes.ExtractInformationYAML.GetInfoAmbient;
import static configuration.classes.ExtractInformationYAML.URLAmbient;

public class FunctionsHook {

    public static void SetNameSuite(Scenario scenario) {
        String suite = scenario.getSourceTagNames().stream().findFirst().toString().replace("Optional[@", "").replace("]", "");
        io.qameta.allure.Allure.suite(suite);
    }

    public static void setAllureInformations(Scenario scenario) {
        String feature_name = "Feature: " + scenario.getId().split("/")[scenario.getId().split("/").length - 1].split(":")[0].replace(".feature", "");
        io.qameta.allure.Allure.feature(feature_name);
        System.out.println(feature_name);
        System.out.println(scenario.getName());
        SetNameSuite(scenario);
    }

    public static WebDriver mountDriverType() throws IOException {
        String local_chrome = "";
        if (System.getProperty("os.name").toUpperCase().contains("MAC"))
            local_chrome = System.getProperty("user.dir") + "/driver/mac/chromedriver";
        else if (System.getProperty("os.name").toUpperCase().contains("LINUX"))
            local_chrome = System.getProperty("user.dir") + "/driver/linux/chromedriver";
        else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
            local_chrome = System.getProperty("user.dir") + "/driver/windows/chromedriver";
        System.setProperty("webdriver.chrome.driver", local_chrome);
        String downloadFilepath = System.getProperty("user.dir") + "/src/test/java/files/downloadCSV";
        String url = URLAmbient(String.valueOf(ExtractInformationYAML.TypeLogin.QUOTA2));
        url = url.replace("AMBIENT_YAML", GetInfoAmbient().getAmbient().toLowerCase());
        System.setProperty("http.proxyHost", url);
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("http.proxyUser", "XY29318");
        System.setProperty("http.proxyPassword", "Teste-29318%");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--verbose");
        optionsChrome.addArguments("--whitelisted-ips=''");
        optionsChrome.addArguments("--disable-popup-blocking");
        optionsChrome.addArguments("--disable-default-apps");
        optionsChrome.addArguments("test-type=browser");
        optionsChrome.addArguments("--ignore-certificate-errors");
        optionsChrome.addArguments("--remote-allow-origins=*");
        optionsChrome.addArguments("--proxy-server=\"direct://\"");
        optionsChrome.addArguments("--proxy-bypass-list=*");
        optionsChrome.addArguments("--start-maximized");
        optionsChrome.addArguments("--disable-dev-shm-usage");
        optionsChrome.addArguments("--disable-gpu");
        optionsChrome.setHeadless(false);
        optionsChrome.setExperimentalOption("prefs", chromePrefs);
        optionsChrome.setExperimentalOption("w3c", true);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, optionsChrome);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        optionsChrome.merge(capabilities);
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.merge(capabilities);
        Driver.driver = new ChromeDriver(optionsChrome);
        return Driver.driver;
    }

    public static boolean getFirstExecution() {
        return FunctionsHook.firstExecution;
    }

    public static void setFirstExecution(boolean firstExecution) {
        FunctionsHook.firstExecution = firstExecution;
    }

    static boolean firstExecution = true;

    public static void VerifyOpenConnectionFirstTest() throws Exception {
//        if (getFirstExecution()) {
//            Thread.sleep(1000);
//            AllureFunctions.allureEnvironmentWriter(
//                    ImmutableMap.<String, String>builder()
//                            .put("System Operation:", System.getProperty("os.name") + " | "
//                                    + System.getProperty("os.version") + " | "
//                                    + System.getProperty("os.arch"))
//                            .put("Automação:", GetInfoAmbient().getName())
//                            .put("Versão da Automação:", GetInfoAmbient().getVersion())
//                            .put("Navegador:", "Chrome")
//                            .put("Ambiente:", GetInfoAmbient().getAmbient())
//                            .build(), System.getProperty("user.dir")
//                            + "/target/allure-results/");
//            setFirstExecution(false);
//        } else {
//            System.out.println("Conexão com Banco de Dados reutilizada!");
//        }
    }

    public static void CloseDriver(Scenario scenario) throws IOException {
        try {
            //AllureFunctions.saveScreenshot();
            Driver.driver.close();
            Driver.driver.quit();
        } catch (Exception ignored) {
        }
    }
}
