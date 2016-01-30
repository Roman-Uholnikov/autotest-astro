package automation;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Class contains base, low level operation.
 * <p>
 * Created by roman on 07.02.15.
 */
public class Base {

    public static Logger logger = Logger.getLogger(Base.class.getName());

    protected static int DEFAULT_WAIT_TIME_SEC = 15;

    protected Set<WebDriver> drivers = new HashSet();

    @After
    public void tearDown() {
        drivers.forEach(p -> {
            p.get(Constants.SITE_URL + "/logout");
            p.quit();
        });
    }

    /**
     * Wait until element will be available, but no more that specified time.
     *
     * @param seconds amount of seconds to wait element
     * @param locator XPath of element which is expected
     */
    public void pauseUntilFoundByXPath(String locator, long seconds, WebDriver window) {
        new WebDriverWait(window, seconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
    }


    public void pauseUntilFound(By locator, long seconds, WebDriver window) {
        new WebDriverWait(window, seconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait until element will be available, but no more {@link Base#DEFAULT_WAIT_TIME_SEC} seconds.
     *
     */
    public void pauseUntilDisplayed(By locator, WebDriver window) {
        boolean displayed = false;
        while (!displayed) {
            try {
                WebElement element = findElement(locator, window);
                if (element.isDisplayed()) {
                    displayed = true;
                    return;
                }
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Find element by XPath, wait no more than {@link Base#DEFAULT_WAIT_TIME_SEC}
     *
     * @param xpath
     * @return WebElement
     * @throws org.openqa.selenium.NoSuchElementException
     */
    public WebElement findElementByXPath(String xpath, WebDriver window) {
        pauseUntilFoundByXPath(xpath, DEFAULT_WAIT_TIME_SEC, window);
        return window.findElement(By.xpath(xpath));
    }

    /**
     * Find element by XPath, wait no more than specified time
     *
     * @param id
     * @return WebElement
     * @throws org.openqa.selenium.NoSuchElementException
     */
    public WebElement findElementById(String id, WebDriver window) {
        //пауза
        new WebDriverWait(window, DEFAULT_WAIT_TIME_SEC).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        return window.findElement(By.id(id));
    }




    /**
     * Find element by XPath, wait no more than {@link Base#DEFAULT_WAIT_TIME_SEC}
     *
     * @return WebElement
     * @throws org.openqa.selenium.NoSuchElementException
     */
    public WebElement findElement(By locator, WebDriver window) {
        pauseUntilFound(locator, DEFAULT_WAIT_TIME_SEC, window);
        return window.findElement(locator);
    }

    /**
     * Find element by XPath, wait no more than specified time
     *
     * @param xpath
     * @return WebElement
     * @throws org.openqa.selenium.NoSuchElementException
     */
    public WebElement findElementByXPath(String xpath, int seconds, WebDriver window) {
        pauseUntilFoundByXPath(xpath, seconds, window);
        return window.findElement(By.xpath(xpath));
    }

    /**
     * Find element by XPath
     *
     * @param xpath
     * @return WebElement
     * @throws org.openqa.selenium.NoSuchElementException
     */
    public boolean isElementExistByXPath(String xpath, int seconds, WebDriver window) {
        WebElement element = null;
        try {
            pauseUntilFoundByXPath(xpath, seconds, window);
            element = window.findElement(By.xpath(xpath));
        } catch (NoSuchElementException ex) {
            logger.warning(MessageFormat.format("Can not get element by xpath {0}, {1}", xpath, ex.getMessage()));
        }
        return element != null;
    }

    /**
     * Открыть новое окно инкогнито.
     *
     * @return
     */
    public WebDriver getNewWindow() {
        WebDriver driver = new FirefoxDriver();
        WebDriver.Options options = driver.manage();
        WebDriver.Window window = options.window();

        // Make sure all elements are visible.
        window.maximize();
        drivers.add(driver);
        return driver;
    }
}
