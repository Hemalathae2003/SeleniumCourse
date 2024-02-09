package automation.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.SeleniumFramework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Submitordertest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		LandingPage loginpage= new LandingPage(driver);
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		
		
		driver.findElement(By.id("userEmail")).sendKeys("parvathi123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Parvathi@123");
		
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
	 List<WebElement> products=	driver.findElements(By.cssSelector(".mb-3"));
		
	WebElement prod= products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		
	prod.findElement(By.cssSelector(".card button:last-of-type")).click();
	

	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	//ng-animating that circle id name
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	Thread.sleep(7000L);
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	List<WebElement> cart= driver.findElements(By.cssSelector(".cartSection h3"));
	
	
	Boolean val=cart.stream().anyMatch(match->match.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
	
	Assert.assertTrue(val);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	
	WebElement order= driver.findElement(By.cssSelector(".action__submit"));
	js.executeScript("arguments[0].scrollIntoView();", order);
	Thread.sleep(4000L);
	driver.findElement(By.cssSelector(".action__submit")).click();
	//driver.findElement(By.xpath("//div[@class='actions']//a")).click();
	
	String confirmmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
	}

}
