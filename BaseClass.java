package week6.day2.assignments.exceldata;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public RemoteWebDriver driver;
	
	public static String excelFile;
	
	@Parameters({"browser","url","username","password"})
	@BeforeMethod
	public void preConditions(String browser,String url,String username,String password) {
		
		
		if(browser.equalsIgnoreCase("chrome")) {
		
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Leads")).click();
		
		

	}
	
	@DataProvider(indices=1)
	public String[][] sendData() throws IOException {
		
		return ReadExcelFile.readExcel(excelFile);		
		

	}
	
	@AfterMethod
	public void postConditions() {
		
		driver.close();

	}

}
