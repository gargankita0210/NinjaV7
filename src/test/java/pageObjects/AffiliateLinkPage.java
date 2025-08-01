package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AffiliateLinkPage extends BasePage {
	
	
	//Constructor
	
    public AffiliateLinkPage( WebDriver driver)
	{
		super(driver);	
	}
	
//Locators

	@FindBy(xpath ="//a[normalize-space()='Affiliate']")
	WebElement link_affiliate;
	
	@FindBy(xpath ="//input[@id='input-company']")
	WebElement element_company;
	
	@FindBy(xpath ="//input[@id='input-website']")
	WebElement element_website;
	
	@FindBy(xpath ="//input[@id='input-tax']")
	WebElement element_tax;
	
	@FindBy(xpath ="//input[@id='input-cheque']")
	WebElement element_cheque;
	
	@FindBy(xpath ="//button[normalize-space()='Continue']")
	WebElement element_continue;
	
	@FindBy(css =".alert.alert-success.alert-dismissible")
	WebElement element_alert;

	

public void clickAffiliate() throws InterruptedException {
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link_affiliate);
	Thread.sleep(500);
	link_affiliate.click();

	
}
	
	public void setCompany() {
		element_company.clear();
		element_company.sendKeys("CloudBerry");

	}
	
	public void setWebsite() {
		element_website.clear();
		element_website.sendKeys("cloudberry.services");

	}
	
	public void setTax() {
		element_tax.clear();
		element_tax.sendKeys("123456");

	}
	
	public void setCheque() {
		element_cheque.clear();
		element_cheque.sendKeys("Ankita Garg");

	}
	
	public void clickContinue() throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element_continue);
		Thread.sleep(500);
		element_continue.click();

		
	}
	
	public boolean checkAlertbox() {
		return element_alert.isDisplayed();
		
	}
	
}