package automation.main_checklist;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by Julia on 22.01.2016.
 */
public class OpenCards extends TestHelper {


    /**
     * проверить открытие карты эксперта онлайн, нажатие Позвонить, проверка нид_логин
     */
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



    /**
     * выбрать тематику а главной - открыть эксперта, выбрать у него тематику из блока консультарую, в открывшемся каталоге открыть эксперта ОФФлайн
     */
    @Test
    public void OpenCardWithDirection() {
        WebDriver window = getNewWindow();
        List<String> thematics = Arrays.asList("family-and-kids", "love-and-relationships", "destiny", "vip", "job-and-career"); //todo дописать все отсальные
        List<String> specialities = Arrays.asList("astrology", "extrasensory", "divination", "numerology", "healing");
        List<String> thematicAndSpecialities = new ArrayList<>();
        for(String thematic: thematics) {
            thematicAndSpecialities.add("all/" + thematic);
        }
        thematicAndSpecialities.addAll(specialities);

        for (String speciality : specialities) {
            for (String thematic : thematics) {
                thematicAndSpecialities.add(speciality + "/" + thematic);
            }
        }

        //проверка. Для каждой тематики и специализации
        for(String thematicOrSpeciality: thematicAndSpecialities){
            logger.info("Проверяем доступность экспертов настранице: " + Constants.SITE_URL + "/experts/" + thematicOrSpeciality);
            //открываем адрес
            window.get(Constants.SITE_URL + "/experts/" + thematicOrSpeciality);
            //найти эксперта со статусом Доступен сейчас и открыть большую карточку
            boolean foundAvailableExpert = clickOnAvailableExpert(window);
            window.get(Constants.SITE_URL + "/experts/" + thematicOrSpeciality);
            //найти эксперта со статусом НеДоступен сейчас и открыть большую карточку
            boolean foundNotAvailableExpert = clickOnNotAvailableExpert(window);
            window.get(Constants.SITE_URL + "/experts/" + thematicOrSpeciality);
            //найти эксперта со статусом Занят на линии и кликнуть по кнопке Уведомить о доступности
            boolean foundBusyExpert = clickOnBusyExpert(window);
            if(!(foundAvailableExpert | foundBusyExpert | foundNotAvailableExpert)) {
                fail("Не один эксперт не был найден на странице:" + Constants.SITE_URL + "/experts/" + thematicOrSpeciality);
            }
        }


    }


}


    //открыть карту услуги с главной

    //выбрать тематику для услуги - открыть карту услуги

    //выбрать тематику и открыть статью

    // зайти в каталог гороскопов с главной

    // открыть гороскоп из каталога

    //переключить гороскоп в блоке Гороскоп для другиз ЗЗ

    //переключить каталоги в виджете гороскопов на главной




