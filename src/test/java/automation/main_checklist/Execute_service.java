package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import com.sun.java.swing.plaf.windows.resources.windows;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sun.plugin2.os.windows.Windows;

import java.awt.*;
import java.util.stream.Collectors;

/**
 * Created by Julia on 26.01.2016.
 */
public class Execute_service extends TestHelper {


    @Test
    public void Order_service() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //открыть карточку тестового эксперта Юли
        window.get("https://astro.club/expert/yuli");

        //кликнуть Заказать в карточке Услуги
        findElementByXPath(".//*[@class='btn btn-green product__order-button ga_btn']",window).click();

        //войти под Клиентом
        loginSiteUser(Constants.USER_LOGIN,Constants.USER_PASSWORD,window);

        pauseUntilDisplayed(By.xpath(".//*[@id='expert-request-service_price']"),window);
        findElementById(".//*[@id='comment']",window).sendKeys("Тестовая проверка заказа услуги у Эксперта Юли");
        window.findElements(By.xpath("//fieldset//div[@class='form-group']/button[@type='submit']")).stream()
                .filter(expertElement -> expertElement.isDisplayed()).collect(Collectors.toList()).get(0).click();

        // заказать услугу у эксперта
        //пополнить счет - редирект
        //заказ
        //выйти из пользователя
        //зайти под экспертом
        //выполнить услугу
        //выйти из эксперта
        //зайти под модератором
        //пропустить услугу
        //зайти под клиентом
        //скачать пдф с услугой, ПРОВЕРИТЬ текст
    }
}