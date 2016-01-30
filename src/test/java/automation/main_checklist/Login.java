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
     * проверить логин юзера и попап с большой карты Эксперта через кнопку Позвонить
     */

    @Test
    public void LoginFromBigExpertCard() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //найти эксперта со статусом Доступен сейчас и открыть большую карточку
        clickOnAvailableExpert(window);

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //кликнуть на кнопку Позвонить
        window.findElement((By.xpath("//*[@class='ga_btn btn btn-green btn-lg product__order-button']"))).click();

        // вставить тест хелпер ЛогинЮзерСайт
        loginSiteUser(Constants.USER_LOGIN,Constants.USER_PASSWORD,window);

        //подождать пока появиться попап запрос на аудио консультацию или попап предупреждение о платной консультации
//        pauseUntilDisplayed(By.xpath("//*[@class='dropdown-current__value']"), window);
        //todo что тут проверять то?

        //логаут
        logout(Constants.SITE_URL, window);

    }

    //проверить логин юзера и попап с большой карты Эксперта через кнопку ЧАТ



    //проверить логин юзера и попап с главной через кнопку Позвонить

    //проверить логин юзера и попап с главной через кнопку ЧАТ

/**
 *
 */

}
