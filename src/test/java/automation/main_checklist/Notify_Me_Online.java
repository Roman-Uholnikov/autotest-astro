package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Julia on 26.01.2016.
 */
public class Notify_Me_Online extends TestHelper {

    //проверить открытие карты эксперта оффлайн и нажать Заказать консультацию с главной, затем Уведомить меня

    @Test
    public void OpenCardOfflineExpert() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //найти эксперта со статусом Нет в сети и открыть большую карточку (по кнопке Заказать консультацию)//// TODO: 11.02.2016  
        pause(3);

        window.findElement(By.xpath("//div[@class='card__preorder']//*[@class='btn btn-green btn-alpha']")).click();


        if (window.findElements(By.xpath("//div[@class='card__preorder']//*[@class='btn btn-green btn-alpha']")).size() > 0) {
            window.findElements(By.xpath("//div[@class='card__preorder']//*[@class='btn btn-green btn-alpha']")).get(0).click();
        }

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //кликнуть по кнопке Уведомить меня
        window.findElement(By.xpath("//*[@class='btn btn-default btn-lg product__order-button ga_btn']")).click();

        //войти на сайт использовать метод логина из тест хелпера
        loginSiteUser(Constants.USER_LOGIN,Constants.USER_PASSWORD,window);

        //открылось окно с инфо об уведомлении с номером телефона юзера
        pauseUntilDisplayed(By.xpath(".//*[@id='notify-me-online']"), window);

        //проверить что отображается телефон
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


}
