package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC05_AddToWishlist extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC05_AddToWishlist.class);

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testAddToWishList() {
        logger.debug("Test started: testAddToWishList");

        try {
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

            Thread.sleep(1000); // Consider replacing with WebDriverWait
            logger.debug("Waited for 1 second");

            myaccount.clickMenuItemLaptopsAndNoteBooks();
            logger.debug("Clicked 'Laptops & Notebooks' menu");

            myaccount.clickSubMenuItemShowAll();
            logger.debug("Clicked 'Show All' under Laptops");

            refineSearchPage.clickAddtoWishList();
            logger.debug("Clicked 'Add to Wishlist' on product");

            String alertText = refineSearchPage.getAlertText();
            logger.debug("Alert text received: " + alertText);

            Assert.assertTrue(alertText.contains("Success"), "Wishlist addition failed");
            logger.info("Item successfully added to wishlist");

        } catch (AssertionError ae) {
            logger.error("Assertion failed in testAddToWishList", ae);
            Assert.fail("Assertion failed: " + ae.getMessage());
        } catch (Exception e) {
            logger.error("Exception occurred in testAddToWishList", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("Test ended: testAddToWishList");
    }
}
