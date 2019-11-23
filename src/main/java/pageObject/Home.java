package pageObject;

import commons.LoggerHelper;
import commons.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Home {
    private WebDriver driver;
    private final Logger log = LoggerHelper.getLogger(Login.class);
    WaitHelper waitHelper;

    @FindBy(xpath="//span[text()='Flights']/..")
    WebElement flightLink;

    @FindBy(xpath="//li[text()='Oneway']")
    WebElement onewayRadio;

    @FindBy(id="fromCity")
    WebElement exisitngFromCity;

    @FindBy(xpath="//input[@placeholder='From']")
    WebElement fromCityField;

    @FindBy(id="toCity")
    WebElement exisitngToCity;

    @FindBy(xpath="//input[@placeholder='To']")
    WebElement toCityField;

    @FindBy(xpath="//div[contains(@class,'autocomplePopup')]//p[contains(@class,'blackText')]")
    List<WebElement> cityOptions;

    public void selectFlightOPtion() {
        flightLink.click();
    }

    public void selectOneWay() {
        onewayRadio.click();
    }

    public void selectFromCity(String strFromCity) {
        exisitngFromCity.click();
        fromCityField.sendKeys(strFromCity);
    }


}
