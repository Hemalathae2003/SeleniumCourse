package automation.SeleniumFramework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;
	
	
	
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
		
	}

	public void gotoapp()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	
}
