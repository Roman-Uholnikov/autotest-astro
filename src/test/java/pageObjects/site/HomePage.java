package pageObjects.site;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Julia on 16-Jul-16.
 */
public class HomePage {

    private WebDriver driver;

    /**
     * Registration Link
     */
    @FindBy(xpath = "//*[@class='header__auth-login']//*[@href='#registration']")
    private WebElement registrationLink;

    /**
     * Login Link
     */
    @FindBy(xpath = "//*[@class='header__auth-login']//*[@href='#login']")
    private WebElement loginLink;

    /**
     * Expert's cards
     */

    @FindBy(xpath = "//*[@class='cards__list directions_container']//*[@class='cards__item']")
    private List<WebElement> expertCards;

    public void clickExpertCard(){
        expertCards.get(0).click();

    }




}
