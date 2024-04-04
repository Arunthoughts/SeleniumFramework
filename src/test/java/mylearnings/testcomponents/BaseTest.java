package mylearnings.testcomponents;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import mylearnings.pageobjects.LogIn;

public class BaseTest {
	
	public static WebDriver driver;
	public LogIn logInObject;
	
	public WebDriver initializeDriver() throws IOException {
		
		
		  //property class in java is used to read the value from file (i.e. in keyvalue pair) 
		  Properties property =new Properties();
		  
		  //property.load method needs a FileInputStream object as an argument
		  FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\mylearnings\\resources\\GlobalData.properties");
		  
		  //providing FileInputStream as an argument(reading that file)
		  property.load(fis);
		  
		  //we are checking if something is giver in maven command for browser or not if yes then that will be used
		  //or else our value stored in file will be used
		  String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") :property.getProperty("browser");
		  
			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			} else if (browserName.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
			}else {
				driver = new InternetExplorerDriver();
			}
		 
		
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LogIn launchApplicationPage() throws IOException {
			driver = initializeDriver();
			logInObject = new LogIn(driver);
			logInObject.navigateTo();
			return logInObject;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication() {
		driver.close();
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		
		File filename =new File(System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png");
		FileUtils.copyFile(file, filename);
		return System.getProperty("user.dir")+"\\reports\\"+testcaseName+".png";
	}
	
	public List<HashMap<String, String>> getjsonData(String filePath) throws IOException {
		
		//reading the json file and storing the data in string format
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//converting string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;	
	}
}
