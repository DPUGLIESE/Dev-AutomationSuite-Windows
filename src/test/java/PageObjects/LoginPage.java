package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends WebPage {
	
	private String title;
	private By usernameLocator;
	private By passwordLocator;
	private By loginButtonLocator;
	
	
    
    public LoginPage(WebDriver driver, String title) {
    	super(driver,title);
    	this.title = title;
    }
    
    // To set the HTML elements (using name), represented as WebElements
    public void setElementsByName(String username, String password, String submit){
    	// The locators for these elements should only be defined once.
    	usernameLocator = By.name(username);
        passwordLocator = By.name(password);
        loginButtonLocator = By.name(submit);
    }
    
    // To set the HTML elements (using id), represented as WebElements
    public void setElementsById(String username, String password, String submit){
        // The locators for these elements should only be defined once.
    	usernameLocator = By.id(username);
        passwordLocator = By.id(password);
        loginButtonLocator = By.id(submit);
    }
    
    // To enter a username
    public LoginPage typeUsername(String username) {

        driver.findElement(usernameLocator).sendKeys(username);

        // Return the current page object as this action doesn't navigate to a page represented by another PageObject
        return this;    
    }

    // To enter a password
    public LoginPage typePassword(String password) {

        driver.findElement(passwordLocator).sendKeys(password);

        // Return the current page object as this action doesn't navigate to a page represented by another PageObject
        return this;    
    }

    // To submit the login form
    public WebPage submitLogin() {
        
        driver.findElement(loginButtonLocator).submit();

        // Return a new page object representing the destination. 
        return new WebPage(driver);    
    }

    // To submit the login form knowing that an invalid username and / or password were entered
    public LoginPage submitLoginExpectingFailure() {

        driver.findElement(loginButtonLocator).submit();

        // Return a new page object representing the destination. 
        return new LoginPage(driver,title);   
    }

    // To log in using a user name and password.
    public WebPage loginAs(String username, String password) {

        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
}
