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
     * перед выполнением теста проверить: 1) все данные клиента (фио, имя, др клиента) для выполн услуги заполнены, 2)на балансе достаточно средств для заказа
     */

    @Test
    public void Order_service() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL + "/service/30/yuli");

                //кликнуть Заказать в карточке Услуги
        findElementByXPath(".//*[@class='btn btn-green btn-lg product__order-button ga_btn']",window).click();

        //войти под Клиентом
        loginSiteUser(Constants.USER_LOGIN,Constants.USER_PASSWORD,window);

        pauseUntilDisplayed(By.xpath(".//*[@id='expert-request-service_price']"),window);
        findElementById("question_1",window).sendKeys("Test1");
        findElementById("question_2",window).sendKeys("Test2");
        findElementById("question_3",window).sendKeys("Test3");
        getFirstDisplayedWebElement(window,".//*[@id='comment']").sendKeys("Тестовая проверка заказа услуги у Эксперта Юли");
        getFirstDisplayedWebElement(window,".//*[@id='expert-request-service_form_']//fieldset//div[@class='form-group']/button[@type='submit']").click();
        pauseUntilDisplayed(By.id("btnOk"),window);
        logout(Constants.SITE_URL,window);

        //войти экспертом, выполнить услугу

        loginSite(Constants.SITE_URL,Constants.EXPERT_LOGIN,Constants.EXPERT_PASSWORD,window);
        pause(3);
        window.get(Constants.SITE_URL + "/account/orders");

        pause(3);
        getFirstDisplayedWebElement(window,".//a[@class='btn btn-green btn-wide custom-table__btn']").click();
        pause(3);

        String parentWindow = window.getWindowHandle();
        window.switchTo().frame("expertText_ifr");
        pause(1);
        findElementByXPath(".//*[@id='tinymce']",window).sendKeys("Дано известно, астрология — древнейшее учение, доказывающее движением небесных тел и событиями, происходящими на Земле, существует определенная связь. Существуют многочисленные определения астрологии, широкие определения включают различные понятия астрономии, психологии, магии, эзотерики, нумерологии.\n" +
                "Предугадывать определенные события люди научились очень много лет назад, но для того чтобы основательно подготовиться к поворотам судьбы – к этому вопросу мы предлагаем подходить более серьезно. Звезды способны рассказать нам о предстоящих событиях и определить необходимые действия. Гороскопы способны приоткрыть нам занавесу будущего. Любая цепочка событий может быть прервана или разрушена свободной волей человека.\n" +
                "Наши эксперты помогут вам разобраться в решении жизненных вопросов, вовремя ухватиться за счастливый шанс или просто сделать жизнь приятней и светлее. Астропрогнозы, гороскопы, аудио и чат консультации – это те услуги, которые вы сможете заказать на нашем сайте.\n" +
                "Составлять астрологические прогнозы люди научились еще множество веков назад для того, чтобы иметь возможность лучше подготовиться к сюрпризам, которые готовит нам судьба. Ориентация в реальной жизни по небесным светилам является одним из способов приоткрыть завесу будущего. Звезды способны рассказать нам о предстоящих событиях и определить наши действия.\n" +
                "Каждый из нас вправе сам решать, верить или нет в астрологический прогноз судьбы, но неоспоримым фактом является то, что астрология — реальная наука со своими методами исследования и неоспоримыми результатами, сомнений не вызывает. И мы вам это готовы доказать!\n");


        window.switchTo().window(parentWindow);
        findElementById("sendBtn",window).click();
        pauseUntilDisplayed(By.xpath(".//*[@id='btnOk']"),window);
        findElement(By.xpath(".//*[@id='btnOk']"),window).click();
        logout(Constants.SITE_URL,window);

        loginAdminSite(Constants.SITE_ADMIN_URL,Constants.ADMIN_LOGIN,Constants.ADMIN_PASSWORD,window);



        .//*[@id='yw1']/li/a[@class='dropdown-toggle']/span[@class='menu-text']
        .//*[@class='dropdown-toggle']/span[@class='menu-text']

        https://admin.astrolog.net.ua/service/admin/service/showdoneservice


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