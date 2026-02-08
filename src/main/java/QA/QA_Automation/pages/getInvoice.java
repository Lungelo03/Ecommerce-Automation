package QA.QA_Automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import QA.QA_Automation.base.abstractBase;

public class getInvoice extends abstractBase{
	WebDriver driver;
	public getInvoice(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = ".btn.btn-default.check_out")
	WebElement btnInvoice;
	@FindBy(linkText = "Continue")
	WebElement Continue;
	
	public void DownloadInvoice() {
		btnInvoice.click();
		waitAmoment(5);
		Continue.click();
	}
	
	
}
