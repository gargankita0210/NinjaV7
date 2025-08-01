package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddToCartPage extends BasePage{
	
	
		public AddToCartPage (WebDriver driver) {
			super(driver);
		}
		
		
		// Locators

		@FindBy(xpath = "//input[@id='input-option-225']")
		WebElement box_selectDate;
		
		@FindBy(xpath = "//button[text()='Add to Cart']")
		WebElement button_AddToCart;
		
		
		@FindBy(id = "alert")
		WebElement msg_alert;
		
		@FindBy(xpath = "//span[text()='Checkout']")
		WebElement link_checkOut;
		

		
		

		
		// Action

		public void setDeliveryDate(String formattedDate) {
			
			box_selectDate.clear();
			box_selectDate.sendKeys(formattedDate);


		}
		
		public void clickAddtoCart()
		{
			button_AddToCart.click();
		}

		
		public String getAlertText()
		{
			wait.until(ExpectedConditions.visibilityOf(msg_alert));
			return msg_alert.getText();
		}
		
		public void clickCheckOut() {
			
			link_checkOut.click();
		}

}
