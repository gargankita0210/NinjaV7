package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.RetryAnalyzer;

public class TC02_Login extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC02_Login.class);

    @Test(groups = {"sanity", "regression", "datadriven"}, dataProvider = "LoginData", dataProviderClass = DataProviders.class, retryAnalyzer = RetryAnalyzer.class)
    public void testLogin(String userName, String password) {
        logger.debug("Test started: testLogin with userName=" + userName);

        try {
            HomePage homePage = new HomePage(getDriver());
            logger.debug("HomePage object initialized");

            homePage.clickMyAccount();
            logger.debug("Clicked 'My Account'");

            homePage.clickLinkLogin();
            logger.debug("Clicked 'Login'");

            LoginPage loginPage = new LoginPage(getDriver());
            logger.debug("LoginPage object initialized");

            loginPage.setEmail(userName);
            logger.debug("Entered email");

            loginPage.setPassword(password);
            logger.debug("Entered password");

            loginPage.click_login();
            logger.debug("Clicked login");

            MyAccountPage myAccountPage = new MyAccountPage(getDriver());
            logger.debug("MyAccountPage object initialized");

            String confirmationMsg = myAccountPage.getAccountConfirmationMsg();
            logger.debug("Account confirmation message: " + confirmationMsg);

            Assert.assertEquals(confirmationMsg, "My Account", "Login verification failed");
            logger.info("Login successful for user: " + userName);

            // Logout after successful login
            myAccountPage.clickMyAccount();
            logger.debug("Clicked 'My Account' again");

            myAccountPage.clickLogout();
            logger.debug("Clicked 'Logout'");

            AssertJUnit.assertTrue(true);

        } catch (AssertionError ae) {
            logger.error("Assertion failed during login test for user: " + userName, ae);
            Assert.fail("Assertion failed: " + ae.getMessage());
        } catch (Exception e) {
            logger.error("Exception encountered during login test for user: " + userName, e);
            Assert.fail("Exception occurred: " + e.getMessage());
        }

        logger.debug("Test ended: testLogin for userName=" + userName);
    }
}
