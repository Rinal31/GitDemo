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

public class ValidateNavigationBar extends base{
	public static Logger log=(Logger) LogManager.getLogger(base.class.getName());
	public WebDriver driver;
	
	@BeforeTest
	public void initializeTest() throws IOException {
		driver=initializeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	@Test
	public void basePageNavigationBar() throws IOException {
		
		
		
		
		LandingPage l=new LandingPage(driver);
	    //System.out.println(l.getNavigateBars().getText());	
		//This will check whether navigation bar is displayed or not, if not script fails
		Assert.assertTrue(l.getNavigateBars().isDisplayed());
		//l.getNavigateBars()
		log.info("Navigation bars are displayed in home page");
	}
	
@AfterTest
public void tearDown() {
	driver.close();
}

}
