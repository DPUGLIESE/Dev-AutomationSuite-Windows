package Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Main.LoadSite;
import Main.MainSuite;
import Main.SiteData;
import Main.StaticProvider;
import PageObjects.LoginPage;
import PageObjects.WebPage;

public class ExampleLoginPage extends MainSuite {

	@BeforeClass
	@Parameters({"webDriver"})
	public void before(String webDriver) throws UnknownHostException{
		
		openDriver(webDriver);
		
	}
	
	@Test(dataProvider = "exampleLogin", dataProviderClass = StaticProvider.class)	
	public void test(String url) throws InterruptedException, IOException, AWTException{
		
	
			SiteData data  = LoadSite.getUrlData(url, Driver, true);			
			
			LoginPage loginPage = new LoginPage(Driver, "Sign in · GitHub");
			loginPage.setElementsByName("login", "password", "commit");
			WebPage homepage = loginPage.loginAs("userTest0","P@ssword1");
			Assert.assertEquals(homepage.getTitle(),"GitHub");			
	}
	
	@AfterClass
	@Parameters("webDriver")
	public void after(String webDriver){
		
		closeDriver(webDriver);
		
	}

}

