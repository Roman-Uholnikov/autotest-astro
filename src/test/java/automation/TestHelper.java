package automation;

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
    public void loginSite(String siteUrl, String login, String password){
        openUrl(siteUrl);

        findElementByXPath("//div[@class='header__top']//a[@href='#login']").click();
        pauseUntilDisplayed("//form[@id='login_form']//input[@id='user_models_User_email']");
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_email']").sendKeys(Constants.Users.EXPERT_LOGIN);
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_password']").sendKeys(Constants.Users.EXPERT_PASSWORD);
        findElementByXPath("//*[@id='loginButton']").click();

        logger.info("Loggined user name: " + findElementByXPath("//span[@class='dropdown-current__value']").getText());
    }

    /**
     * Force server to delete the session.
     * 
     * @param siteUrl
     */
    public void logout(String siteUrl){
        openUrl(siteUrl + "logout");
    }

}
