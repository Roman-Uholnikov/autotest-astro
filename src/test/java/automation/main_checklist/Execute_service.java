package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julia on 26.01.2016.
 */
public class Execute_service extends TestHelper {
    /**
     * перед выполнением теста проверить: 1) все данные клиента для выполн услуги заполнены, 2)на балансе достаточно средств для заказа
     */

    @Test
    public void Order_service() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL + "/expert/yuli");

                //кликнуть Заказать в карточке Услуги
        findElementByXPath(".//*[@class='btn btn-green product__order-button ga_btn']",window).click();

        //войти под Клиентом
        loginSiteUser(Constants.USER_LOGIN,Constants.USER_PASSWORD,window);

        pauseUntilDisplayed(By.xpath(".//*[@id='expert-request-service_price']"),window);
        getFirstDisplayedWebElement(window,".//*[@id='comment']").sendKeys("Тестовая проверка заказа услуги у Эксперта Юли");
        getFirstDisplayedWebElement(window,".//*[@id='expert-request-service_form_']//fieldset//div[@class='form-group']/button[@type='submit']").click();

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