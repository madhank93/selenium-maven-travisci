package io.github.madhank93.selenium_maven_travisci;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleTest {
	
	
	private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setupTest() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test (priority = 1)
    public void test() {
    	driver.get("https://time.is/");
    	String currentDate = driver.findElement(By.xpath("//div[@id='dd']")).getText();
    	System.out.println("Date: " + currentDate);
    }
    
    @Test (priority = 2)
    public void screenshot() {
    	try {
    		TakesScreenshot screenShot = (TakesScreenshot) driver;
    		FileHandler.copy(screenShot.getScreenshotAs(OutputType.FILE),
    				new File(System.getProperty("user.dir") + "/screenshot" + ".png"));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
