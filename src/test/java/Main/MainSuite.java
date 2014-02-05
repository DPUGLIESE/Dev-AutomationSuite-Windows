package Main;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.browsermob.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.android.library.AndroidWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.lang.String;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainSuite {
	
//Webdiver
	
	public static WebDriver Driver;
	
//SeleniumServer
	
	public static ProxyServer server;
	
//BrowserMob Proxy
	
	public static Proxy proxy;
	
//Browsers configurations
	
	public static DesiredCapabilities ffCap = DesiredCapabilities.firefox();
	public static DesiredCapabilities chromeCap = DesiredCapabilities.chrome();
	public static DesiredCapabilities ieCap = DesiredCapabilities.internetExplorer();
	public static DesiredCapabilities androidCap = DesiredCapabilities.android();
	
//XLS file and sheets
	
	public Workbook XLS;
	public static ArrayList<Sheet> sheets = new ArrayList<Sheet>();
	public static String logDirectory;
	
//Directory of the xls where the sites are, and strings to add in the url
	
	public String XLSDirectory = "";
	
//Methods to set server and proxy
	
	public static void setProxy(String proxyDirecc) throws UnknownHostException{
		
		proxy.setHttpProxy(proxyDirecc);   
    	proxy.setFtpProxy(proxyDirecc);
    	proxy.setSslProxy(proxyDirecc);
    	
	}
	
	public void startServer(String proxyDirecc) throws Exception{  
	   
		server = new ProxyServer(4444);
	    server.start();
	    server.setCaptureContent(true);
	    server.setCaptureHeaders(true);
	   
	    if (!(proxyDirecc.equals("")||proxyDirecc.equals("NOPROXY"))){
	    	
		    proxy = server.seleniumProxy();
	
		    if (!(proxyDirecc.equals("")||proxyDirecc.equals("NOPROXY"))){
	
				setProxy(proxyDirecc);
				
			}
	
			ffCap.setCapability(CapabilityType.PROXY, proxy);
	        chromeCap.setCapability(CapabilityType.PROXY, proxy);
	        ieCap.setCapability(CapabilityType.PROXY, proxy);
	        ieCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);    
			ieCap.setCapability("ie.ensureCleanSession", true);    
		    ieCap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);	    
		    ieCap.setCapability("ignoreProtectedModeSettings", true);
	    }
	    System.out.println("*-*-*-  SERVER INICIATED  -*-*-*");
		
	}
	
// Methods to open browsers
	
	@SuppressWarnings("deprecation")
	public static WebDriver openDriver(String webDriver){
		
		switch (webDriver){
		
			case "firefox":
				
				Driver = new FirefoxDriver(ffCap);			
				Driver.manage().deleteAllCookies();				
				System.out.println("*-*-*-  FIREFOX DRIVER INICIATED  -*-*-*");
				
				break;
			
			case "explorer":
							
				Driver = new InternetExplorerDriver(ieCap);				
				Driver.manage().deleteAllCookies();			
				System.out.println("*-*-*-  IE DRIVER INICIATED  -*-*-*");			
							
				break;
			
			case "chrome":
				
				Driver = new ChromeDriver(chromeCap);			
				Driver.manage().deleteAllCookies();		
				System.out.println("*-*-*-  CHROME DRIVER INICIATED  -*-*-*");
				
				break;
				
			case "android":
				
				//androidCap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				Driver = new AndroidDriver();
				System.out.println("*-*-*-  ANDROID DRIVER INICIATED  -*-*-*");
				
				break;
		
		}
		
		return Driver;
		
	}

//Method to close browsers
	
public static void closeDriver(String webDriver){
		
		switch (webDriver){
		
			case "firefox":
				
				Driver.quit();
				System.out.println("*-*-*-  FF DRIVER CLOSED  -*-*-*");
				
				break;
			
			case "explorer":
							
				Driver.quit();
				System.out.println("*-*-*-  IE DRIVER CLOSED  -*-*-*");			
							
				break;
			
			case "chrome":
				
				Driver.quit();
				System.out.println("*-*-*-  CHROME DRIVER CLOSED  -*-*-*");
				
				break;
				
			case "android":
				
				Driver.quit();
				System.out.println("*-*-*-  ANDROID DRIVER CLOSED  -*-*-*");
				
				break;
		
		}
		
	}
	
//Method to close the server connection
	
	public void closeServer() throws Exception{
        
		server.stop();
        
		System.out.println("*-*-*-  SERVER CLOSED  -*-*-*");
	
	}

//TestNG methods
	
	@BeforeSuite
	@Parameters({"proxyDirecc","sheetsToWork","xlsDirectory"})
	public void beforeSuite(String proxyDirecc,String sheetsToWork,String xlsDirectory) throws Exception{
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver-2.8.exe");
		System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe"); 
		
		startServer(proxyDirecc);
		
		if (!xlsDirectory.equals("NOINPUT")){
			XLS = LogManager.openXLS(xlsDirectory);
			sheets = LogManager.getSheets(sheetsToWork, XLS);
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd#HH-mm-ss");
		Date date = new Date();
		//create a new log folder for this session
		logDirectory = System.getProperty("user.dir")+"\\test-logs\\"+dateFormat.format(date).toString();
		new File(System.getProperty("user.dir")+"\\test-logs\\"+dateFormat.format(date).toString()).mkdirs();
		System.out.println("Project directory: "+System.getProperty("user.dir"));
	}
	
	@AfterSuite
	@Parameters({"xlsDirectory"})
	public void afterSuite(String xlsDirectory) throws Exception{
	
		if (!xlsDirectory.equals("NOINPUT")){
			LogManager.closeXLS(xlsDirectory, XLS);
		}
		closeServer();
		
		Process process=Runtime.getRuntime().exec("xcopy C:\\Users\\Augusto\\workspace\\Dev-AutomationSuite\\test-output\\MainSuite "+logDirectory+" /e /i /h");
		process.waitFor();
		
	}

}

