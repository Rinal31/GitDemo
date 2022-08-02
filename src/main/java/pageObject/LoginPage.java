package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public WebDriver driver;
	
	//this constructor will get the knowledge of chrome driver which was assigned in base class
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By email=By.id("user_email");
	private By password=By.id("user_password");
	private By login=By.cssSelector("input[type='submit']");
	private By forgotPassword=By.cssSelector("[href*='password/new']");
	
	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	
	}
	public WebElement getLogin() {
		return driver.findElement(login);
		
	}
	
	public ForgotPassword forgotPassword()
	{
		driver.findElement(forgotPassword).click();
		return new ForgotPassword(driver);
		
	}
}
