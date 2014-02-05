package Tests;

import java.awt.AWTException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Main.LoadSite;
import Main.MainSuite;
import Main.SiteData;

public class ExampleNoInput extends MainSuite {

	@BeforeClass
	@Parameters({"webDriver"})
	public void before(String webDriver) throws UnknownHostException{
		
		openDriver(webDriver);
		
	}
	
	@Test
	@Parameters("webDriver")
	public void test(String webDriver) throws InterruptedException, IOException, AWTException{

		@SuppressWarnings("unused")
		SiteData data  = LoadSite.getUrlData("http://www.google.com/", Driver, false);			
		
	}
	
	@AfterClass
	@Parameters("webDriver")
	public void after(String webDriver){
		
		closeDriver(webDriver);
		
	}

}

