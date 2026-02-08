package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.ProductsPage;
import QA.QA_Automation.pages.Registration;
import QA.QA_Automation.pages.cartPage;
import QA.QA_Automation.pages.checkoutPage;
import QA.QA_Automation.pages.getInvoice;
import QA.QA_Automation.pages.paymentPage;


public class testEndtoEnd extends initialisation  {
	/**
	 * Executes an end-to-end automated test flow for an e-commerce application.
	 *
	 * <p>This method initializes the application, performs user registration,
	 * adds multiple products to the shopping cart (from both product listing
	 * and product detail pages), proceeds through checkout, completes payment,
	 * and finally downloads the generated invoice.</p>
	 *
	 * <p>The test uses predefined account, address, and payment data to simulate
	 * a complete user purchase journey. It is intended to validate the core
	 * purchase workflow, including cart operations, checkout, payment processing,
	 * and invoice generation.</p>
	 *
	 * @param args command-line arguments (not used)
	 * @throws IOException if an error occurs during invoice download or file handling
	 * @throws InterruptedException if the execution is interrupted during waits
	 */
	@Test
	public static void EndtoEnd() throws IOException, InterruptedException {
		initialisation init = new initialisation();
		Registration reg = init.launchApplication();
		 HashMap<String, String> accInformation = accountInfo();
		 HashMap<String, String> addressInformation = addressInfo();
		ProductsPage products = reg.Register(accInformation, addressInformation);
		products.getProductList(); //8.5 8.6
		cartPage cart =	products.addProductsByName("Summer White Top","Little Girls Mr. Panda Shirt","Winter Top"); 
		products.productDetailedView("Lace Top For Women","4"); // add product from detailed view page
		cart.toCart();
	//	cart.removeProductFromCart("Summer White Top"); see testSearchProduct.java
		checkoutPage checkout =	cart.cartItems();
		paymentPage pay = checkout.checkoutItems("i need them ASAP");
		HashMap<String, String> payment = paymentInfo();
		getInvoice downloadInvoice =pay.confirmPayment(payment);
		downloadInvoice.DownloadInvoice();
		reg.logout();
		init.tearDown();
	
	}
	/**
	 * for cleaner and ease of use- hashmaps 
	 * contains account information
	 * @return acc - account information map
	 */
	public static HashMap<String, String> accountInfo(){
		HashMap<String, String> acc = new HashMap<>();
		acc.put("name", "lungelo");
		acc.put("email", "elemt10@gmail.com");
		acc.put("gender", "male");
		acc.put("password", "1234311");
		acc.put("day", "17");
		acc.put("month", "July");
		acc.put("year", "2000");
	
		return acc;
	}
	/**
	 * contains users address information
	 * @return acc the address information map
	 */
	public static HashMap<String, String> addressInfo(){
		HashMap<String, String> addr = new HashMap<>();
		addr.put("lastName", "Buthelezi");
		addr.put("address", "11 Biccard");
		addr.put("country", "United States");
		addr.put("state", "Gauteng");
		addr.put("city", "Johannesburg");
		addr.put("zipCode", "10491");
		addr.put("number", "0712387891");
	
		return addr;
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
