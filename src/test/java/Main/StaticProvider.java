package Main;

import org.testng.annotations.DataProvider;

public class StaticProvider {

	@DataProvider(name = "urls")
	public static Object[][] urls() {
		return new Object[][] {			
				{"https://www.google.com/"},
				{"https://www.google.com/"},
				{"http://devspark.com/"},
		};
	}
	
	
	@DataProvider(name = "exampleLogin")
	public static Object[][] exampleLogin() {
		return new Object[][] {			
				{"https://github.com/login"},				
		};
	}
	
}
