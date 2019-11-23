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

public class Login extends UIBase{

    private final Logger log = LoggerHelper.getLogger(Login.class);
    WaitHelper waitHelper;

    @FindBy(xpath="//p[text()=' Login or Create Account']/..")
    WebElement loginOrCreateBtn;

    @FindBy(id="username")
    WebElement username;

    @FindBy(xpath="//button[@data-cy='continueBtn']")
    WebElement continueBtn;

    @FindBy(xpath="//button[@data-cy='login']")
    WebElement loginBtn;

    @FindBy(id="password")
    WebElement password;

    @FindBy(linkText="or Login via Password")
    WebElement viaPasswordLink;

    @FindBy(xpath="//li[@data-cy='account']")
    WebElement account;

    @FindBy(xpath="//p[contains(text(),'personal profile')]")
    WebElement usernamelabel;


    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public void clickLoginOrCreate() {
        waitHelper.WaitForElementClickable(loginOrCreateBtn);
        log.info("clicking login....");
        loginOrCreateBtn.click();
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
        clickButton(continueBtn);
    }

    public void clickLogin() {
        waitHelper.WaitForElementClickable(loginBtn);
        log.info("clicking continue....");
        clickButton(loginBtn);
    }

    public void clickviaPasswordLink() {
        waitHelper.WaitForElementClickable(viaPasswordLink);
        log.info("clicking viaPasswordLink....");
        viaPasswordLink.click();
    }

    public void login(String strusername, String strPassword) {
        clickLoginOrCreate();
        enterUsername(strusername);
        clickContinue();
        enterPassword(strPassword);
        clickLogin();
    }

    public boolean verifyUser(String strUserName) {
        waitHelper.WaitForElementClickable(account);
        log.info("Opening user details....");
        account.click();
        waitHelper.waitForElement(usernamelabel);
        if(usernamelabel.getText().contains(strUserName)){
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ChromeBrowser obj = new ChromeBrowser();
        driver = obj.getChromeDriver(obj.getChromeOptions());
            driver.get("https://www.makemytrip.com/");
            driver.manage().window().maximize();
            Login login = new Login(driver);
            login.login("makemytrip223@gmail.com","Project@223");
            System.out.println(login.verifyUser("makemytrip223@gmail.com"));

    }
}