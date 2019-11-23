package pageObject;

import commons.ChromeBrowser;
import commons.LoggerHelper;
import commons.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UIBase {
    public static WebDriver driver;
    private final Logger log = LoggerHelper.getLogger(UIBase.class);
    WaitHelper waitHelper;

    public UIBase() {
        waitHelper = new WaitHelper(driver);
    }

    public void clickButton(WebElement element) {
        for(int i=0;i<=10;i++) {
            System.out.println(i);
            try {
                waitHelper.WaitForElementClickable(element);
                new Actions(driver).moveToElement(element).click().build().perform();
            } catch (Exception e) {
                break;
            }
        }
    }
}
