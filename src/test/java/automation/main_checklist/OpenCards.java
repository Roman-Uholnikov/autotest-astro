package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Julia on 22.01.2016.
 */
public class OpenCards extends TestHelper {


     //проверить открытие карт эксперта онлайн


    @Test
    public void OpenCardOnlineExpert() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //найти эксперта со статусом Доступен сейчас и открыть большую карточку

        if (window.findElements(By.className("card__status-inner")).size() > 0) {
            window.findElements(By.xpath("//*[@class='card__title-inner']")).get(0).click();
        }

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

    }

    //проверить открытие карты эксперта оффлайн и нажать Заказать консультацию с главной, затем Уведомить меня

    @Test
    public void OpenCardOfflineExpert() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //найти эксперта со статусом Нет в сети и открыть большую карточку (по кнопке Заказать консультацию)

        if (window.findElements(By.xpath("//*[@class='btn btn-green btn-alpha']")).size() > 0) {
            window.findElements(By.xpath("//*[@class='btn btn-green btn-alpha']")).get(0).click();
        }

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //кликнуть по кнопке Уведомить меня
        window.findElement(By.xpath("//*[@class='btn btn-default btn-lg product__order-button ga_btn']")).click();

        //войти на сайт использовать метод логина из тест хелпера
        loginSiteUser(Constants.USER_LOGIN,Constants.USER_PASSWORD,window);

        //открылось окно с инфо об уведомлении с номером телефона юзера
        pauseUntilDisplayed(By.xpath(".//*[@id='notify-me-online']"), window);

    }

    //проверить открытие карточки Занят на линии и кликнуть Уведомить меня в маленькой карте Эксперта

    @Test
    public void OpenCardBusyExpert() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //найти эксперта со статусом Занят на линии и кликнуть по кнопке Уведомить о доступности

        if (window.findElements(By.xpath("//*[@class='']")).size() > 0) {
            window.findElements(By.xpath("//*[@class='btn btn-green btn-alpha']")).get(0).click();
        }

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //нажать на
    }
    // выбрать тематику - открыть эксперта, выбрать специализацию, проверить пересечение


    //открыть карту услуги с главной

    //выбрать тематику для услуги - открыть карту услуги

    //выбрать тематику и открыть статью

    // зайти в каталог гороскопов с главной

    // открыть гороскоп из каталога

    //переключить гороскоп в блоке Гороскоп для другиз ЗЗ


}

