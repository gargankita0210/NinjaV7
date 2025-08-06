package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.net.URI;
import org.openqa.selenium.remote.RemoteWebDriver;


import pageObjects.*;

public class BaseClass {

	private static final Logger logger = LogManager.getLogger(BaseClass.class);

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public HomePage homePage;
	public LoginPage loginPage;
	public MyAccountPage myaccount;
	public RefineSearchPage refineSearchPage;
	public AddToCartPage addToCart;
	public CheckOutPage checkOutPage;
	public AffiliateLinkPage affiliatepage;
	public Properties prop;

	public static WebDriver getDriver() {
		return driver.get();
	}

	@BeforeClass(groups = {"sanity", "regression", "datadriven"})
	@Parameters({"browser", "os"})
	public void openApp(String browser, String os) throws IOException {
		logger.debug("Starting openApp with browser: {} and OS: {}", browser, os);
		
		try (FileReader file = new FileReader(".//src//test//resources//config.properties")) {
			prop = new Properties();
			prop.load(file);
			logger.debug("Loaded config properties successfully.");
		} catch (IOException e) {
			logger.error("Failed to load config.properties file", e);
			throw e;
		}

		WebDriver localDriver = null;
		
		
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("macOS")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("Remote condition ,No matching os");
				return;
			}

			String gridURL = "http://192.168.0.23:4444"; // Update if needed
			//String gridURL = "http://192.168.86.36:4444/wd/hub"; // this will also work
			

			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), chromeOptions.merge(capabilities));
				break;

			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), firefoxOptions.merge(capabilities));
				break;

			case "safari":
				SafariOptions edgeOptions = new SafariOptions();
				localDriver = new RemoteWebDriver(URI.create(gridURL).toURL(), edgeOptions.merge(capabilities));
				break;

			default:
				logger.error("No matching browser found: {}", browser);
				return;
			}

		}
		
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch (browser.toLowerCase()) {
			case "chrome":
				localDriver = new ChromeDriver();
				logger.debug("ChromeDriver initialized");
				break;
			case "safari":
				localDriver = new SafariDriver();
				logger.debug("SafariDriver initialized");
				break;
			case "firefox":
				localDriver = new FirefoxDriver();
				logger.debug("FirefoxDriver initialized");
				break;
			default:
				logger.error("Unsupported browser: {}", browser);
				return;
		}
		}
		driver.set(localDriver);
		logger.debug("Driver assigned to thread-local storage");
		
		// Page objects
		try {
			homePage = new HomePage(getDriver());
			loginPage = new LoginPage(getDriver());
			myaccount = new MyAccountPage(getDriver());
			refineSearchPage = new RefineSearchPage(getDriver());
			addToCart = new AddToCartPage(getDriver());
			checkOutPage = new CheckOutPage(getDriver());
			affiliatepage = new AffiliateLinkPage(getDriver());
		} catch (Exception e) {
			logger.error("Error initializing page objects: ", e);
		}
	
			String appUrl = prop.getProperty("appURL");
			getDriver().get(appUrl);
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			logger.debug("Application launched at URL: {}", appUrl);

		} 
	

	public String captureScreen(String tname) throws IOException{
		String targetFilePath = null;
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File sourceFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

			targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);

			if (sourceFile.renameTo(targetFile)) {
				logger.debug("Screenshot saved to {}", targetFilePath);
			} else {
				logger.warn("Failed to rename screenshot file.");
			}
		} catch (Exception e) {
			logger.error("Unexpected error during screenshot capture: ", e);
		}
		return targetFilePath;
	}

	@AfterClass(groups = {"sanity", "regression", "datadriven"})
	public void closeApp() {
		try {
			getDriver().close();
			logger.debug("Browser closed for thread: {}", Thread.currentThread().getId());
		} catch (Exception e) {
			logger.error("Error closing browser: ", e);
		}
	}
}
