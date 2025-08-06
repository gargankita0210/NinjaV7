package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {

	// Constructor

	public MyAccountPage(WebDriver driver) {
		super(driver);

	}

	// Locators

	@FindBy(tagName = "h1")
	WebElement confirmation_textMsg;

	@FindBy(xpath = "//a[text()='Laptops & Notebooks']")
	WebElement Link_menuItem_LaptopsAndNoteBooks;

	@FindBy(xpath = "//a[text()='Show All Laptops & Notebooks']")
	WebElement Link_submenuItem_showAll;;

	// Added logout steps to achieve login from differenet creds
	@FindBy(xpath = "//li[@class='list-inline-item']//i[@class='fa-solid fa-caret-down']")
	WebElement Link_MyAccount;
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement Link_Logout;

	// Action

	public String getAccountConfirmationMsg() {

		return confirmation_textMsg.getText();

	}

	public void clickMenuItemLaptopsAndNoteBooks() {

		wait.until(ExpectedConditions.visibilityOf(Link_menuItem_LaptopsAndNoteBooks));

		Link_menuItem_LaptopsAndNoteBooks.click();
	}

	public void clickSubMenuItemShowAll() {

		Link_submenuItem_showAll.click();
	}

	public void clickMyAccount() {

		Link_MyAccount.click();
	}

	public void clickLogout() {

		Link_Logout.click();
	}

}
