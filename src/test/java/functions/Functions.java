package functions;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Functions extends Driver{

    static WebDriver driver;

    public Functions() {
        driver = Driver.driver;
        PageFactory.initElements(driver, this);
    }

    public static void WaitForElement(By element_) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(element_));
    }

    /**
     * Checks if the element appears on the screen
     *
     * @param type      Selenium By. (XPATH;ID;NAME;ETC) if send an element, null value
     * @param element   Selenium WebElement (TYPE = NULL)
     * @param mandatory (boolean) if the element is required to appear
     * @param attempts  (int) number of attempts to find the element
     * @return boolean value (if find = true else find = false)
     */
    public static boolean CheckExistingObject(By type, WebElement element, Boolean mandatory, int attempts) throws InterruptedException {
        for (int i = 0; i < attempts; i++) {
            try {
                if (element != null) {
                    if (element.isDisplayed() && element.isEnabled())
                        return true;
                } else if (driver.findElement(type).isDisplayed() && driver.findElement(type).isEnabled())
                    return true;
                if (!mandatory)
                    return true;
            } catch (Exception e) {
                if (i == attempts - 1 && mandatory) {
                    failedTest("Não foi possivel verificar a existência do elemento" + element + "|" + e.getMessage());
                    AllureFunctions.saveScreenshot();
                }
                Thread.sleep(1000);
            }
        }
        return false;
    }

    /**
     * Used in SmartField to clear the fields
     *
     * @param Type         Selenium By. (XPATH;ID;NAME;ETC) if send an element, null value
     * @param element_view Selenium WebElement (TYPE = NULL)
     * @param value        (String) value to select or fill the field
     */
    public static void clearField(By Type, WebElement element_view, String value) {
        if (Type != null) {
            for (int x = 0; x <= 100; x++) {
                try {
                    driver.findElement(Type).clear();
                    if (!driver.findElement(Type).getAttribute("value").equals("0,00") && !driver.findElement(Type).getAttribute("value").equals("")) {
                        driver.findElement(Type).sendKeys(Keys.ARROW_RIGHT);
                        driver.findElement(Type).sendKeys(Keys.BACK_SPACE);
                    } else
                        break;
                } catch (Exception e) {
                    break;
                }
            }
            if (value != null)
                driver.findElement(Type).sendKeys(value);
        } else if (element_view != null) {
            for (int x = 0; x <= 100; x++) {
                try {
                    element_view.clear();
                    if (!element_view.getAttribute("value").equals("0,00") && !element_view.getAttribute("value").equals("")) {
                        element_view.sendKeys(Keys.ARROW_RIGHT);
                        element_view.sendKeys(Keys.BACK_SPACE);
                    } else
                        break;
                } catch (Exception e) {
                    break;
                }
            }
            if (value != null)
                element_view.sendKeys(value);
        }
    }

    /**
     * Checks if the element appears on the screen
     *
     * @param Type         Selenium By. (XPATH;ID;NAME;ETC) if send an element, null value
     * @param element_view Selenium WebElement (TYPE = NULL)
     * @param value        (String) value to select or fill the field
     * @param attempts     (int) number of attempts to find the element
     */
    public static Functions SmartField(By Type, WebElement element_view, String value, int attempts) throws InterruptedException {
        for (int i = 0; i < attempts; i++)
            try {
                if (CheckExistingObject(Type, element_view, true, attempts)) {
                    String htmltag = "";
                    String type_element = "";
                    WebElement elementjava = null;
                    if (Type != null) {
                        htmltag = driver.findElement(Type).getTagName().toUpperCase().trim();
                        if (driver.findElement(Type).getAttribute("type") != null)
                            type_element = driver.findElement(Type).getAttribute("type").toUpperCase().trim();
                        elementjava = driver.findElement(Type);
                    } else if (element_view != null) {
                        htmltag = element_view.getTagName().toUpperCase().trim();
                        if (element_view.getAttribute("type") != null)
                            type_element = element_view.getAttribute("type").toUpperCase().trim();
                        elementjava = element_view;
                    }
                    JavascriptExecutor javaclick = (JavascriptExecutor) driver;
                    if (type_element.equals("SUBMIT"))
                        htmltag = "SUBMIT";
                    else if (htmltag.equals("INPUT") && type_element.matches("IMAGE|BUTTON|CHECKBOX|RADIO"))
                        htmltag = "BUTTON";
                    else if (htmltag.matches("MAT-CARD|MAT-CARD-FOOTER|MAT-ICON|SPAN|DIV|B|I|MAT-OPTION|STRONG|MAT-PANEL-DESCRIPTION|H3|P|LIB-BUTTON|LIB-FIELD-SELECT|TD|TH|TR"))
                        htmltag = "BUTTON";
                    else if (htmltag.matches("LIB-INPUT-TOGGLE|MAT-EXPANSION-PANEL|MAT-CHECKBOX|MAT-OPTION|MAT-SELECT|LIB-INPUT-CHECKBOX"))
                        htmltag = "BUTTON_SELENIUM";
                    else if (htmltag.equals("SELECT"))
                        htmltag = "SELECT";
                    if (htmltag.matches("INPUT|TEXTAREA"))
                        clearField(Type, element_view, value);
                    else if (htmltag.matches("BUTTON|A|SUBMIT"))
                        if (Type != null)
                            javaclick.executeScript("arguments[0].click();", driver.findElement(Type));
                        else
                            javaclick.executeScript("arguments[0].click();", elementjava);
                    else if (htmltag.equals("BUTTON_SELENIUM"))
                        if (Type != null)
                            driver.findElement(Type).click();
                        else {
                            assert elementjava != null;
                            elementjava.click();
                        }
                    else if (htmltag.equals("SELECT"))
                        if (Type != null) {
                            Select selectElement = new Select(driver.findElement(Type));
                            if (value != null)
                                selectElement.selectByVisibleText(value);
                        } else {
                            Select selectElement = new Select(element_view);
                            if (value != null)
                                selectElement.selectByVisibleText(value);
                        }
                    return new Functions();
                }
            } catch (Exception e) {
                Thread.sleep(500);
            }
        return new Functions();
    }

    /**
     * Randomizar numeros
     *
     * @param num quantidade de caracteres
     */
    private static int randomize(int num) {
        return (int) (Math.random() * num);
    }

    static final String numbersA = "0123456789";
    static final SecureRandom rnd2 = new SecureRandom();

    /**
     * Random Numeros
     *
     * @param len quantidade de caracteres
     */
    public static String randomStringNumber(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(numbersA.charAt(rnd2.nextInt(numbersA.length())));
        return sb.toString();
    }

    static final String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
    static final SecureRandom rnd = new SecureRandom();
    /**
     * Gerar Random de Strings
     *
     * @param len quantidade de caracteres
     */
    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    /**
     * Aleatoriar valores do mínimo ao máximo
     *
     * @param minimo valor mínimo
     * @param maximo valor máximo
     */
    public static int aleatoriar(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

    /**
     * Formatar Data
     *
     * @param diasSoma   quantidade de dias para somar
     * @param formatacao formatacao escolhida
     */
    public static String DataFormating(int diasSoma, String formatacao) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, diasSoma);
        dt = c.getTime();
        DateFormat dateFormat = new SimpleDateFormat(formatacao);
        return dateFormat.format(dt);
    }

    /**
     * Rolar a tela por coordenada x e y
     *
     * @param x coordenada x
     * @param y coordenada y
     */
    public static void ScrollDown(String x, String y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(" + x + "," + y + ")");
    }

    /**
     * Rolar a tela por coordenada x e y
     *
     * @param element webElement a ser movido
     */
    public static void ScrollElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Falhar teste
     *
     * @param messsageFail mensagem de falha
     */
    public static void failedTest(String messsageFail) {
        AllureFunctions.saveScreenshot();
        Assert.fail(messsageFail);
    }

    /**
     * Obtem o full xpath do elemento
     *
     * @param el Elemento WebElement
     */
    public static String xpathGetter(WebElement el) {
        int n = el.findElements(By.xpath("./ancestor::*")).size();
        String path = "";
        WebElement current = el;
        for (int i = n; i > 0; i--) {
            String tag = current.getTagName();
            int lvl = current.findElements(By.xpath("./preceding-sibling::" + tag)).size() + 1;
            path = String.format("/%s[%d]%s", tag, lvl, path);
            current = current.findElement(By.xpath("./parent::*"));
        }
        return "/" + current.getTagName() + path;
    }

    public static String SpaceComplete(String textoAdicionarEspaco, int QuantidadeCaracteres) {
        if (textoAdicionarEspaco == null) textoAdicionarEspaco = "";
        int textoCaracteres = QuantidadeCaracteres;
        int counttexto = textoAdicionarEspaco.length();
        String textoFormatado;
        if (counttexto > QuantidadeCaracteres)
            textoFormatado = textoAdicionarEspaco.substring(0, QuantidadeCaracteres);
        else {
            textoFormatado = textoAdicionarEspaco;
            for (int i = counttexto; i < textoCaracteres; i++)
                textoFormatado = textoFormatado + " ";
        }
        return textoFormatado;
    }

    public static String SpaceCompleteBefore(String textoAdicionarEspaco, int QuantidadeCaracteres) {
        int counttexto = textoAdicionarEspaco.length();
        String textoFormatado;
        if (counttexto > QuantidadeCaracteres)
            textoFormatado = textoAdicionarEspaco.substring(0, QuantidadeCaracteres);
        else {
            textoFormatado = textoAdicionarEspaco;
            for (int i = counttexto; i < QuantidadeCaracteres; i++)
                textoFormatado = " " + textoFormatado;
        }
        return textoFormatado;
    }


}
