package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC01_LaunchApplication extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC01_LaunchApplication.class);

    @Test(groups = {"sanity", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void launchApp() {
        logger.debug("Test started: launchApp");

        try {
            String actualTitle = homePage.getTitle();
            logger.debug("Retrieved page title: " + actualTitle);

            Assert.assertEquals(actualTitle, "Your store of fun");
            logger.debug("Assertion passed: Title matched expected value");

        } catch (AssertionError e) {
            logger.error("Assertion failed: Page title did not match", e);
            Assert.fail("Test failed due to assertion error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected exception occurred", e);
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage());
        }

        logger.debug("Test ended: launchApp");
    }
}
