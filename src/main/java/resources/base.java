package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.apache.commons.io.FileUtils;

public class base {
    public WebDriver driver;
    public Properties prop;
	public WebDriver initializeDriver() throws IOException {
		
		//data. properties file access code to take browser details
		prop=new Properties();
		
		//to remove hardcoded data.properties file pth used |Syste.getProperty("user.dir")|
		//FileInputStream fis=new FileInputStream("C:\\Users\\BRIAN DSILVA\\Desktop\\RinalSerrao\\NewSelSpace\\E2EProject\\src\\main\\java\\resources\\data.properties");
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		
		prop.load(fis);
		// to get the browser value from maven command, instead of prop.getProperty("browser"); use System.getProperty("browser");
		//Now when we run |mvn test -Dbrowser=chrome| it will not refer data.properties file
		
		//String browserName=prop.getProperty("browser");
		
		String browserName=System.getProperty("browser");
		System.out.println(browserName);
		
		//to have work in both head and headless mode commenting equals method and using contains menthos
		//if(browserName.equals("chrome")) {
	     
		if(browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\BRIAN DSILVA\\Desktop\\RinalSerrao\\browserDrivers\\chromedriver.exe");
			
			//making chromedriver to run in headless mode (without invoking browser on screen)
			ChromeOptions options=new ChromeOptions();
			
			if(browserName.contains("headless")) {
		    //make it headless only if argument passed as chromeheadless
			options.addArguments("headless");
			}
			//pass the knowledge of headless to ChromeDriver
			driver=new ChromeDriver(options);
		}
		
		else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\BRIAN DSILVA\\Desktop\\RinalSerrao\\browserDrivers\\msedgedriver.exe");
			driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
			
	}
	public static String getScreenShotPath(String testMethodName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=System.getProperty("user.dir")+"\\reports\\"+testMethodName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;
		
	}
}
