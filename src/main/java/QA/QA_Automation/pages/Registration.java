package QA.QA_Automation.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import QA.QA_Automation.base.abstractBase;

public class Registration extends abstractBase{
	
	WebDriver driver;
	LoginPage logg;
	public Registration(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//a[@href='/login']")
	WebElement register;
	@FindBy(css = "div[class='signup-form'] h2" )
	WebElement VerifyText1;
	@FindBy(xpath="//input[@placeholder='Name']")
	WebElement Name;
	@FindBy(xpath="//input[@data-qa='signup-email']")
	WebElement Email;
	@FindBy(css="button[data-qa='signup-button']")
	WebElement Signup;
	@FindBy(xpath="//h2[b[text()='Enter Account Information']]")
	WebElement VerifyTex2;
	@FindBy(xpath="//h2[b[text()='Account Created!']]")
	WebElement VerifyTex3;
	@FindBy(xpath = "//a[@data-qa='continue-button']")
	WebElement btncontinue;
	@FindBy(xpath = "//a[i[contains(@class,'fa-user')]]")
	WebElement user;
	@FindBy(xpath = "(//p[normalize-space()='Email Address already exist!'])[1]")
	WebElement existingUser;
	/**
	 * answers Testcase 1 & 5: Register user
	 * @param name
	 * @param email
	 * @return login 
	 * @throws InterruptedException 
	 */
	public ProductsPage Register(HashMap<String, String> accountInformation,  HashMap<String, String> addressInformation) throws InterruptedException {
		register.click(); 
		//wait for the following page to load completely
		waitAmoment(4);
		String isDisplayed = VerifyText1.getText();
		Assert.assertTrue(isDisplayed.equalsIgnoreCase("New User Signup!"));
		Name.sendKeys(accountInformation.get("name")); 
		Email.sendKeys(accountInformation.get("email")); 
		Signup.click();
		// wait for the regesiter page to load completetly
		waitAmoment(4);;
		By emailExistsMsg = By.xpath("//p[contains(text(),'Email Address already exist')]");
		List<WebElement> errorMsg = driver.findElements(emailExistsMsg);
		
		if (!errorMsg.isEmpty() && errorMsg.get(0).isDisplayed()) {
		    // Email already registered → switch to Login
			logg = new LoginPage(driver);
		    System.out.println("User already exists. Switching to Login.");
		    logg.Login(accountInformation);
		
		}else {
		Assert.assertTrue(VerifyTex2.getText().equalsIgnoreCase("Enter Account Information")); 
		accountInfo(accountInformation.get("gender"),accountInformation.get("password"),accountInformation.get("day"),
				accountInformation.get("month"),accountInformation.get("year")); // acc info
		addressInfo(accountInformation.get("name"),addressInformation.get("lastName"),addressInformation.get("address"),addressInformation.get("country")
				,addressInformation.get("state"),addressInformation.get("city"),addressInformation.get("zipCode"),addressInformation.get("number")); // address info
		//verify if accout is created
		waitAmoment(4);
		Assert.assertTrue(VerifyTex3.getText().equalsIgnoreCase("Account Created!"));
	
		btncontinue.click();
		Assert.assertTrue(user.isDisplayed());
		}
		ProductsPage products  = new ProductsPage(driver); 
		
		return products;
		
	}
	
	@FindBy(id =  "id_gender1")
	WebElement Male;
	@FindBy(id =  "id_gender2")
	WebElement Female;
	@FindBy(id = "password")
	WebElement password;
	@FindBy(id = "days")
	WebElement days;
	@FindBy(name  = "months")
	WebElement months;
	@FindBy(name  = "years")
	WebElement years;
	@FindBy(xpath = "//input[@id='optin']")
	WebElement optin;
	@FindBy(xpath = "//input[@id='newsletter']")
	WebElement newsletter;
	/**
	 * helper method to complete the registration method
	 * @param gender
	 * @param password
	 */
	private void accountInfo(String Gender, String Password,String Day, String Month, String Year) {
		if(Gender.equalsIgnoreCase("Male")) {
			Male.click();
		}else if(Gender.equalsIgnoreCase("Female")) {
			Female.click();
		}
		
		password.sendKeys(Password);
		//use select classs to select dropdowns
		//REMEMBER NOT TO HARD CODE
		Select Days = new Select(days);
		Days.selectByVisibleText(Day);
		Select Months = new Select(months);
		Months.selectByVisibleText(Month);
		Select Years = new Select(years);
		Years.selectByVisibleText(Year);
		optin.click();
		newsletter.click();
	}
	
	@FindBy(id = "first_name")
	WebElement firstName;
	@FindBy(id = "last_name")
	WebElement lastName;
	@FindBy(xpath = "//input[@id='address1']")
	WebElement address1;
	@FindBy(id = "country")
	WebElement country;
	@FindBy(id = "state")
	WebElement state;
	@FindBy(id = "city")
	WebElement city;
	@FindBy(id = "zipcode")
	WebElement zipcode;
	@FindBy(id = "mobile_number")
	WebElement number;
	@FindBy(css = "button[data-qa='create-account']")
	WebElement createAccount;
	/**
	 * 
	 * @param name
	 * @param LastName
	 */
	private void addressInfo(String name ,String LastName, String Address1, String Country
			,String State ,String City, String ZipCode, String Number) {
		firstName.sendKeys(name);
		lastName.sendKeys(LastName);
		address1.sendKeys(Address1);
		Select ctry = new Select(country);
		ctry.selectByValue(Country);
		state.sendKeys(State);
		city.sendKeys(City);
		zipcode.sendKeys(ZipCode);
		number.sendKeys(Number);
		createAccount.click();
		
	}
	@FindBy(css ="a[href='/logout']")
	WebElement logout;
	public void logout() {
		logout.click();
		
	}
	
	
}
