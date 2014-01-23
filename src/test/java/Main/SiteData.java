package Main;

import java.util.ArrayList;

import org.browsermob.core.har.Har;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SiteData {
	
	//The url that the webdriver loaded
	private String url;
	//The driver object
	private WebDriver driver;
	//The sourcecode of the site
	private String sourceCode;
	//All the elements of the site
	private ArrayList<WebElement> elements;
	//The traffic  while the site was loading
	private Har traffic;
	
	public String getUrl(){
		return url;
	}
	public WebDriver getDriver(){
		return driver;
	}
	public String getSourceCode(){
		return sourceCode;
	}
	public ArrayList<WebElement> getElements(){
		return elements;
	}
	public Har getTraffic(){
		return traffic;
	}
	
	public void setUrl(String newUrl){
		url = newUrl;
	}
	public void setDriver(WebDriver newDriver){
		driver = newDriver;
	}
	public void setSourceCode(String newCode){
		sourceCode = newCode;
	}
	public void setElements(ArrayList<WebElement> newElements){
		elements = newElements;
	}
	public void setTraffic(Har newTraffic){
		traffic = newTraffic;
	}

}
