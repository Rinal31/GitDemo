package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	public WebDriver driver;
	
	//this constructor will get the knowledge of chrome driver which was assigned in base class
	public LandingPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By signin=By.xpath("//span[contains(text(),'Login')]");
	private By title=By.xpath("//h2[contains(text(),'Featured Courses')]");
	private By navBars=By.xpath("//header/div[2]/div[1]/nav[1]/ul[1]");
	private By header=By.cssSelector("div[class*='video-banner']  h3");
	
	public LoginPage getLogin() {
		//click and sending login page object here for page object optimization. this will avoid creation of new page objects for each class in testcase
		driver.findElement(signin).click();
		LoginPage lp=new LoginPage(driver);
		return lp;
	}
	
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	
	public WebElement getNavigateBars() {
		return driver.findElement(navBars);
	}

	public WebElement getHeader() {
		return driver.findElement(header);
	}
}
