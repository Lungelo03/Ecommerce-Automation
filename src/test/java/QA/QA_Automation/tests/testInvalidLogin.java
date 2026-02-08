package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.LoginPage;


public class testInvalidLogin extends initialisation {
	@Test
    public static void invalidLogin() throws IOException {
    	initialisation init = new initialisation();
		LoginPage login = init.launchApp();
		 HashMap<String, String> credintials = LoginDetails();
	      login.invalidLogin(credintials);;
			init.tearDown();
    }
	public static HashMap<String, String> LoginDetails() {
	    HashMap<String, String> acc = new HashMap<>();
	    acc.put("email", "elemt10@gmail.com");
	    acc.put("password", "1234qwe311");
	    return acc;
	}
}
