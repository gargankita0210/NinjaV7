package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RefineSearchPage extends BasePage {

	// Constructor

	public RefineSearchPage(WebDriver driver) {
		super(driver);

	}

	// Locators

	@FindBy(xpath = "//a[contains(text(),'HP LP3065')]")
	WebElement link_Product_HPLP3065;
	
	@FindBy(xpath = "//h4/a[normalize-space()='HP LP3065']/ancestor::div[contains(@class, 'product-thumb')]//button[@title='Add to Wish List']")
	WebElement element_wishList;
	
	@FindBy(id = "alert")
	WebElement msg_alert;
	
	// Action

	public void clickProduct_HPLP3065() {

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", link_Product_HPLP3065);

	}
	
	public void clickAddtoWishList() throws InterruptedException {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_wishList);
		Thread.sleep(500);
		element_wishList.click();

		
	}
	
	public String getAlertText()
	{
		wait.until(ExpectedConditions.visibilityOf(msg_alert));
		return msg_alert.getText();
	}

}
