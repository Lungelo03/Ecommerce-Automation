package QA.QA_Automation.pages;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QA.QA_Automation.base.abstractBase;

public class LoginPage extends abstractBase {
   WebDriver driver;
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[@href='/login']")
	WebElement register;
	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement email;
	@FindBy(name ="password")
	WebElement password;
	@FindBy(css = "button[data-qa='login-button']")
	WebElement btnlogin;
	@FindBy(xpath = "//a[i[contains(@class,'fa-user')]]")
	WebElement user;
	@FindBy(css = "a[href='/delete_account']")
	WebElement deleteacc;
	@FindBy(xpath="//h2[@data-qa='account-deleted']")
	WebElement VerifyTex4;
	@FindBy(css ="a[href='/logout']")
	WebElement logout;
	/**
	 * user enters credintials to login
	 * @param Email
	 * @param Password
	 * @return products page
	 * @throws InterruptedException 
	 */
	public ProductsPage Login(HashMap<String, String> accountInformation) {
		register.click();
		email.sendKeys(accountInformation.get("email"));
		password.sendKeys(accountInformation.get("password"));
		btnlogin.click();
		//wait for the following page to load completely
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		Assert.assertTrue(user.isDisplayed()); //1.16 . Verify that 'Logged in as username' is visible
		//PAUSE For NOW
		/**
		deleteacc.click();	
		waitForWebElementToAppear(VerifyTex4);
		//assert.assertTrue(VerifyTex4.isDisplayed());
		System.out.println(VerifyTex4.isDisplayed()); 
		logout.click();
		 **/
		
		ProductsPage products = new ProductsPage(driver);
		return products;
	}
	@FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
	WebElement invalidText;
	/**
	 * user enters invalid credintials to login
	 * @param Email
	 * @param Password
	 * @return products page
	 * @throws InterruptedException 
	 */
	public void invalidLogin(HashMap<String, String> accountInformation) {
		register.click();
		email.sendKeys(accountInformation.get("email"));
		password.sendKeys(accountInformation.get("password"));
		btnlogin.click();
		Assert.assertTrue(invalidText.isDisplayed());
		
	}
	
	public void logout() {
		logout.click();
		
	}
	
	public void deleteAccount() {
		Assert.assertTrue(user.isDisplayed()); //verify if the logged in user icon with the user name is displayed
		deleteacc.click();	
		waitForWebElementToAppear(VerifyTex4);
		Assert.assertTrue(VerifyTex4.isDisplayed());
	}

}
	
