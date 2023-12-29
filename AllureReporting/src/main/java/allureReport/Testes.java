package allureReport;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Listeners({AllureListner.class})
public class Testes extends BaseClass
{

	public WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		ChromeOptions options=new ChromeOptions();
		 options.addArguments("--remote-allow-origins=*");
		 options.addArguments("--disable-notifications");

   	//WebDriverManager.chromedriver().setup();
	 System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

	   // BaseClass base=new BaseClass();
	   // base.initialize_driver();
	 driver=new ChromeDriver(options);
	    
	    driver.get("https://demo.nopcommerce.com/");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	      
	}
	
	
	@Test(priority=1,description="Verify Logo presence on Hme")
	@Description("Verify Logo presence on Hme.....")
	@Epic("EP001")
	@Feature("Feature1:Logo")
	@Story("Story:Logo Prtesence")
	@Step("Verify logo presence")
	@Severity(SeverityLevel.MINOR)
	public void logoPresence()
	{
		boolean logo=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
        Assert.assertEquals(logo, true);
		
	}
	
	@Test(priority=2)
	@Description("Verify Logo presence on Hme.....")
	@Epic("EP001")
	@Feature("Feature2:Login")
	@Story("Story:Valid Login")
	@Step("Verify login")
	@Severity(SeverityLevel.BLOCKER)
	public void LoginTest()
	{
		 
		driver.findElement(By.xpath("//a[normalize-space()='Log in']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("ravish1530@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Ravish@123");
		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
	}
	@Test(priority=3)
	@Description("Verify User registration.....")
	@Epic("EP001")
	@Feature("Feature3:Registration")
	@Story("Story:Registration")
	@Step("Verify Registration")
	@Severity(SeverityLevel.NORMAL)
	public void registration()
	{
		throw new SkipException("Skipping this Test");
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
