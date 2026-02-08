package QA.QA_Automation.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.verifySubscription;

public class testSubscriptionPage extends initialisation {

	@Test
	public static void subscription() throws IOException {
		initialisation init = new initialisation();
		init.launchApplication();
		verifySubscription sub = init.instantSubscription();
		sub.subscribeHomePage("lungelo45@gmail.com");
		sub.subscribeCartPage("buthelzi991@gmail.com");
		init.tearDown();

	}

}
