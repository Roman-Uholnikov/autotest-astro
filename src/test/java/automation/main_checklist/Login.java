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

        if (window.findElements(By.className("card__status-inner")).size() > 0) {
            window.findElements(By.xpath("//*[@class='card__title-inner']")).get(0).click();
        }

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //кликнуть на кнопку Позвонить
        window.findElement((By.xpath("//*[@class='ga_btn btn btn-green btn-lg product__order-button']"))).click();



        // вставить тест хелпер ЛогинЮзерСайт



        //подождать пока появиться попап запрос на аудио консультацию или попап предупреждение о платной консультации
        pauseUntilDisplayed(By.xpath("//*[@class='dropdown-current__value']"), window);

        //логаут

    }

    //проверить логин юзера и попап с большой карты Эксперта через кнопку ЧАТ



    //проверить логин юзера и попап с главной через кнопку Позвонить

    //проверить логин юзера и попап с главной через кнопку ЧАТ

/**
 *
 */

}
