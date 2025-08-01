package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testBase.BaseClass;
import utilities.HelperMethods;
import utilities.RetryAnalyzer;

public class TC03_AddToCart extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC03_AddToCart.class);

    @Test(groups = {"sanity", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testAddToCart() {
        logger.debug("Test started: testAddToCart");

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
            logger.debug("Clicked login");

            Thread.sleep(1000); // Avoid if possible; prefer explicit waits
            logger.debug("Waited for 1 second");

            myaccount.clickMenuItemLaptopsAndNoteBooks();
            logger.debug("Clicked 'Laptops & Notebooks' menu");

            myaccount.clickSubMenuItemShowAll();
            logger.debug("Clicked 'Show All'");

            HelperMethods helperMethods = new HelperMethods();
            helperMethods.scrollWindow(getDriver());
            logger.debug("Scrolled window");

            refineSearchPage.clickProduct_HPLP3065();
            logger.debug("Clicked on product 'HP LP3065'");

            String formatDate = helperMethods.getformattedDate();
            logger.debug("Formatted date: " + formatDate);

            addToCart.setDeliveryDate(formatDate);
            logger.debug("Set delivery date");

            addToCart.clickAddtoCart();
            logger.debug("Clicked 'Add to Cart'");

            // Assertion
            String alertText = addToCart.getAlertText();
            logger.debug("Alert message: " + alertText);

            Assert.assertTrue(alertText.contains("Success"), "Item not added successfully");
            logger.info("Item successfully added to cart");

        } catch (AssertionError ae) {
            logger.error("Assertion failed during add-to-cart test", ae);
            Assert.fail("Assertion failed: " + ae.getMessage());
        } catch (Exception e) {
            logger.error("Exception occurred in add-to-cart test", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("Test ended: testAddToCart");
    }
}
