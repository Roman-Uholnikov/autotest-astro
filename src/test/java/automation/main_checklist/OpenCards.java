package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.fail;

/**
 * Created by Julia on 22.01.2016.
 */
public class OpenCards extends TestHelper {


     //проверить открытие карты эксперта онлайн, нажатие Позвонить, проверка нид_логин


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

        //проверить нид_логин - кликнуть Позвонить


        WebElement freeAudioButton = null;
        WebElement nonFreeAudioButton = null;
        if (window.findElements(By.xpath(".//*[@id='nonFreeAudioBtn']")).size() > 0) {
            nonFreeAudioButton = window.findElements(By.xpath(".//*[@id='nonFreeAudioBtn']")).get(0);
        }

        if (window.findElements(By.xpath(".//*[@id='freeAudioBtn']")).size() > 0) {
            freeAudioButton = window.findElements(By.xpath(".//*[@id='freeAudioBtn']")).get(0);
        }


        if (freeAudioButton == null && nonFreeAudioButton == null) {
            //не найдена не та не та кнопка. Выкидываем ошибку
            fail("нет кнопки позвонить");
        } else {
            if (freeAudioButton != null) {
                freeAudioButton.click();
            } else {
                nonFreeAudioButton.click();
            }

        }

    //pauseUntilDisplayed подождать пока появилось окно Вход
    pauseUntilDisplayed(By.xpath(".//*[@id='login']//*[@id='user_models_User_phone']"), window);

    }



    // выбрать тематику а главной - открыть эксперта, выбрать у него тематику из блока консультарую, в открывшемся каталоге открыть эксперта ОФФлайн


    @Test
    public void OpenCardWithDirection() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //выбрать Тематику на главной - открыть карту Эксперта

        window.findElement(By.xpath("//a[contains(.,'Работа и карьера')]")).click();
        window.findElement(By.xpath("//*[@class='card__title-inner']")).click();

        //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
        pauseUntilDisplayed(By.xpath("//*[@class='product__title']"), window);

        //кликнуть тематику из блока Консультирую
        window.findElement(By.xpath("//*[@class='page-menu__list-link']")).click();

        //pauseUntilDisplayed подождать пока появится каталог экспертов с дропдауном Все специализации
        pauseUntilDisplayed(By.xpath(".//*[@id='select2-specSelect-container']"), window);

       //выбрать тематику  Судьба и будущее

        window.get(Constants.SITE_URL + "/experts/all/destiny");


        //открыть карту Оффлайн эксперта ИЛИ ЗАНЯТ НА ЛИНИИ предусмотреть
        if (window.findElements(By.xpath("//*[@class='btn btn-green btn-alpha']")).size() > 0) {
            window.findElements(By.xpath("//*[@class='btn btn-green btn-alpha']")).get(0).click();
        }


        window.get(Constants.SITE_URL + "/experts/all");


        //pauseUntilDisplayed подождать пока появилось окно Вход
        pauseUntilDisplayed(By.xpath(".//*[@id='login']//*[@id='user_models_User_phone']"), window);


    }
}






    //открыть карту услуги с главной

    //выбрать тематику для услуги - открыть карту услуги

    //выбрать тематику и открыть статью

    // зайти в каталог гороскопов с главной

    // открыть гороскоп из каталога

    //переключить гороскоп в блоке Гороскоп для другиз ЗЗ

    //переключить каталоги в виджете гороскопов на главной




