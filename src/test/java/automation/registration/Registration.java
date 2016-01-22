package automation.registration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import automation.Constants;
import automation.TestHelper;

/**
 * Created by Julia on 22-Nov-15.
 */
public class Registration extends TestHelper {
    /**регистрация пользователя Антона
     *
     */

    @Test
    public void testRegistrationUserFromMainPage() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        window.findElement(By.xpath("//a[contains(.,'Регистрация')]")).click();
        pauseUntilDisplayed(By.xpath(".//*[@id='registration']//*[@id='user_models_User_phone']"), window);
        window.findElement((By.xpath(".//*[@id='registration']//*[@id='user_models_User_phone']"))).sendKeys(Constants.REG_LOGIN);
        window.findElement((By.xpath(".//*[@id='registration']//*[@id='user_models_User_password']"))).sendKeys(Constants.REG_PASSWORD);
        WebElement checkBox = window.findElement((By.xpath(".//*[@id='registration']//label//span")));
        //наводим мишку и нажимаем
        Actions builder = new Actions(window);
        builder.moveToElement(checkBox, 5, 5).perform();
        builder.click();

        pauseUntilDisplayed(By.id("registerButton"), window);
        window.findElement(By.id("registerButton")).click();

        // вывод информации в консоли выполнения теста
        logger.info("Введите смс из телефона");

        //pauseUntilDisplayed подождать пока окно для ввода смс появилось
        pauseUntilDisplayed(By.xpath("//*[@id='user_modules_registration_models_User_sms_access_code']"), window);

        //ввести код из смс
        window.findElement((By.xpath("//*[@id='user_modules_registration_models_User_sms_access_code']"))).sendKeys("SMS ???????");

        //нажать кнопку Зарегистрироваться
        // далее появляется попап thankyoupage, нажать кнопку Перейти в личный кабинет
        //вести имя ТестАнтон в ЛК
        //нажать Сохранить данные в ЛК
    }


}
