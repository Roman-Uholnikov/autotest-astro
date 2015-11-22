package automation.registration;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by Julia on 22-Nov-15.
 */
public class Registration extends TestHelper {
    /**
     *
     */

    @Test
    public void testRegistrationUserFromMainPage() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);
        window.findElement(By.xpath("//a[contains(.,'Регистрация')]")).click();
        pauseUntilDisplayed(By.xpath(".//div[@class='registration__body']//*[@id='user_modules_registration_models_User_phone']"),window);
        window.findElement((By.xpath(".//div[@class='registration__body']//*[@id='user_modules_registration_models_User_phone']"))).sendKeys(Constants.USER_LOGIN);
        window.findElement((By.xpath(".//div[@class='registration__body']//*[@id='user_modules_registration_models_User_password']"))).sendKeys(Constants.USER_PASSWORD);
        window.findElement((By.xpath(".//div[@class='registration__body']//*[@id='user_modules_registration_models_User_verifyPassword']"))).sendKeys(Constants.USER_PASSWORD);
        window.findElement((By.xpath(".//div[@class='registration__body']//*[@id='user_modules_registration_models_User_rulesAccepted']"))).click();
        window.findElement(By.id("add-action")).click();

        logger.info("Введите смс из телефона");

        //pauseUntilDisplayed подождать пока окно для ввода смс появилось
        pauseUntilDisplayed(By.xpath("//*[@id='user_modules_registration_models_User_sms_access_code']"),window);
        window.findElement((By.xpath("//*[@id='user_modules_registration_models_User_sms_access_code']"))).sendKeys("SMS ???????");
        //нажать подтвердить регистрацию
        //проверить что мы видим следующий шаг регистрации
    }



}
