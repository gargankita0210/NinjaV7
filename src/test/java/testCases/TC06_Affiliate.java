package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC06_Affiliate extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC06_Affiliate.class);


    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testAffiliate() {
        logger.debug("Test started: testAffiliate");

        try {
        	
        	logger.debug("This is a DEBUG log");
            logger.info("This is an INFO log");
            logger.error("This is an ERROR log");
            homePage.clickMyAccount();
            logger.debug("Clicked 'My Account'");

            homePage.clickLinkLogin();
            logger.debug("Clicked 'Login'");

            loginPage.setEmail("gargankita0210@gmail.com");
            logger.debug("Entered email");

            loginPage.setPassword("Learning@123");
            logger.debug("Entered password");

            loginPage.click_login();
            logger.debug("Clicked 'Login' button");

            Thread.sleep(500); // Ideally use WebDriverWait
            logger.debug("Waited 0.5 seconds before proceeding to Affiliate section");

            affiliatepage.clickAffiliate();
            logger.debug("Clicked on 'Affiliate' link");

            affiliatepage.setCompany();
            logger.debug("Set company");

            affiliatepage.setWebsite();
            logger.debug("Set website");

            affiliatepage.setTax();
            logger.debug("Set tax information");

            affiliatepage.setCheque();
            logger.debug("Set cheque payment method");

            Thread.sleep(500); // Small wait for form stability
            logger.debug("Waited 0.5 seconds before clicking Continue");

            affiliatepage.clickContinue();
            logger.debug("Clicked 'Continue' button");

            boolean alertDisplayed = affiliatepage.checkAlertbox();
            logger.debug("Alert box status: " + alertDisplayed);

            AssertJUnit.assertTrue(alertDisplayed);
            logger.info("Affiliate registration success verified");

        } catch (AssertionError ae) {
            logger.error("Assertion failed in testAffiliate", ae);
            Assert.fail("Assertion failed: " + ae.getMessage());
        } catch (Exception e) {
            logger.error("Exception occurred in testAffiliate", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("Test ended: testAffiliate");
    }
}
