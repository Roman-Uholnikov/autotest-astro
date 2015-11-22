package automation.chat;

import automation.Constants;
import automation.TestHelper;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by roman on 07.02.15.
 */
public class LoginFormTest extends TestHelper {

    /**
     *
     */
    @Test
    public void testSubscriptionWithWrongEmail() {
        WebDriver expertWindow = getNewWindow();
        WebDriver clientWindow = getNewWindow();
        loginSite(Constants.SITE_URL,
                Constants.EXPERT_LOGIN,
                Constants.EXPERT_PASSWORD,
                expertWindow);

        clientWindow.get(Constants.SITE_URL);

        //Проверяем что мы залогиниись как Юля (имя не пустое)
        assertTrue(findElementByXPath("//span[@class='dropdown-current__value']", expertWindow) != null);

//        logout(Constants.SITE_URL, expertWindow);


    }

    /**
     * Tests login form (first case)
     *
     */
    @Test
    public void testSubscriptionWithoutEmail() {
        // TODO: write new test here
    }

}
