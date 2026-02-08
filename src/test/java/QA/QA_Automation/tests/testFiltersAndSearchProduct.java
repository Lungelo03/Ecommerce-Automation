package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.cartPage;
import QA.QA_Automation.pages.FiltersAndsearchProduct;

public class testFiltersAndSearchProduct extends initialisation {
	@Test
	public  void testSearchFilters() throws IOException {
		//test search product ,filter by category and filter by brand
		initialisation init = new initialisation();
		init.launchApp();
		 FiltersAndsearchProduct search =  init.instantsearch();
		 search.addSearchedProductToCart("Summer White Top");
		 HashMap<String, String> filter =filterByCategory();
		search.selectCategoryAndSubCategory(filter);
		 search.filterByBrand("MADAME","Madame Top For Women");
		cartPage cart = init.instantCart();
		cart.toCart();
		init.tearDown();
		
	}
	
	/**
	 * Returns a map containing login credentials.
	 *
	 * <p>The returned {@link HashMap} includes the following key-value pairs:
	 * <ul>
	 *   <li><b>email</b> – the login email address</li>
	 *   <li><b>password</b> – the login password</li>
	 * </ul>
	 *
	 * @return a {@link HashMap} with predefined login details
	 */
	public static HashMap<String, String> filterByCategory() {
	    HashMap<String, String> acc = new HashMap<>();
	    acc.put("category", "Kids");
	    acc.put("subcategory", "Tops & Shirts");
	    acc.put("item", "Frozen Tops For Kids");
	    return acc;
	}

}
