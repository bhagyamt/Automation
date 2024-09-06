package demo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationTest_02_FunctionalTestingCase {

	public static void main(String[] args) throws InterruptedException, IOException {
	//	launch browser
		WebDriver driver=new ChromeDriver();
	//maximize window	
		driver.manage().window().maximize();
	//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	//navigate to url	
		driver.get("https://demo.dealsdray.com/");
	//enter username
		driver.findElement(By.name("username")).sendKeys("prexo.mis@dealsdray.com");
	//enter password
		driver.findElement(By.name("password")).sendKeys("prexo.mis@dealsdray.com");
	//click on login button
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
	
		driver.findElement(By.xpath("//span[text()='chevron_right']")).click();
		driver.findElement(By.xpath("(//span[text()='Orders'])[1]")).click();
		driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();
		driver.findElement(By.xpath("//input[@id=\"mui-7\"]")).sendKeys("C:\\Users\\bkabb\\Downloads\\demo-data.xlsx");
		driver.findElement(By.xpath("//button[text()=\"Import\"]")).click();
		driver.findElement(By.xpath("//button[text()=\"Validate Data\"]")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(3000);
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(".\\screenshots\\failed.png");
		FileUtils.copyFile(src, trg);
	
		driver.findElement(By.xpath("//button[text()=\"Submit\"]")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		driver.close();

	}

}
