package PageObjects;

import org.openqa.selenium.WebDriver;

public class WebPage {

	protected final WebDriver driver;
	
	public WebPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Check that we're on the right page.
	public WebPage(WebDriver driver, String title) {
		this.driver = driver;
        if (!title.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the login page");
        }
	}

	public String getTitle() {
		
		return driver.getTitle();
	}
	
}
