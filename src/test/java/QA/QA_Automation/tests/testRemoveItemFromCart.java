package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.LoginPage;
import QA.QA_Automation.pages.ProductsPage;
import QA.QA_Automation.pages.cartPage;

public class testRemoveItemFromCart {

	@Test
	public static void removeItem() throws IOException {
		initialisation init = new initialisation();
		LoginPage login = init.launchApp();
		 HashMap<String, String> credintials = LoginDetails();
		 ProductsPage products = login.Login(credintials);
		 products.getProductList();
		 cartPage cart =	products.addProductsByName("Little Girls Mr. Panda Shirt","Summer White Top","Winter Top"); 
		 cart.removeProductFromCart("Summer White Top");
		 init.login.logout();
			init.tearDown();
		

	}
	public static HashMap<String, String> LoginDetails() {
	    HashMap<String, String> acc = new HashMap<>();
	    acc.put("email", "elemt10@gmail.com");
	    acc.put("password", "1234311");
	    return acc;
	}
}
