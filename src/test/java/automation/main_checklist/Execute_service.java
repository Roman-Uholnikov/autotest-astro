package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import com.sun.java.swing.plaf.windows.resources.windows;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import sun.plugin2.os.windows.Windows;

import java.awt.*;

/**
 * Created by Julia on 26.01.2016.
 */
public class Execute_service extends TestHelper {


    @Test
    public void Order_service() {
        WebDriver window = getNewWindow();
        window.get(Constants.SITE_URL);

        //войти под Клиентом
        loginSiteUser(Constants.USER_LOGIN,Constants.USER_PASSWORD,window);
        //открыть карточку тестового эксперта Юли
        window.get("https://astro.club/expert/yuli");


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