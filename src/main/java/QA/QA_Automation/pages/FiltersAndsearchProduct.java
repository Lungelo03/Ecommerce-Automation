package QA.QA_Automation.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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

public class FiltersAndsearchProduct extends abstractBase{
	WebDriver driver;
	ProductsPage product;
	public FiltersAndsearchProduct(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "a[href='/products']")
	WebElement navtoProducts;
	@FindBy(css = ".features_items .col-sm-4")
	List<WebElement> products;
	By productsBy = By.cssSelector(".features_items .col-sm-4");
	
	public cartPage addSearchedProductToCart(String productName)
	{
	
		 WebElement prod =	searchByName(productName).stream()
		        .filter(p -> p.findElement(By.cssSelector(".productinfo p")).getText().equalsIgnoreCase(productName))
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("Product not found: " + productName));
		 //hover first
		    Actions actions = new Actions(driver);
		    actions.moveToElement(prod).perform();
		prod.findElement(By.cssSelector(".overlay-content a")).click();
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cartModal a[href='/view_cart']")));
		    viewCartLink.click();
		    cartPage cart= new cartPage(driver);
			return cart;
		 
	}
	private List<WebElement> searchByName(String productName) { //if search is used make this an argument: String productName
		navtoProducts.click();
		waitForElementToAppear(productsBy);
		products =  searchproduct(productName);
		return products;

	}
				 
	
	@FindBy(id ="search_product")
	WebElement search;
	@FindBy(id = "submit_search")
	WebElement submitSearch;
	public List<WebElement> searchproduct(String productName) {
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
	
	@FindBy(css = ".panel.panel-default")
	 List<WebElement> panels ;
	@FindBy(css= ".panel-body")
	WebElement panelBody;
	 public  void selectCategoryAndSubCategory(HashMap<String, String> filterBy) {
		 navtoProducts.click();
	        waitAmoment(3);
	        //everything is on the same case // case sensitivity
	        String category = filterBy.get("category").trim().toLowerCase();
	        String subCategory = filterBy.get("subcategory").trim().toLowerCase();
	        for (WebElement panel : panels) {
	            WebElement categoryLink = panel.findElement(By.cssSelector(".panel-title a"));
	            String categoryText = categoryLink.getText().trim().toLowerCase();
	            // Match category
	            if (categoryText.equals(category)) {
	                // Expand category if collapsed
	               
	                categoryLink.click();
	                waitAmoment(4);
	                List<WebElement> subCategories = panel.findElements(By.cssSelector("a"));
	                for (WebElement subCat : subCategories) {
	                    String subCatText = subCat.getText().trim().toLowerCase();
	                  
	                    // Match subcategory
	                    if (subCatText.equals(subCategory)) {
	                        subCat.click();
	                        product = new ProductsPage(driver);
	                        String item =filterBy.get("item");
	                        product.addProductsByName(item);
	                        return;
	                    }
	                }
	            }
	        }

	        throw new RuntimeException(
	                "Category, Subcategory or item not found: "
	                        + filterBy.get("category") + " -> " + filterBy.get("subcategory") + "->"+ filterBy.get("item"));
	    }
	 @FindBy(css = ".brands-name a")
	 List<WebElement> brands;
	 public void filterByBrand(String Brand, String item) {
		 navtoProducts.click();
		 for (WebElement brand : brands) {
			 if(brand.getText().contains(Brand)) {
				 brand.click();
				 waitAmoment(3);
				  product = new ProductsPage(driver);
                  product.addProductsByName(item);
                  return;
			 }
		 }
		 throw new RuntimeException(
	             "brand or item not found: " + Brand + " -> " + item);
		
	 }

    
}
