package demo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class AutomationTest_01_UITestingCase {

	public static void main(String[] args) throws Exception {
		
		ScreenRecorderUtil.startRecord("main");
		WebDriver driver = null;
        // Define the browsers to test
		String[] browsers = { "chrome", "firefox", "safari"};
        // Define the screen resolutions for Desktop and Mobile
        String[][] resolutions = { { "Desktop", "1920", "1080" }, { "Desktop", "1366", "768" },
				{ "Desktop", "1536", "864" }, { "Mobile", "360", "640" }, { "Mobile", "414", "896" },
				{ "Mobile", "375", "667" } };
        // URLs to test sitemap.xml file pages
        String[] pages = { "https://www.getcalley.com/", "https://www.getcalley.com/calley-lifetime-offer/", 
				"https://www.getcalley.com/see-a-demo/", "https://www.getcalley.com/calley-teams-features/", 
				"https://www.getcalley.com/calley-pro-features/"};
     // Loop through all browsers
		for (String browser : browsers) {
            // Initialize browser WebDriver based on the browser type
			if (browser.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (browser.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (browser.equals("safari")) {
				driver = new SafariDriver();
			}           
			// Set the implicit wait timeout
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            // Loop through all screen resolutions
			for (String[] resolution : resolutions) {
				String device = resolution[0];
				int width = Integer.parseInt(resolution[1]);
				int height = Integer.parseInt(resolution[2]);
                // Set the browser window size to the specified resolution
				driver.manage().window().setSize(new Dimension(width, height));
				// Loop through all pages to test
				for (String pageUrl : pages) {
                    // Open the page URL
					driver.get(pageUrl);
                    // Wait for page to load 
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    // Create the folder structure to save the screenshots
					String folderPath = "screenshots/" + device + "/" + width + "x" + height;
					File dir = new File(folderPath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
                    // Take a screenshot
					File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    // Generate a timestamp for the screenshot file
					String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
                    // Save the screenshot to the folder
					try {
						FileUtils.copyFile(screenshot, new File(folderPath + ".\\screenshots\\" + timestamp + ".png"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
            // Quit the browser session
			driver.quit();
			ScreenRecorderUtil.stopRecord();
		}
	}
}