package QA.QA_Automation.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import QA.QA_Automation.base.abstractBase;

public class ProductsPage extends abstractBase{
	WebDriver driver;
	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "a[href='/products']")
	WebElement navtoProducts;
	@FindBy(css = ".features_items .col-sm-4")
	List<WebElement> products;
	By productsBy = By.cssSelector(".features_items .col-sm-4");
	/**
	 *wait for all product to appear then get then return  the list of those product
	 * @return products - a list of products
	 */
	public List<WebElement> getProductList() { //if search is used make this an argument: String productName
		navtoProducts.click();
		waitForElementToAppear(productsBy);
		/**
		 * 8.6
		 for(WebElement p : products) {
			   System.out.println( p.getText());
		   } **/
		//if search is being used
		
		return products;

	}
	@FindBy(id="quantity")
	WebElement quantity;
	@FindBy(css = "button[type='button']")
	WebElement addToCart;
	@FindBy(css = ".modal-content")
	WebElement Modal;
	/**
	 * Navigates to a product's detail page by product name, updates the quantity,
	 * adds the product to the cart, and proceeds to the cart page via the
	 * confirmation modal.
	 *
	 * <p>This method performs the following steps:
	 * <ul>
	 *   <li>Finds the product card from the product listing using the given product name</li>
	 *   <li>Clicks the "View Product" link to open the product detail page</li>
	 *   <li>Updates the quantity field with the provided value</li>
	 *   <li>Clicks the "Add to Cart" button</li>
	 *   <li>Waits for the "Added!" modal to appear and clicks the "View Cart" link</li>
	 * </ul>
	 *
	 * @param productName the name of the product to be selected from the product list
	 * @param Quantity the quantity to be entered for the selected product
	 * @return a {@link cartPage} object representing the shopping cart page
	 * @throws RuntimeException if the specified product is not found in the product list
	 */
	public cartPage productDetailedView(String productName, String Quantity) {
		
		    WebElement productCard = getProductList().stream() 
		        .filter(p -> p.findElement(By.cssSelector(".productinfo p"))
		                      .getText().equalsIgnoreCase(productName))
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("Product not found: " + productName));

		    productCard .findElement(By.cssSelector(".choose a")).click();
		    //wait for the cart page to load completely
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		    quantity.clear();
		    quantity.sendKeys(Quantity); //verify if the quantiy box is functional
		    addToCart.click();
		    //the view cart link is in a modal traverse to the moda first
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cartModal a[href='/view_cart']")));
		    viewCartLink.click();
		    cartPage cart= new cartPage(driver);
			return cart;

		}
				 
	
	/**
	 * Adds one or more products to the shopping cart by their product names.
	 *
	 * <p>This method accepts a variable number of product names using Java varargs
	 * ({@code String...}). For each provided name, it locates the corresponding
	 * product from the product list, hovers over it to reveal the action overlay,
	 * and clicks the <em>Add to cart</em> button.</p>
	 *
	 * <p>After adding each product, the method clicks <em>Continue Shopping</em>
	 * and waits for the cart modal to disappear before proceeding to the next
	 * product. Once all products have been added, it navigates to the cart page.</p>
	 *
	 * @param productNames one or more product names to be added to the cart;
	 *                     values may be passed individually or as a String array
	 * @return a {@link cartPage} instance representing the cart page
	 * @throws RuntimeException if any product with the given name is not found
	 */
	
	public cartPage addProductsByName(String... productNames) { //string... allows to pass as many strings either individually or by an array

	    Actions actions = new Actions(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

	    for (String productName : productNames) {

	        WebElement product = getProductList().stream()
	                .filter(p -> p.findElement(By.cssSelector(".productinfo p"))
	                        .getText().equalsIgnoreCase(productName))
	                .findFirst()
	                .orElseThrow(() ->
	                        new RuntimeException("Product not found: " + productName));
	        // Hover on product
	        actions.moveToElement(product).perform();
	        // Click Add to cart
	        product.findElement(By.cssSelector(".overlay-content a")).click();
	        // Wait for modal and click Continue Shopping (except last item)
	        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cartModal button[data-dismiss='modal']") ));
	        continueBtn.click();
	        // Wait for modal to disappear before next iteration
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cartModal")));
	    }
	    // Open cart once after all items are added
	    WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/view_cart']")));
	    viewCartLink.click();

	    return new cartPage(driver);
	}

	
	@FindBy(id ="search_product")
	WebElement search;
	@FindBy(id = "submit_search")
	WebElement submitSearch;
	public List<WebElement> searchProduct(String productName) {
	    List<WebElement> matchedProducts = new ArrayList<>();

	    search.clear();
	    search.sendKeys(productName);
	    submitSearch.click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfAllElements(products));

	    for (WebElement product : products) {
	        if (product.isDisplayed()) {
	            matchedProducts.add(product);
	          // System.out.println(product.findElement(By.cssSelector(".productinfo p")).getText()); //checking
	        }
	    }
	    return matchedProducts;
	}

	
}
