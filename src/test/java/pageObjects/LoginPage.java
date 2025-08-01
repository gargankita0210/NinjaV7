package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	//Constructor
		public LoginPage( WebDriver driver)
		{
			super(driver);
			
		}
		
		//locators
		
		//driver.findElement(By.id("input-email")).sendKeys(login);
		//driver.findElement(By.id("input-password")).sendKeys(password);
		//driver.findElement(By.xpath("//button[@type='submit'][text()='Login']")).click();
		
		@FindBy(id="input-email")
		WebElement text_email;
		
		@FindBy(id="input-password")
		WebElement text_password;
		
		@FindBy(xpath="//button[@type='submit'][text()='Login']")
		WebElement button_submit;


		//Actions
		
		public void setEmail(String login){
			text_email.sendKeys(login);
			
		}
		
		public void setPassword(String password){
			text_password.sendKeys(password);
			
		}
		
		public void click_login(){
			button_submit.click();
			
		}

}
