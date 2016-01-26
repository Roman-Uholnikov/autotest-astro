package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Class contains business level operation.
 *
 * Created by Julia on 21-Nov-15.
 */

    public class TestHelper extends Base {


    /**
     * проверить следующий метод - Логин с карточки Эксперта через кнопку Уведомить о доступности
     */

    public void loginSiteUser(String login, String password, WebDriver window){

        //window.get(siteUrl);

        //findElementByXPath("//a[contains(.,'Вход')]", window).click();

        //подождать пока появиться форма логина
        //pauseUntilDisplayed(By.xpath(".//*[@id='login']//*[@id='user_models_User_phone']"), window);
        //ввести логин, пароль клиента, кликнуть Войти
        findElementById("user_models_User_phone", window).sendKeys(Constants.USER_LOGIN);
        findElementById("user_models_User_password", window).sendKeys(Constants.USER_PASSWORD);

        window.findElement((By.id("loginButton"))).click();

        //logger.info("Loggined user name: " + findElementByXPath("//span[@class='dropdown-current__value']", window).getText());
    }







    /**
     *  Login into site.
     *
     * @param siteUrl here should be PROD url or TEST url
     * @param login
     * @param password
     */

    //этот логин используется в классе чат, необходимо переписать
    public void loginSite(String siteUrl, String login, String password, WebDriver window){

        window.get(siteUrl);

        findElementByXPath("//div[@class='header__top']//a[@href='#login']", window).click();
        pauseUntilDisplayed(By.xpath("//form[@id='login_form']//input[@id='user_models_User_email']"), window);
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_email']", window).sendKeys(Constants.EXPERT_LOGIN);
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_password']", window).sendKeys(Constants.EXPERT_PASSWORD);
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
