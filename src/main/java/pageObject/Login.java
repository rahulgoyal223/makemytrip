package pageObject;

import commons.ChromeBrowser;
import org.apache.log4j.Logger;
import commons.LoggerHelper;
import commons.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class Login {

    private WebDriver driver;
    private final Logger log = LoggerHelper.getLogger(Login.class);
    WaitHelper waitHelper;

    @FindBy(xpath="//p[text()=' Login or Create Account']/..")
    WebElement loginBtn;

    @FindBy(id="username")
    WebElement username;

    @FindBy(xpath="//button[@data-cy='continueBtn']")
    WebElement continueBtn;

    @FindBy(id="password")
    WebElement password;

    @FindBy(linkText="or Login via Password")
    WebElement viaPasswordLink;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public void clickLogin() {
        waitHelper.WaitForElementClickable(loginBtn);
        log.info("clicking login....");
        loginBtn.click();
    }

    public void enterUsername(String strUserName) {
        waitHelper.waitForElement(username);
        log.info("entering username...."+strUserName);
        username.sendKeys(strUserName);
    }

    public void enterPassword(String strPassword) {
        waitHelper.waitForElement(password);
        log.info("entering password...."+strPassword);
        password.sendKeys(strPassword);
    }

    public void clickContinue() {
        waitHelper.WaitForElementClickable(continueBtn);
        log.info("clicking continue....");
        for(int i=0;i<=10;i++) {
            try {
                new Actions(driver).moveToElement(continueBtn).click().build().perform();
            } catch (Exception e) {
                break;
            }
        }
    }

    public void clickviaPasswordLink() {
        waitHelper.WaitForElementClickable(viaPasswordLink);
        log.info("clicking viaPasswordLink....");
        viaPasswordLink.click();
    }

    public void login(String strusername, String strPassword) {
        clickLogin();
        enterUsername(strusername);
        clickContinue();
        clickviaPasswordLink();
        enterPassword(strPassword);
        clickContinue();
    }

    public static void main(String[] args) {
        ChromeBrowser obj = new ChromeBrowser();
        WebDriver driver = obj.getChromeDriver(obj.getChromeOptions());
            driver.get("https://www.makemytrip.com/");
            driver.manage().window().maximize();
            Login login = new Login(driver);
            login.login("8431484412","Preeti@2808");

    }
}