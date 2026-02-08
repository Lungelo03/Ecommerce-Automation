package QA.QA_Automation.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.cartPage;

public class testEmptyCart extends initialisation {

	@Test
	public static void emptyCart() throws IOException {
		initialisation init = new initialisation();
		init.launchApplication();
		cartPage cart = init.instantCart();
		cart.toCart();
		cart.emptyCart();
		init.tearDown();
	}

}
