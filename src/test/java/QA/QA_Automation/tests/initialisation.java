package QA.QA_Automation.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v143.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import QA.QA_Automation.pages.LoginPage;
import QA.QA_Automation.pages.Registration;
import QA.QA_Automation.pages.cartPage;
import QA.QA_Automation.pages.contactUsPage;
import QA.QA_Automation.pages.FiltersAndsearchProduct;
import QA.QA_Automation.pages.verifySubscription;

public class initialisation {
	public WebDriver driver;
	public  Registration registration;
	public LoginPage login;
	
	/**
	 * Initializes and configures a Chrome WebDriver instance with DevTools integration.
	 * <p>
	 * This method performs the following steps:
	 * <ul>
	 *   <li>Launches a new {@link ChromeDriver} instance.</li>
	 *   <li>Creates a DevTools session for the driver.</li>
	 *   <li>Enables network tracking using the Chrome DevTools Protocol.</li>
	 *   <li>Blocks requests to common advertising and tracking domains 
	 *       (e.g., DoubleClick, Google Ads, Facebook trackers).</li>
	 *   <li>Sets an implicit wait timeout of 7 seconds for locating elements.</li>
	 *   <li>Maximizes the browser window.</li>
	 * </ul>
	 * By blocking ad and tracker domains, this configuration helps reduce 
	 * page clutter and improve test stability by preventing unwanted popups or banners.
	 * </p>
	 *
	 * @return a fully initialized {@link WebDriver} instance configured with
	 *         Chrome DevTools to block ads and trackers.
	 *
	 * @throws IOException if an I/O error occurs during driver initialization.
	 *
	 * @see org.openqa.selenium.WebDriver
	 * @see org.openqa.selenium.chrome.ChromeDriver
	 * @see org.openqa.selenium.devtools.DevTools
	 * @see org.openqa.selenium.devtools.v125.network.Network
	 */
	
	public WebDriver initializeDriver() throws IOException

	{
		
		//file downloading preferences eg invoices
		String downloadPath = System.getProperty("user.dir") + "/resources";
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadPath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("safebrowsing.enabled", true);
		

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		 // Launch Chrome
        ChromeDriver driver = new ChromeDriver(options);

        // Create DevTools session
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Enable network tracking
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));

        // Block common ad/tracker domains
        devTools.send(Network.setBlockedURLs(Optional.empty(),Optional.of(Arrays.asList(
                "*.doubleclick.net/*",
                "*.googlesyndication.com/*",
                "*.google-analytics.com/*",
                "*.adservice.google.com/*",
                "*.facebook.net/*",
                "*.adsense.com/*",
                "*.trackingdomain.com/*"
        ))));


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.manage().window().maximize();
		// properties class
		return driver; 

	}
	/**
	 * must execute first and must alway run
	 * after launching the appliation we return the landing page since it is the first page the site lands
	 * @return landingPage 
	 * @throws IOException
	 */
	//@BeforeMethod(alwaysRun = true)
	public Registration launchApplication() throws IOException
	{
		
		
		 driver = initializeDriver();
		 goToLandingPage();
		 registration = new Registration(driver);
		return registration;
	
	}
	public LoginPage launchApp() throws IOException
	{
		
		
		 driver = initializeDriver();
		 goToLandingPage();
		 login = new LoginPage(driver);
		return login;
	
	}
	public FiltersAndsearchProduct instantsearch() {
		FiltersAndsearchProduct search = new FiltersAndsearchProduct(driver);
		return search;
	}
	public contactUsPage instantContactUs() {
		contactUsPage contact = new contactUsPage(driver);
		return contact;
	}
	public verifySubscription instantSubscription() {
		verifySubscription subscribe = new verifySubscription(driver);
		return subscribe;
	}
	public cartPage instantCart() {
		cartPage cart = new cartPage(driver);
		return cart;
	}
	/**
	 * Navigate to landing page
	 */
	public void goToLandingPage() {
		driver.get("https://automationexercise.com/");
	}

	/**
	 * Must execute last
	 * shuts the down the browser
	 */
//	@AfterMethod(alwaysRun = true) //Must execute last
	public void tearDown()
	{
		driver.close();
	}
	

}
