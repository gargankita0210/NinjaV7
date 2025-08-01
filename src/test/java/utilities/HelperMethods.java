package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class HelperMethods {
	
	public void scrollWindow(WebDriver driver) {
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

	}
	
	
	public String getformattedDate() {
		LocalDate currentDate = LocalDate.now().plusDays(5);
		System.out.println(currentDate);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatdate = currentDate.format(formatter);
		
		System.out.println(formatdate);
		
		return formatdate;
		
	}
	

}
