package automation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by roman on 07.02.15.
 */
public class LoginFormTest extends TestHelper {

    /**
     * Tests login form (first case)
     * <p>Prerequisites: user with {@link automation.Constants.Users#EXPERT_LOGIN} does not exist in the system<p/>
     */
    @Test
    public void testSubscriptionWithWrongEmail() {
        loginSite(Constants.Environments.PROD_SITE_URL,
                Constants.Users.EXPERT_LOGIN,
                Constants.Users.EXPERT_PASSWORD);

        //Checking
        //Проверяем что мы залогиниись как Юля (имя не пустое)
        assertTrue(findElementByXPath("//span[@class='dropdown-current__value']") != null);

        logout(Constants.Environments.PROD_SITE_URL);
    }

    /**
     * Tests login form (first case)
     * <p>Prerequisites: user with {@link automation.Constants.Users#EXPERT_LOGIN} does not exist in the system<p/>
     */
    @Test
    public void testSubscriptionWithoutEmail() {
        // TODO: write new test here
    }

}
