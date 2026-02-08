package QA.QA_Automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QA.QA_Automation.base.abstractBase;

public class verifySubscription extends abstractBase {
	WebDriver driver;
	public verifySubscription(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".col-sm-8 a[href='/']")
	WebElement home;
	@FindBy(css = ".single-widget h2")
	WebElement SubscriptionHeader;
	@FindBy(id = "susbscribe_email")
	WebElement enterEmail;
	@FindBy(id = "subscribe")
	WebElement btnSuscribe;
	public void subscribeHomePage(String email) {
		home.click();
		waitAmoment(3);
		Assert.assertTrue(SubscriptionHeader.isDisplayed());
		enterEmail.sendKeys(email);
		btnSuscribe.click();
		
	}
	@FindBy(css = "a[href='/view_cart']")
	WebElement cart;
	public void subscribeCartPage(String email) {
		cart.click();
		waitAmoment(3);
		Assert.assertTrue(SubscriptionHeader.isDisplayed());
		enterEmail.sendKeys(email);
		btnSuscribe.click();
		
	}

}
