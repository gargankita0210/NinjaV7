package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import testBase.BaseClass;
import utilities.HelperMethods;
import utilities.RetryAnalyzer;

public class TC04_CompleteCheckOut extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC04_CompleteCheckOut.class);

    @Test(groups = {"sanity", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testCheckOut() {
        logger.debug("Test started: testCheckOut");

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

            Thread.sleep(1000); // Ideally replace with WebDriverWait
            logger.debug("Waited 1 second after login");

            myaccount.clickMenuItemLaptopsAndNoteBooks();
            logger.debug("Clicked on 'Laptops & Notebooks'");

            myaccount.clickSubMenuItemShowAll();
            logger.debug("Clicked 'Show All' under Laptops");

            HelperMethods helperMethods = new HelperMethods();
            helperMethods.scrollWindow(getDriver());
            logger.debug("Scrolled down the window");

            refineSearchPage.clickProduct_HPLP3065();
            logger.debug("Selected product 'HP LP3065'");

            String formatDate = helperMethods.getformattedDate();
            logger.debug("Formatted delivery date: " + formatDate);

            addToCart.setDeliveryDate(formatDate);
            logger.debug("Set delivery date");

            addToCart.clickAddtoCart();
            logger.debug("Clicked 'Add to Cart'");

            // Validate item was added
            String alertText = addToCart.getAlertText();
            logger.debug("Alert text: " + alertText);
            Assert.assertTrue(alertText.contains("Success"), "Item was not added successfully");
            logger.info("Item added to cart successfully");

            addToCart.clickCheckOut();
            logger.debug("Clicked 'Checkout'");

            checkOutPage.selectShippingAddress();
            logger.debug("Selected shipping address");

            checkOutPage.chooseShippingMethod();
            checkOutPage.clickShippingContinue();
            logger.debug("Selected and continued shipping method");

            checkOutPage.choosePayMethod();
            checkOutPage.clickPaymentContinue();
            logger.debug("Selected and continued payment method");

            checkOutPage.clickConfirmOrder();
            logger.debug("Clicked 'Confirm Order'");

            // Validate order confirmation
            String confirmationMsg = checkOutPage.getOrderConfirmMsg();
            logger.debug("Order confirmation message: " + confirmationMsg);
            Assert.assertEquals(confirmationMsg, "Your order has been placed!", "Order confirmation failed");
            logger.info("Order placed successfully");

        } catch (AssertionError ae) {
            logger.error("Assertion failed in testCheckOut", ae);
            Assert.fail("Assertion failed: " + ae.getMessage());
        } catch (Exception e) {
            logger.error("Exception occurred in testCheckOut", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.debug("Test ended: testCheckOut");
    }
}
