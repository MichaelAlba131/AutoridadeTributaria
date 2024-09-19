package page.login;

import functions.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    static WebDriver driver;
    public LoginPage() {
        driver = Driver.driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "mat-input-0")
    public static WebElement user_login;

    @FindBy(how = How.XPATH, using = "//input[contains(@class,'mat-input-element mat-form-field-autofill-control')]")
    public static WebElement email_login;

    @FindBy(how = How.ID, using = "mat-input-1")
    public static WebElement password_login;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Acessar')]//ancestor::button")
    public static WebElement acessar_btn;
}
