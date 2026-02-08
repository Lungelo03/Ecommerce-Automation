package QA.QA_Automation.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import QA.QA_Automation.pages.Registration;

public class testRegistration {
	
	@Test
	public static void registration() throws IOException, InterruptedException {
		initialisation init = new initialisation();
		Registration reg = init.launchApplication();
		 HashMap<String, String> accInformation = accountInfo();
		 HashMap<String, String> addressInformation = addressInfo();
		 reg.Register(accInformation, addressInformation);
			init.tearDown();
		 
}
	/**
	 * for cleaner and ease of use- hashmaps 
	 * contains account information
	 * @return acc - account information
	 */
	public static HashMap<String, String> accountInfo(){
		HashMap<String, String> acc = new HashMap<>();
		acc.put("name", "zune");
		acc.put("email", "zune@gmail.com");
		acc.put("gender", "male");
		acc.put("password", "zun5522");
		acc.put("day", "11");
		acc.put("month", "May");
		acc.put("year", "2003");
	
		return acc;
	}
	/**
	 * contains users address information
	 * @return acc the address information
	 */
	public static HashMap<String, String> addressInfo(){
		HashMap<String, String> acc = new HashMap<>();
		acc.put("lastName", "Buthelezi");
		acc.put("address", "12 florida");
		acc.put("country", "United States");
		acc.put("state", "USA");
		acc.put("city", "Chicago");
		acc.put("zipCode", "2813");
		acc.put("number", "9945235701");
	
		return acc;
	}
}
