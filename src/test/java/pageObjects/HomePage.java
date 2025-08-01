package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//Constructor
	public HomePage( WebDriver driver)
	{
		super(driver);
		
	}
	
	//locators
	
	@FindBy(css=".fa-solid.fa-user")
	WebElement link_myAccount;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement link_login;


	//Actions
	
	public void clickMyAccount(){
		
		link_myAccount.click();
		
		
	}
	
    public void clickLinkLogin(){
		
    	link_login.click();
		}

    
    public String getTitle()
    {
    	return driver.getTitle();
    }
}
