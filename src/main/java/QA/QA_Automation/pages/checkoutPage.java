package QA.QA_Automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QA.QA_Automation.base.abstractBase;

public class checkoutPage extends abstractBase {
	WebDriver driver;
	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "message")
	WebElement message;
	@FindBy(css = ".check_out")
	WebElement placeOrder;
	public paymentPage checkoutItems(String Message) {
		verifyCartTotals();
		message.sendKeys(Message);
		placeOrder.click();
		paymentPage payment = new paymentPage(driver);
		return payment;
	}
	
	@FindBy(css = "tbody tr[id^='product-']")
	 List<WebElement> rows;
	@FindBy(css  = "tr:last-child .cart_total_price")
	WebElement grandTotal;
	
	/**
	 * Verifies that the grand total displayed in the cart matches
	 * the sum of the total prices of all individual cart items.
	 *
	 * <p>This method iterates through each cart row, extracts the
	 * item total amount, converts it to an integer value, and
	 * accumulates the sum. It then compares the calculated sum
	 * against the grand total displayed in the checkout section.</p>
	 *
	 * <p>The assertion will fail if there is any mismatch between
	 * the calculated total and the displayed grand total, indicating
	 * an incorrect cart calculation.</p>
	 */
	private void verifyCartTotals() {

	    int calculatedGrandTotal = 0;

	    for (WebElement row : rows) {

	        String totalText = row.findElement(By.cssSelector(".cart_total_price")).getText();
	        int total = Integer.parseInt(totalText.replace("Rs.", "").trim());
	        calculatedGrandTotal += total;
	    }
	    // Get displayed grand total
	    String grandTotalElement = grandTotal.getText();

	    int displayedGrandTotal = Integer.parseInt(grandTotalElement.replace("Rs.", "").trim());
	    // Assert if total amount is equal the sum  of the items price in checkout
	    Assert.assertEquals(calculatedGrandTotal, displayedGrandTotal);
	    
	    
	}
}

