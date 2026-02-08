package QA.QA_Automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import QA.QA_Automation.base.abstractBase;
	
	public class landingPage extends abstractBase {
		WebDriver driver;
		public landingPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	/**
	 * Navigate to landing page
	 */
	public void goToLandingPage() {
		driver.get("https://automationexercise.com/");
		
	}

}
