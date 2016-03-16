package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class contains business level operation.
 * <p>
 * Created by Julia on 21-Nov-15.
 */

public class TestHelper extends Base {


    /**
     * логин (авторизация пользователя) в уже отрытое и активное окно авторизации.
     * Подразумевается что окно ввода логина и пароля уже было вызвано и ожидает ввода.
     */

    public void loginSiteUser(String login, String password, WebDriver window) {
        //ввести логин, пароль клиента, кликнуть Войти
        pause(1);
        //Я не знаю почему, возможно гдето яваскрпит реагирует, но нужно первый раз чтото ввести.
        getFirstDisplayedWebElement(window, "//*[@id='user_models_User_password']").sendKeys(password);
        pause(1);
        getFirstDisplayedWebElement(window, "//*[@id='user_models_User_phone']").sendKeys(login);
        getFirstDisplayedWebElement(window, "//*[@id='user_models_User_password']").clear();
        getFirstDisplayedWebElement(window, "//*[@id='user_models_User_password']").sendKeys(password);
        pause(3);
        window.findElement((By.id("loginButton"))).click();

    }


    /**
     * открывает указаный адрес, и пытаеться найти кнопку "Вход" и залогиниться
     * //
     *
     * @param siteUrl  here should be PROD url or TEST url
     * @param login
     * @param password
     */
    public void loginSite(String siteUrl, String login, String password, WebDriver window) {

        window.get(siteUrl);

        findElementByXPath("//div[@class='header__top']//a[@href='#login']", window).click();
        pauseUntilDisplayed(By.xpath(".//*[@id='login']/div[@class='modal-dialog']"), window);

        loginSiteUser(login, password, window);
    }


    /**
     * Force server to delete the session.
     *
     * @param siteUrl
     */
    public void logout(String siteUrl, WebDriver window) {
        window.get(Constants.SITE_URL + "/logout");

    }


       // findElementByXPath("//div[@class='dropdown-current']/span[@class='dropdown-current__value']",window).click();



    /**
     * Найти эксперта со статусом Доступен сейчас и открыть большую карточку.
     * Если доступного эксперта не удалось найти, тест продолжит работать.
     * Ели эксперта удалось найти, но его карточка не открылась, тест упадет.
     *
     * @param window Окно в котором открывать
     * @return true - эксперт был найден, либо false - если эксперт не был найден
     */
    protected boolean clickOnAvailableExpert(WebDriver window) {
        final String xpathExpression = "//div[@class='card__status status-available']/following-sibling::a[@class='card__image card__image_round']";
        pause(3);
        final List<WebElement> expertsList = window.findElements(By.xpath(xpathExpression)).stream()
                .filter(expertElement -> expertElement.isDisplayed()).collect(Collectors.toList());
        if (expertsList.size() > 0) {
            expertsList.get(0).click();
            //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
            pauseUntilFound(By.xpath("//*[@class='product__title']"), DEFAULT_WAIT_TIME_SEC, window);
            logger.info("Найден Доступный эксперт :" + window.findElement(By.xpath("//*[@class='product__title']")).getText());
            return true;
        }
        logger.info("Не найдено ни одного Доступного Эксперта");
        return false;
    }

    /**
     * Найти эксперта со статусом Недоступен сейчас и открыть большую карточку.
     * Если доступного эксперта не удалось найти, тест продолжит работать.
     * Ели эксперта удалось найти, но его карточка не открылась, тест упадет.
     *
     * @param window Окно в котором открывать
     * @return true - эксперт был найден, либо false - если эксперт не был найден
     */
    protected boolean clickOnNotAvailableExpert(WebDriver window) {
        //найти эксперта со статусом Нет в сети и открыть большую карточку (по кнопке Заказать консультацию)
        final String xpathExpression = "//*[@class='btn btn-green btn-alpha']";
        pause(3);
        final List<WebElement> expertsList = window.findElements(By.xpath(xpathExpression)).stream()
                .filter(expertElement -> expertElement.isDisplayed()).collect(Collectors.toList());
        if (expertsList.size() > 0) {
           expertsList.get(0).click();
            //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
            pauseUntilFound(By.xpath("//*[@class='product__title']"), DEFAULT_WAIT_TIME_SEC, window);
            logger.info("Найден Не Доступный эксперт :" + window.findElement(By.xpath("//*[@class='product__title']")).getText());
            return true;
        }
        logger.info("Не найдено ни одного Недоступного Эксперта");
        return false;
    }

    /**
     * Найти эксперта со статусом "Занят На Линии" сейчас и открыть большую карточку.
     * Если доступного эксперта не удалось найти, тест продолжит работать.
     * Ели эксперта удалось найти, но его карточка не открылась, тест упадет.
     *
     * @param window Окно в котором открывать
     * @return true - эксперт был найден, либо false - если эксперт не был найден
     */
    protected boolean clickOnBusyExpert(WebDriver window) {
        String xpathForBusyExpert = "//div[@class='card__status status-busy' and child::span[contains(.,'Занят на линии')]]/following-sibling::a[@class='card__image card__image_round']";
        pause(3);
        final List<WebElement> expertsList = window.findElements(By.xpath(xpathForBusyExpert)).stream()
                .filter(expertElement -> expertElement.isDisplayed()).collect(Collectors.toList());
        if (expertsList.size() > 0) {
            expertsList.get(0).click();
            //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
            pauseUntilFound(By.xpath("//*[@class='product__title']"), DEFAULT_WAIT_TIME_SEC, window);
            logger.info("Найден Эксперт На Линии :" + window.findElement(By.xpath("//*[@class='product__title']")).getText());
            return true;
        }
        logger.info("Не найдено ни одного Эксперт На Линии");
        return false;
    }

    /**
     * Возвращает первый ОТОБРАЖАЕМЫЙ елемент, надйеный по XPath.
     * @param window - окно в котором мы ищем.
     * @param xpathExpression
     * @return
     */
    protected WebElement getFirstDisplayedWebElement(final WebDriver window, final String xpathExpression) {
        final List<WebElement> allElements = window.findElements(By.xpath(xpathExpression));
        final List<WebElement> visibleWebElements = allElements.stream()
                .filter(expertElement -> expertElement.isDisplayed()).collect(Collectors.toList());

        if(allElements.size()==0){
            logger.warning("Не найден ни один елемент (отображаемый или не отображаемый)");
            return null;
        }

        if(visibleWebElements.size() == allElements.size() && allElements.size()!=1){
            logger.warning("Все элементы видимы. Возвращаем первый. Это может быть ошибкой, будте внимательны");
        }

        if(visibleWebElements.size()==0){
            logger.warning("Не найден ни один видимый елемент, берем тот который не видимый");
            allElements.get(0);
        }

        return visibleWebElements.get(0);
    }
}
