package automation;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;

/**
 * Class contains base, low level operation.
 * <p>
 * Created by roman on 07.02.15.
 */
public class Base {

    public static Logger logger = Logger.getLogger(Base.class.getName());

    protected static int DEFAULT_WAIT_TIME_SEC = 15;

    protected Set<WebDriver> drivers = new HashSet();

    private static Properties prop = new Properties();

    static {
        loadProperties();
    }

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
        switch (getSystemProperty("webdriver.type")){
            case "chrome":
                return getNewWindowChrome();
            case "firefox":
                return getNewWindowFirefox();
            default:
                return getNewWindowFirefox();
        }
    }

    /**
     * Открыть новое окно мозила
     *
     * @return
     */
    private WebDriver getNewWindowFirefox() {
        WebDriver driver = new FirefoxDriver();
        WebDriver.Options options = driver.manage();
        WebDriver.Window window = options.window();

        // Make sure all elements are visible.
        window.maximize();
        drivers.add(driver);
        return driver;
    }

        /**
         * Открыть новое окно Chrome.
         *
         * @return
         */
    private WebDriver getNewWindowChrome() {
        setOSDriverPath();

        WebDriver driver = new ChromeDriver();

        WebDriver.Options options = driver.manage();
        WebDriver.Window window = options.window();

        // Make sure all elements are visible.
        window.maximize();
        drivers.add(driver);
        return driver;
    }

    private static void loadProperties() {
        try(InputStream input = new FileInputStream("target/test-classes/config.properties")) {
            prop.load(input);
        } catch (FileNotFoundException e) {
            logger.warning("config.properties is not found. " + e.getMessage());
        } catch (IOException e) {
            logger.warning("config.properties can not be read. " + e.getMessage());
        }
    }

    public static String getSystemProperty(String key){
        return prop.getProperty(key, System.getProperty(key));
    }

    /**
     * Choose appropriate selenium web driver
     */
    public void setOSDriverPath(){
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("win")){
            System.setProperty("webdriver.chrome.driver", "target/test-classes/webdrivers/windows/chromedriver.exe");
        } else if(osName.contains("mac")){
            System.setProperty("webdriver.chrome.driver", "target/test-classes/webdrivers/mac/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "target/test-classes/webdrivers/linux/chromedriver");
        }

    }
}
