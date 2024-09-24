package page.login;

import functions.Driver;
import functions.Functions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Functions {
    static WebDriver driver;
    public LoginPage() {
        driver = Driver.driver;
        PageFactory.initElements(driver, this);
    }
}
