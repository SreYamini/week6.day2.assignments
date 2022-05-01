package week6.day2.assignments.exceldata;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseServiceNowClass {

	
	public RemoteWebDriver driver;
	
	@Parameters({"browser","username","password"})
	@BeforeMethod
public void preConditions(String browser,String username,String password) {
		
		
		if(browser.equalsIgnoreCase("chrome")) {
		
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.get("https://dev73365.service-now.com/login.do?user_name=admin&sys_action=sysverb_login&user_password=%3D%5EpvZJTnOi92");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.id("user_name")).sendKeys(username);
		
		driver.findElement(By.id("user_password")).sendKeys(password);
		
		driver.findElement(By.id("sysverb_login")).click();

	}
	
	@AfterMethod
	public void postConditions() {
		
		driver.close();

	}

}
