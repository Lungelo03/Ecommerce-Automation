package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.LoginPage;
import QA.QA_Automation.pages.ProductsPage;
import QA.QA_Automation.pages.cartPage;
import QA.QA_Automation.pages.checkoutPage;
import QA.QA_Automation.pages.getInvoice;
import QA.QA_Automation.pages.paymentPage;

public class testDetailedViewPage extends initialisation {
	@Test
	public static void detailedPage() throws IOException, InterruptedException {
		//tests login, products from cart Detailed View Page
		initialisation init = new initialisation();
		LoginPage login = init.launchApp();
		 HashMap<String, String> credintials = LoginDetails();
		 ProductsPage products = login.Login(credintials);
		 products.getProductList();
		//products.getProductByName("Summer White Top"); 
		cartPage cart =products.productDetailedView("Premium Polo T-Shirts","3");
		checkoutPage checkout =	cart.cartItems();
		paymentPage pay = checkout.checkoutItems("i need them ASAP");
		HashMap<String, String> payment = paymentInfo();
		getInvoice downloadInvoice =pay.confirmPayment(payment);
		downloadInvoice.DownloadInvoice();
		init.login.logout();
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
	public static HashMap<String, String> LoginDetails() {
	    HashMap<String, String> acc = new HashMap<>();
	    acc.put("email", "elemt10@gmail.com");
	    acc.put("password", "1234311");
	    return acc;
	}
	
	/**
	 * contains users payment information
	 * @return acc the payment information map
	 */
	public static HashMap<String, String> paymentInfo(){
		HashMap<String, String> addr = new HashMap<>();
		addr.put("name", "Mr L Buthelezi");
		addr.put("cardNumber", "0000000000003");
		addr.put("cvc", "310");
		addr.put("expiryMonth", "12");
		addr.put("expiryYear", "2027");
	
		return addr;
	}

}
