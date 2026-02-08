package QA.QA_Automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import QA.QA_Automation.base.abstractBase;

public class cartPage extends abstractBase {
	WebDriver driver;
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "a[href='/view_cart']")
	WebElement Cart;
	@FindBy(css = ".cart_menu")
	WebElement checkTable;
	@FindBy(css = "p[class='text-center'] a[href='/products']")
	WebElement toProducts;
	@FindBy(css= "p[class='text-center'] b")
	WebElement emCart;
	@FindBy(css = ".fa.fa-times")
	 WebElement remove;
	@FindBy(xpath="//table[@id='cart_info_table']/tbody/tr")
	List<WebElement> rows;
	public checkoutPage cartItems() {
		waitAmoment(3);
		if(checkTable.isDisplayed()) {
		//the following should happen iff the aare products in the cart else redirect the user back to th product page
		validateCartTotals();
		toCheckout();
		}else {
			toProducts.click();
		}
			
		checkoutPage checkout = new checkoutPage(driver);
		
		return checkout;
	}
	public void toCart() {
		Cart.click();
	}

	 
	public void removeProductFromCart(String productName) {

	    boolean productFound = false;

	    for (WebElement row : rows) {

	        String name = row.findElement(
	                By.xpath(".//td[@class='cart_description']/h4/a")
	        ).getText();

	        if (name.equalsIgnoreCase(productName)) {

	            WebElement deleteButton = row.findElement(
	                    By.xpath(".//a[contains(@class,'cart_quantity_delete')]")
	            );

	            deleteButton.click();
	            productFound = true;
	            waitAmoment(2);
	            break;
	        }
	    }

	    if (!productFound) {
	        throw new RuntimeException(
	                "Product not found in cart: " + productName
	        );
	    }
		
	}
	
	public ProductsPage emptyCart() {
		if(emCart.isDisplayed()) {
			toProducts.click();
		}else {
			cartItems();
	
		}
	 ProductsPage products = new ProductsPage(driver);
	 return products;
 }

    /**
     * Validates that the total price of each cart item
     * is correctly calculated as:
     * <pre>
     *     total = price × quantity
     * </pre>
     *
     * <p>
     * For every product row present in the cart table, this method:
     * <ul>
     *   <li>Extracts the item price</li>
     *   <li>Extracts the item quantity</li>
     *   <li>Extracts the displayed total</li>
     *   <li>Asserts that the calculated total matches the displayed total</li>
     * </ul>
     * </p>
     *
     * <p>
     * The price and total values are expected to be prefixed with the
     * currency string <b>"Rs."</b>, which is removed before calculation.
     * </p>
     *
     * @throws AssertionError
     *         if any cart item's total does not equal
     *         <code>price × quantity</code>
     */
//	@FindBy(xpath="//table[@id='cart_info_table']/tbody/tr")
	//List<WebElement> rowss;
	private void validateCartTotals() {
			//yes its the same element as above but we reload it again because changes were made to table and the DOM has the old table
			List<WebElement> rowss = driver.findElements(By.xpath("//table[@id='cart_info_table']/tbody/tr")); 
	        for (WebElement row : rowss) {
	            // Get price text (e.g. "Rs. 400")
	            String priceText = row.findElement(By.xpath(".//td[@class='cart_price']/p")).getText();
	            // Get quantity text (e.g. "4")
	            String quantityText = row.findElement(By.xpath(".//td[@class='cart_quantity']/button")).getText();
	            // Get total text (e.g. "Rs. 1600")
	            String totalText = row.findElement(By.xpath(".//td[@class='cart_total']/p")).getText();
	            // Convert to integers
	            int price = Integer.parseInt(priceText.replace("Rs.", "").trim());
	            int quantity = Integer.parseInt(quantityText.trim());
	            int total = Integer.parseInt(totalText.replace("Rs.", "").trim());

	            // Assertion
	            Assert.assertEquals(price * quantity,total);
	        }
	    }
	 @FindBy(css = ".btn.btn-default.check_out")
	 WebElement toCheckout;
	 private void toCheckout() {
		 toCheckout.click();
	 }
	 
	
	 

}
