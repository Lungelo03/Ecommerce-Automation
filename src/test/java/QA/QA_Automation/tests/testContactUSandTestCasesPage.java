package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.contactUsPage;

public class testContactUSandTestCasesPage extends initialisation {

	@Test
	public void contactUS() throws IOException {
		initialisation init = new initialisation();
		init.launchApplication();
		contactUsPage contact = init.instantContactUs();
		HashMap<String, String> inTouch = leaveMessage();
		contact.contactUs(inTouch);
		contact.toTestCases();
		init.tearDown();
	}
	
	public static HashMap<String, String> leaveMessage(){
		HashMap<String, String> acc = new HashMap<>();
		String projectDir = System.getProperty("user.dir") + "/resources/4.PNG";
		acc.put("name", "Lungelo");
		acc.put("email", "elemt10@gmail.com");
		acc.put("subject", "Quality of product");
		acc.put("message", "strong quality, well done!!");
		acc.put("filedir", projectDir);
	
		return acc;
	}

}
