package automation;

import org.openqa.selenium.WebDriver;

/**
 * Class contains business level operation.
 *
 * Created by Julia on 21-Nov-15.
 */
public class TestHelper extends Base {

    /**
     *  Login into site.
     *
     * @param siteUrl here should be PROD url or TEST url
     * @param login
     * @param password
     */
    public void loginSite(String siteUrl, String login, String password, WebDriver window){

        window.get(siteUrl);

        findElementByXPath("//div[@class='header__top']//a[@href='#login']", window).click();
        pauseUntilDisplayed("//form[@id='login_form']//input[@id='user_models_User_email']", window);
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_email']", window).sendKeys(Constants.Users.EXPERT_LOGIN);
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_password']", window).sendKeys(Constants.Users.EXPERT_PASSWORD);
        findElementByXPath("//*[@id='loginButton']", window).click();

        logger.info("Loggined user name: " + findElementByXPath("//span[@class='dropdown-current__value']", window).getText());
    }

    /**
     * Force server to delete the session.
     * 
     * @param siteUrl
     */
    public void logout(String siteUrl, WebDriver window){
        window.get(siteUrl + "logout");
    }

}
