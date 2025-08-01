package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends BasePage {
	

	public CheckOutPage(WebDriver driver){	
		super(driver);
}
	
	
	@FindBy(id = "input-shipping-address")
	WebElement dropdown_shiipingAddress;
	
	@FindBy(xpath = "//button[@id='button-shipping-methods'][normalize-space()='Choose']")
	WebElement choose_shipingMethod;
	
	@FindBy(id = "button-shipping-method")
	WebElement shippingContinueButton;
	
	
	@FindBy(xpath = "//button[@id='button-payment-methods'][normalize-space()='Choose']")
	WebElement choose_payMethod;
	
	@FindBy(id = "button-payment-method")
	WebElement paymenyContinueButton;
	
	@FindBy(id = "button-confirm")
	WebElement button_confirm;
	
	@FindBy(tagName = "h1")
	WebElement order_confirmMsg;
	

	public void selectShippingAddress() {
		Select select = new Select(dropdown_shiipingAddress);
		select.selectByIndex(1);
		
	}
	
	public void chooseShippingMethod()
	{
		choose_shipingMethod.click();
	}
	
	
	public void clickShippingContinue()
	{
		wait.until(ExpectedConditions.elementToBeClickable(shippingContinueButton));
		shippingContinueButton.click();
	}
	
	public void choosePayMethod()
	{
		choose_payMethod.click();
	}
	
	public void clickPaymentContinue()
	{
		wait.until(ExpectedConditions.elementToBeClickable(paymenyContinueButton));

		paymenyContinueButton.click();
	}
	
	public void clickConfirmOrder() throws InterruptedException
	{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button_confirm);
		Thread.sleep(500);
        button_confirm.click();
		Thread.sleep(2000);
	}
	
	public String getOrderConfirmMsg() {
		return order_confirmMsg.getText();
	}
}
