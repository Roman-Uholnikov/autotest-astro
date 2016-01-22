package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Julia on 21.12.2015.
 */
public class Login extends TestHelper {
    /**
     * проверить логин юзера с большой карты Эксперта через кнопку Позвонить
     */

    @Test
    public void LoginFromBigExpertCard() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //найти эксперта со статусом Доступен сейчас и открыть большую карточку

        if (window.findElements(By.className("card__status-inner")).size() > 0) {
            window.findElements(By.xpath("//*[@class='card__title-inner']")).get(0).click();
        }

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //кликнуть на кнопку Позвонить
        window.findElement((By.xpath("//*[@class='ga_btn btn btn-green btn-lg product__order-button']"))).click();

        //подождать пока появиться форма логина
        pauseUntilDisplayed(By.xpath("//*[@class='login']"), window);

        //ввести логин, пароль клиента, кликнуть Войти
        window.findElement((By.id("user_models_User_phone"))).sendKeys("0932649105");
        window.findElement((By.id("user_models_User_password"))).sendKeys("123456");
        window.findElement((By.id("loginButton"))).click();

        //подождать пока появиться попап запрос на аудио консультацию или попап предупреждение о платной консультации
        pauseUntilDisplayed(By.xpath("//*[@class='dropdown-current__value']"), window);

        //логаут

    }

/**
 *
 */

}
