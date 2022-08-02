package Academy.E2EProject;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.base;

public class ValidateTitle extends base{
	public static Logger log=(Logger) LogManager.getLogger(base.class.getName());
	public WebDriver driver;
	
	//declared globally to restrict creation of object again and again
	LandingPage l;
	@BeforeTest
	public void initializeTest() throws IOException {
		driver=initializeDriver();
		driver.get(prop.getProperty("url"));
		log.info("naigated to homepage");
	}
	
	
	@Test
	public void basePageATitleValidation() throws IOException {
		
		l=new LandingPage(driver);
		
		//compare the browser value to the actual text 
		Assert.assertEquals(l.getTitle().getText(), "FEATURED COURSES123");
		//l.getTitle().getText(); //driver.findElement(By.Css.....  this code is from landing page (page object concept)
		log.info("validation of title of home page success");
		
	}
	@Test
     public void basePageHeaderValidation() throws IOException {
		
		//l=new LandingPage(driver);   we have alredy done in above testcase so no need of again creating object as we are performimg operation on same browser
			
		//compare the browser value to the actual text 
		Assert.assertEquals(l.getHeader().getText(), "AN ACADEMY TO LEARN EVERTYTHING ABOUT TESTING");
		//l.getHeader().getText(); //driver.findElement(By.Css.....  this code is from landing page (page object concept)
		log.info("validation of Header in home page success");
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
