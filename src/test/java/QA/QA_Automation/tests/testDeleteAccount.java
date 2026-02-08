package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.LoginPage;

public class testDeleteAccount {
	
	@Test
	public static void deleteAcc() throws IOException {
		initialisation init = new initialisation();
		LoginPage login = init.launchApp();
		 HashMap<String, String> credintials = LoginDetails();
		 login.Login(credintials);
		 login.deleteAccount();
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

}
