package Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Main.LoadSite;
import Main.MainSuite;
import Main.SiteData;
import Main.StaticProvider;

public class ExampleWithProviders extends MainSuite {

	@BeforeClass
	@Parameters({"webDriver"})
	public void before(@Optional("firefox") String webDriver) throws UnknownHostException{
		
		openDriver(webDriver);
		
	}
	
	@Test(dataProvider = "urls", dataProviderClass = StaticProvider.class)	
	public void test(String url) throws InterruptedException, IOException, AWTException{
		
		SiteData data  = LoadSite.getUrlData(url, Driver, true);	
	}	
	
	
	@AfterClass
	@Parameters("webDriver")
	public void after(@Optional("firefox") String webDriver){
		
		closeDriver(webDriver);
		
	}
}
