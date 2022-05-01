package week6.day2.assignments.exceldata;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow extends BaseServiceNowClass{

	@Test(dataProvider= "sendData")
	public void incidents(String incident,String description) throws InterruptedException {
		
		
		
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys(incident);
		
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		
		Thread.sleep(3000);
				driver.switchTo().frame(0);
		
		driver.findElement(By.xpath("//button[text()='New']")).click();
		
		
		driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
		
		Thread.sleep(3000);
		
		Set<String> setwin = driver.getWindowHandles();
		
		List<String> listwin = new LinkedList<String>(setwin);
		
		driver.switchTo().window(listwin.get(1));
		
		driver.manage().window().maximize();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//tbody[@class='list2_body']//tr[1]//td[3]//a")).click();
		
		driver.switchTo().window(listwin.get(0));
		
		driver.switchTo().frame(0);
		
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys(description);
		
		String incidentno = driver.findElement(By.xpath("//input[@name='sys_original.incident.number']")).getAttribute("value");
		
		System.out.println("The incident number is "+incidentno);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("//label[text()='Search']//following-sibling::input")).sendKeys(incidentno,Keys.ENTER);
		
		Thread.sleep(3000);
		
		String text = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		
		if(incidentno.equals(text)) {
			
			System.out.println("the incident is created successful.");
		}
		else {
			System.out.println("the incident is created not successful.");
		}		

	}
	
	@DataProvider
	public String[][] sendData() {
		
		String data[][] = new String[1][2];
		
		data[0][0] ="incident";
		data[0][1]="service now";

		return data;
	}

}
