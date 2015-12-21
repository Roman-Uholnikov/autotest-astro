package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Julia on 21.12.2015.
 */
public class MainPoints extends TestHelper {
    /**
     * проверить открытие страницки эксперта на главной
     */
    @Test
    public void VerificationForNeedLoginFronBigExpertCard() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //найти эксперта со статусом Доступен сейчас
        //pauseUntilDisplayed(By.className("card__status-inner"),window);
        if (window.findElements(By.className("card__image card__image_round")).size() > 0) {
            window.findElements(By.className("card__image card__image_round")).get(0).click();
        }

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //кликнуть на кнопку Позвонить
        window.findElement((By.xpath("//*[@class='ga_btn btn btn-green btn-lg product__order-button']"))).click();


        //подождать пока появиться форма логина
        pauseUntilDisplayed(By.xpath("//*[@class='login']"), window);


    }
}
