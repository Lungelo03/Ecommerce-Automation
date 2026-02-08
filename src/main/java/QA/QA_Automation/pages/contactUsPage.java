package QA.QA_Automation.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QA.QA_Automation.base.abstractBase;

public class contactUsPage extends abstractBase {
	WebDriver driver;
	public contactUsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "a[href='/contact_us']")
	WebElement contactus;
	@FindBy(css = ".contact-form h2")
	WebElement intouch;
	@FindBy(xpath = "//input[@placeholder='Name']")
	WebElement name;
	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement email;
	@FindBy(xpath = "//input[@placeholder='Subject']")
	WebElement subject;
	@FindBy(id = "message")
	WebElement message;
	@FindBy(css = "input[name='upload_file']")
	WebElement fileUpload;
	@FindBy(css = "input[value='Submit']")
	WebElement submit;
	
	/**
	 * Fills out and submits the "Contact Us" form using the provided input data.
	 *
	 * <p>This method clicks the Contact Us section, verifies that the form is displayed,
	 * populates all required fields, uploads a file, submits the form, and finally
	 * handles the confirmation alert by reading and accepting it.</p>
	 *
	 * <p>The {@code inTouch} map is expected to contain the following keys:
	 * <ul>
	 *   <li><b>name</b> – sender's name</li>
	 *   <li><b>email</b> – sender's email address</li>
	 *   <li><b>subject</b> – message subject</li>
	 *   <li><b>message</b> – message body</li>
	 *   <li><b>filedir</b> – absolute path to the file to upload</li>
	 * </ul>
	 *
	 * @param inTouch a {@link HashMap} containing contact form input values
	 */
	public void contactUs(HashMap<String, String> inTouch) {
		contactus.click();
		waitAmoment(2);
		Assert.assertTrue(intouch.isDisplayed());
		name.sendKeys(inTouch.get("name"));
		email.sendKeys(inTouch.get("email"));
		subject.sendKeys(inTouch.get("subject"));
		message.sendKeys(inTouch.get("message"));
		fileUpload.sendKeys(inTouch.get("filedir"));
		submit.click();
		waitAmoment(1);
		driver.switchTo().alert().getText(); // to get text on the alert
        driver.switchTo().alert().accept(); // to accept alert
	}
	@FindBy(css = "a[href = '/test_cases']")
	WebElement testcases;
	/**
	 * navigate to test cases
	 */
	public void toTestCases() {
		testcases.click();
	}
	

}
