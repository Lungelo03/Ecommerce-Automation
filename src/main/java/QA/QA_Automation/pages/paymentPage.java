package QA.QA_Automation.pages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QA.QA_Automation.base.abstractBase;

public class paymentPage extends abstractBase{
	WebDriver driver;
	public paymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "name_on_card")
	WebElement name;
	@FindBy(name = "card_number")
	WebElement cardNumber;
	@FindBy(name = "cvc")
	WebElement cvc;
	@FindBy(name = "expiry_month")
	WebElement expiryMonth;
	@FindBy(name = "expiry_year")
	WebElement expiryYear;
	@FindBy( id = "submit")
	WebElement btnSumit;
	@FindBy(css = "h2[class='title text-center'] b")
	WebElement confirmOrder;
	public getInvoice confirmPayment(HashMap<String, String> paymentInformation) {
		name.sendKeys(paymentInformation.get("name"));
		cardNumber.sendKeys(paymentInformation.get("cardNumber"));
		cvc.sendKeys(paymentInformation.get("cvc"));
		expiryMonth.sendKeys(paymentInformation.get("expiryMonth"));
		expiryYear.sendKeys(paymentInformation.get("expiryYear"));
		btnSumit.click();
		waitAmoment(2);
		Assert.assertEquals("ORDER PLACED!", confirmOrder.getText());
		
		return new getInvoice(driver);
		
	} 
}
