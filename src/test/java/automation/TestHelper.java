package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Class contains business level operation.
 * <p>
 * Created by Julia on 21-Nov-15.
 */

public class TestHelper extends Base {


    /**
     * проверить следующий метод - Логин с карточки Эксперта через кнопку Уведомить о доступности
     */

    public void loginSiteUser(String login, String password, WebDriver window) {

        //window.get(siteUrl);

        //findElementByXPath("//a[contains(.,'Вход')]", window).click();

        //подождать пока появиться форма логина
        //pauseUntilDisplayed(By.xpath(".//*[@id='login']//*[@id='user_models_User_phone']"), window);
        //ввести логин, пароль клиента, кликнуть Войти
        findElementById("user_models_User_phone", window).sendKeys(Constants.USER_LOGIN);
        findElementById("user_models_User_password", window).sendKeys(Constants.USER_PASSWORD);

        window.findElement((By.id("loginButton"))).click();

        //logger.info("Loggined user name: " + findElementByXPath("//span[@class='dropdown-current__value']", window).getText());
    }


    /**
     * Login into site.
     *
     * @param siteUrl  here should be PROD url or TEST url
     * @param login
     * @param password
     */

    //этот логин используется в классе чат, необходимо переписать
    public void loginSite(String siteUrl, String login, String password, WebDriver window) {

        window.get(siteUrl);

        findElementByXPath("//div[@class='header__top']//a[@href='#login']", window).click();
        pauseUntilDisplayed(By.xpath("//form[@id='login_form']//input[@id='user_models_User_email']"), window);
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_email']", window).sendKeys(Constants.EXPERT_LOGIN);
        findElementByXPath("//form[@id='login_form']//input[@id='user_models_User_password']", window).sendKeys(Constants.EXPERT_PASSWORD);
        findElementByXPath("//*[@id='loginButton']", window).click();

        logger.info("Loggined user name: " + findElementByXPath("//span[@class='dropdown-current__value']", window).getText());
    }


    /**
     * Force server to delete the session.
     *
     * @param siteUrl
     */
    public void logout(String siteUrl, WebDriver window) {
        window.get(siteUrl + "logout");
    }

    /**
     * Найти эксперта со статусом Доступен сейчас и открыть большую карточку.
     * Если доступного эксперта не удалось найти, тест продолжит работать.
     * Ели эксперта удалось найти, но его карточка не открылась, тест упадет.
     *
     * @param window Окно в котором открывать
     * @return true - эксперт был найден, либо false - если эксперт не был найден
     */
    protected boolean clickOnAvailableExpert(WebDriver window) {
        if (window.findElements(By.xpath("//div[@class='card__status status-available']/following-sibling::a[@class='card__image card__image_round']")).size() > 0) {
            window.findElements(By.xpath("//div[@class='card__status status-available']/following-sibling::a[@class='card__image card__image_round']")).get(0).click();
            //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
            pauseUntilFound(By.xpath("//*[@class='product__title']"), DEFAULT_WAIT_TIME_SEC, window);
            logger.info("Найден Доступный эксперт :" + window.findElement(By.xpath("//*[@class='product__title']")).getText());
            return true;
        }
        logger.info("Не найдено ниодного Доступного Эксперта");
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
        if (window.findElements(By.xpath("//*[@class='btn btn-green btn-alpha']")).size() > 0) {
            window.findElements(By.xpath("//*[@class='btn btn-green btn-alpha']")).get(0).click();
            //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
            pauseUntilFound(By.xpath("//*[@class='product__title']"), DEFAULT_WAIT_TIME_SEC, window);
            logger.info("Найден Не Доступный эксперт :" + window.findElement(By.xpath("//*[@class='product__title']")).getText());
            return true;
        }
        logger.info("Не найдено ниодного Недоступного Эксперта");
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
        if (window.findElements(By.xpath(xpathForBusyExpert)).size() > 0) {
            window.findElements(By.xpath(xpathForBusyExpert)).get(0).click();
            //pauseUntilDisplayed подождать пока окно большой карты Эксперта появилось
            pauseUntilFound(By.xpath("//*[@class='product__title']"), DEFAULT_WAIT_TIME_SEC, window);
            logger.info("Найден Эксперт На Линии :" + window.findElement(By.xpath("//*[@class='product__title']")).getText());
            return true;
        }
        logger.info("Не найдено ниодного Эксперт На Линии");
        return false;
    }
}
