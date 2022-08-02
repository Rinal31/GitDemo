package Academy.E2EProject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.ForgotPassword;
import pageObject.LandingPage;
import pageObject.LoginPage;
import resources.base;

public class HomePage extends base{
	public static Logger log=(Logger) LogManager.getLogger(base.class.getName());
	public WebDriver driver;
	//we are declaring this here to keep local copy of a driver to avoid overriding it due to parallel test case running.
	//this will run parallel not sequential beacuse we have maintained each class in seperate <test> tag inside testng.xml file
	
	@BeforeTest
	public void initializeTest() throws IOException {
		driver=initializeDriver();
		//driver.get(prop.getProperty("url")); this code written in @Test method, because we are using multiple logins so it should invoke chrome each time for each login data
	}
	
	
	@Test(dataProvider ="getData")
	public void basePageLoginUsers(String username, String password, String text) throws IOException {
		driver.get(prop.getProperty("url"));
		//login using loginpage class obejects
		LandingPage l=new LandingPage(driver);
		
		LoginPage lp=l.getLogin(); //login page object from landing page (page object concept)
		lp.getEmail().sendKeys(username);
		lp.getPassword().sendKeys(password);
		lp.getLogin().click(); 
		ForgotPassword fp= lp.forgotPassword();
		fp.getEmail().sendKeys("xxx");
		fp.sendMeInstructions().click();
		log.info(text);
		log.info("login is attempted");
	}
	
	//DataProvider annotation of testNG is useful for parameterized data working with multiple data
	//instead of handcoding username password for login, use below parametrization using TestNG concept
	@DataProvider
	public Object[][] getData() {
		//row stands for how many different data types should run
		//column stands for how many values for each test
		Object[][] data=new Object[2][3];
		//0th row
		data[0][0]="nonrestricteduser@qw.com";
		data[0][1]="123456";
		data[0][2]="non restricted user";
		//1st row
		data[1][0]="restricteduser@qw.com";
		data[1][1]="783056";
		data[1][2]="restricted user";
		
		return data;
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		log.info("browser closed");
	}

}
