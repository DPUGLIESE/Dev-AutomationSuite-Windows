package Main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Main.SiteData;

public class LoadSite extends MainSuite {

	//Load an URL on a driver
	public static void getUrl(String URL, WebDriver driver, boolean fullLoad) throws InterruptedException, AWTException {
		
		if (fullLoad) {
			
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
			
		} else {
		
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		
		}
		
		boolean load = false;
		
		for (int i = 1; i<5; i++){
			
			if (!load) {
			
				try {
					
					System.out.println("Loading: "+URL);
					
					driver.get(URL);
					IEAuth(driver);
		
					} catch (org.openqa.selenium.TimeoutException e) {
						
					}
				
				if (fullLoad) {
					
					if (((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")) {
						
						load = true;
						
					}
					
				} else {
			
					if (((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")||((JavascriptExecutor)driver).executeScript("return document.readyState").equals("interactive")) {
						
						load = true;
						
					}
					
				}
				
			}
			
		}
		
	}
	
	//Load a site on a webdriver that is already opened, the boolean fullLoad defines if the wait should be longer in order to wait the site to load the 100%.
	//It returns all teh data of the site in a SiteData object.
	public static SiteData getUrlData(String URL, WebDriver driver, boolean fullLoad) throws InterruptedException, AWTException {
		
		if (fullLoad) {
			
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			
			driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
			
		} else {
		
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			
			driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		
		}
		
		SiteData data = new SiteData();
		
		boolean load = false;
		
		for (int i = 1; i<5; i++){
			
			if (!load) {
			
				try {
					
					server.newHar(URL);		
					System.out.println("Loading: "+URL);	
					driver.get(URL);		
					IEAuth(driver);
		
					} catch (org.openqa.selenium.TimeoutException e) {
						
					}
				
				Thread.sleep(3000);
				
				if (fullLoad) {
					
					if (((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")) {
						
						load = true;
						
					}
					
				} else {
			
					if (((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")||((JavascriptExecutor)driver).executeScript("return document.readyState").equals("interactive")) {
						
						load = true;
						
					}
					
				}
				
			}
			
		}
			
		data.setUrl(URL);
		
		data.setDriver(driver);
		
		data.setTraffic(server.getHar());
		
		data.setSourceCode(driver.getPageSource().toString());
		
		data.setElements((ArrayList<WebElement>) driver.findElements(By.xpath("//*")));
		
		return data;
		
	}
	
	//Handle the auth window that pop up on IE
	public static void IEAuth(WebDriver driver) throws InterruptedException, AWTException{
		
		boolean alert = true;
		
		while (alert){
			
			try { 
				
			driver.switchTo().alert().accept();
			
			Thread.sleep(1000);
			
			Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_ENTER);
			
			Thread.sleep(1000);
			
			} catch (org.openqa.selenium.NoAlertPresentException e){
				
				alert = false;
				
				}
				
		}
	
	}

}
