package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	// Constructor
			WebDriver driver;
		    WebDriverWait wait;


		    BasePage(WebDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver, this);
		        wait = new WebDriverWait(driver, Duration.ofSeconds(20));


			}

}
